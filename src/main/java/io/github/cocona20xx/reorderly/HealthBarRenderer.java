package io.github.cocona20xx.reorderly;

import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.mojang.blaze3d.systems.RenderSystem;
import io.github.cocona20xx.reorderly.config.ReOrderlyConfigManager;
import io.github.cocona20xx.reorderly.util.RenderUtil;
import io.github.cocona20xx.reorderly.api.UIManager;
import io.github.cocona20xx.reorderly.api.UIStyle;
import io.github.cocona20xx.reorderly.config.ReOrderlyConfigAccessor;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.*;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Matrix4f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.RaycastContext;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.stream.StreamSupport;

public class HealthBarRenderer {

    public static void render(MatrixStack matrices, float partialTicks, Camera camera, GameRenderer gameRenderer, LightmapTextureManager lightmapTextureManager, Matrix4f projection, Frustum capturedFrustum) {
        MinecraftClient mc = MinecraftClient.getInstance();
        ReOrderlyConfigAccessor configAccessor = ReOrderlyConfigManager.getAccessor();
        if(mc.world == null || (!configAccessor.canRenderInF1() && !MinecraftClient.isHudEnabled()) || !configAccessor.canDraw()) {
            return;
        }
        final Entity cameraEntity = camera.getFocusedEntity() != null ? camera.getFocusedEntity() : mc.player; //possible fix for optifine (see https://github.com/UpcraftLP/Orderly/issues/3)
        assert cameraEntity != null : "Camera Entity must not be null!";
        if(configAccessor.showFocusedOnly()) {
            Entity focused = getEntityLookedAt(cameraEntity);
            if(focused instanceof LivingEntity && focused.isAlive()) {
                renderHealthBar((LivingEntity) focused, matrices, partialTicks, camera, cameraEntity);
            }
        }
        else {
            Vec3d cameraPos = camera.getPos();
            final Frustum frustum;
            if(capturedFrustum != null) {
                frustum = capturedFrustum;
            }
            else {
                frustum = new Frustum(matrices.peek().getModel(), projection);
                frustum.setPosition(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ());
            }
            StreamSupport.stream(mc.world.getEntities().spliterator(), false).filter(entity -> entity instanceof LivingEntity && entity != cameraEntity && entity.isAlive() && Iterables.size(entity.getPassengersDeep()) == 0 && entity.shouldRender(cameraPos.getX(), cameraPos.getY(), cameraPos.getZ()) && (entity.ignoreCameraFrustum || frustum.isVisible(entity.getBoundingBox()))).map(LivingEntity.class::cast).forEach(entity -> renderHealthBar(entity, matrices, partialTicks, camera, cameraEntity));
        }
    }

    private static Entity getEntityLookedAt(Entity e) {
        Entity foundEntity = null;
        final double finalDistance = 32;
        double distance = finalDistance;
        HitResult pos = raycastUtil(e, finalDistance);
        Vec3d positionVector = e.getPos();
        if(e instanceof PlayerEntity) {
            positionVector = positionVector.add(0, e.getEyeHeight(e.getPose()), 0);
        }
        if(pos != null) {
            distance = pos.getPos().distanceTo(positionVector);
        }
        Vec3d lookVector = e.getRotationVector();
        Vec3d reachVector = positionVector.add(lookVector.x * finalDistance, lookVector.y * finalDistance, lookVector.z * finalDistance);
        Entity lookedEntity = null;
        List<Entity> entitiesInBoundingBox = e.getCommandSenderWorld().getOtherEntities(e, e.getBoundingBox().stretch(lookVector.x * finalDistance, lookVector.y * finalDistance, lookVector.z * finalDistance).expand(1.0F));
        double minDistance = distance;
        for(Entity entity : entitiesInBoundingBox) {
            if(entity.collides()) {
                Box collisionBox = entity.getVisibilityBoundingBox();
                Optional<Vec3d> interceptPosition = collisionBox.raycast(positionVector, reachVector);
                if(collisionBox.contains(positionVector)) {
                    if(0.0D < minDistance || minDistance == 0.0D) {
                        lookedEntity = entity;
                        minDistance = 0.0D;
                    }
                }
                else if(interceptPosition.isPresent()) {
                    double distanceToEntity = positionVector.distanceTo(interceptPosition.get());
                    if(distanceToEntity < minDistance || minDistance == 0.0D) {
                        lookedEntity = entity;
                        minDistance = distanceToEntity;
                    }
                }
            }
            if(lookedEntity != null && (minDistance < distance || pos == null)) {
                foundEntity = lookedEntity;
            }
        }
        return foundEntity;
    }

