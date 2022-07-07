package io.github.cocona20xx.reorderly.api;

import io.github.cocona20xx.reorderly.config.ReOrderlyConfigAccessor;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public interface UIStyle {

    void renderEntity(MatrixStack matrices, VertexConsumerProvider.Immediate immediate, Camera camera, ReOrderlyConfigAccessor config, LivingEntity entity, int light, @Nullable ItemStack icon);

    void renderBossEntity(MatrixStack matrices, VertexConsumerProvider.Immediate immediate, Camera camera, ReOrderlyConfigAccessor config, LivingEntity entity, int light, @Nullable ItemStack icon);
}
