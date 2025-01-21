package com.github.bakycoder.backtobed.item.returner.features;

import com.github.bakycoder.backtobed.api.IFeatureInjector;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MountFeatureInjector implements IFeatureInjector {
    private final static ScheduledExecutorService EXECUTOR = Executors.newSingleThreadScheduledExecutor();

    @Override
    public boolean handlesTeleportation() {
        return false;
    }

    @Override
    public void inject(ServerPlayer player, ServerLevel respawn, ItemStack stack, Vec3 destination) {
        Entity vehicle = player.getVehicle();
        if (vehicle != null) {
            EXECUTOR.schedule(() -> {
                vehicle.teleportTo(
                        respawn,
                        destination.x(),
                        destination.y(),
                        destination.z(),
                        Collections.emptySet(),
                        vehicle.getYRot(),
                        vehicle.getXRot()
                );
            }, 120, TimeUnit.MILLISECONDS);
        }
    }
}
