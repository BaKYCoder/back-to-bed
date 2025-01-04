package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.platform.services.IItemRegistry;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.List;

public class FabricItemRegistry implements IItemRegistry {

    @Override
    public <T extends Item> T register(String path, T item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(BackToBed.MOD_ID, path), item);
    }

    @Override
    public void addToCreativeTab(ResourceKey<CreativeModeTab> tab, ItemStack... stacks) {
        ItemGroupEvents.modifyEntriesEvent(tab).register(content -> content.acceptAll(List.of(stacks)));
    }
}
