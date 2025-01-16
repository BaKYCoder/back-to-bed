package com.github.bakycoder.backtobed.item.returners.injections;

import com.github.bakycoder.backtobed.api.provider.IFeatureInjector;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;


public class MountFeatureInjector implements IFeatureInjector {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void inject(Level level, LivingEntity entity, ItemStack stack, Vec3 destination) {
        ServerPlayer player = (ServerPlayer) entity;

        if (player.getVehicle() != null) {
            Entity vehicle = player.getVehicle();
            player.stopRiding();

            vehicle.teleportTo(destination.x(), destination.y(), destination.z());

            // TODO: Resolve problem, when entity is not rendered
        }
    }
}