    private static void renderHealthBar(LivingEntity passedEntity, MatrixStack matrices, float partialTicks, Camera camera, Entity viewPoint) {
        Preconditions.checkNotNull(passedEntity, "tried to render health bar for null entity");
        ReOrderlyConfigAccessor configAccessor = ReOrderlyConfigManager.getAccessor();
        UIStyle style = UIManager.getCurrentStyle();

        MinecraftClient mc = MinecraftClient.getInstance();
        Stack<LivingEntity> passengerStack = new Stack<>();
        LivingEntity entity = passedEntity;
        passengerStack.push(entity);
        while(entity.getPrimaryPassenger() instanceof LivingEntity) {
            entity = (LivingEntity) entity.getPrimaryPassenger();
            passengerStack.push(entity);
        }
        matrices.push();
        while(!passengerStack.isEmpty()) {
            entity = passengerStack.pop();
            if(!entity.isAlive()) continue;
            String idString = String.valueOf(Registry.ENTITY_TYPE.getId(entity.getType()));
            boolean boss = configAccessor.getBosses().contains(idString);
            if(configAccessor.getBlacklist().contains(idString)) {
                continue;
            }
            processing:
            {
                float distance = passedEntity.distanceTo(viewPoint);
                if(distance > configAccessor.getMaxDistance() || !passedEntity.canSee(viewPoint) || entity.isInvisible()) {
                    break processing;
                }
                if(boss && !configAccessor.showOnBosses()) {
                    break processing;
                }
                if(!configAccessor.showOnPlayers() && entity instanceof PlayerEntity) {
                    break processing;
                }
                if(entity.getMaxHealth() <= 0.0F) {
                    break processing;
                }
                double x = passedEntity.prevX + (passedEntity.getX() - passedEntity.prevX) * partialTicks;
                double y = passedEntity.prevY + (passedEntity.getY() - passedEntity.prevY) * partialTicks;
                double z = passedEntity.prevZ + (passedEntity.getZ() - passedEntity.prevZ) * partialTicks;

                EntityRenderDispatcher renderDispatcher = MinecraftClient.getInstance().getEntityRenderDispatcher();
                matrices.push();
                {
                    matrices.translate(x - renderDispatcher.camera.getPos().x, y - renderDispatcher.camera.getPos().y + passedEntity.getHeight() + configAccessor.getHeightAbove(), z - renderDispatcher.camera.getPos().z);
                    VertexConsumerProvider.Immediate immediate = mc.getBufferBuilders().getEntityVertexConsumers();
                    ItemStack icon = RenderUtil.getIcon(entity, boss);
                    final int light = 0xF000F0;
                    if(boss) {
                        style.renderBossEntity(matrices, immediate, camera, configAccessor, entity, light, icon);
                    }
                    else {
                        style.renderEntity(matrices, immediate, camera, configAccessor, entity, light, icon);
                    }
                }
                matrices.pop();
                matrices.translate(0.0D, -(configAccessor.getBackgroundHeight() + configAccessor.getBarHeight() + configAccessor.getBackgroundPadding()), 0.0D);
            }
        }
        matrices.pop();
    }

    @Nullable
    private static HitResult raycastUtil(Entity entity, double len) {
        Vec3d vec = new Vec3d(entity.getX(), entity.getY(), entity.getZ());
        if(entity instanceof PlayerEntity) {
            vec = vec.add(new Vec3d(0, entity.getEyeHeight(entity.getPose()), 0));
        }
        Vec3d look = entity.getRotationVector();
        if(look == null) {
            return null;
        }
        return raycastUtil(entity, vec, look, len);
    }

    private static HitResult raycastUtil(Entity entity, Vec3d origin, Vec3d ray, double len) {
        Vec3d next = origin.add(ray.normalize().multiply(len));
        return entity.getCommandSenderWorld().raycast(new RaycastContext(origin, next, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, entity));
    }
}
