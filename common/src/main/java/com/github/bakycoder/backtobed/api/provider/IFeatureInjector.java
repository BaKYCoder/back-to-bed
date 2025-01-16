package com.github.bakycoder.backtobed.api.provider;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public interface IFeatureInjector {
    boolean isEnabled();
    void inject(Level level, LivingEntity entity, ItemStack stack, Vec3 destination);
}
