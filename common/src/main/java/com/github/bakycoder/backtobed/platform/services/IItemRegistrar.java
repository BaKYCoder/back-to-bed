package com.github.bakycoder.backtobed.platform.services;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface IItemRegistrar {
    <T extends Item> Object register(String path, Supplier<T> item);
}
