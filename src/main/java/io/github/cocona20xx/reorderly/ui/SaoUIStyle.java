package io.github.cocona20xx.reorderly.ui;

import io.github.cocona20xx.reorderly.config.ReOrderlyConfigAccessor;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;

public class SaoUIStyle extends SimpleUIStyle {

    @Override
    protected void render(MatrixStack matrices, VertexConsumerProvider.Immediate immediate, Camera camera, ReOrderlyConfigAccessor config, LivingEntity entity, int light, @Nullable ItemStack icon, boolean boss) {

    }
}
