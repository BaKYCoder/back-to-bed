package com.github.bakycoder.backtobed.platform.services;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public interface IItemRegistrar {
    interface IRegisteredItem<T> {
        T get();
    }

    <T extends Item> IRegisteredItem<T> register(String path, Supplier<T> item);
}
