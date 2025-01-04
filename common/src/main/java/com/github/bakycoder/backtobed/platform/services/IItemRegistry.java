package com.github.bakycoder.backtobed.platform.services;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface IItemRegistry {
    <T extends Item> T register(String path, T item);
    void addToCreativeTab(ResourceKey<CreativeModeTab> tab, ItemStack... stacks);
}
