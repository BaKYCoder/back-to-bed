package com.github.bakycoder.backtobed.api;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;

public interface IFeatureInjector {
    boolean handlesTeleportation();
    void inject(ServerPlayer player, ServerLevel respawn, ItemStack stack, Vec3 destination);
}
