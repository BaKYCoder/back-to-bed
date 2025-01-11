package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.platform.services.IItemRegistrar;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public class FabricItemRegistrar implements IItemRegistrar {
    @Override
    public <T extends Item> T register(String path, Supplier<T> item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(BackToBed.MOD_ID, path), item.get());
    }
}
