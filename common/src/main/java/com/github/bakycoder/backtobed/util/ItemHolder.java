package com.github.bakycoder.backtobed.util;

import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public record ItemHolder(String path, Supplier<Item> item) {
}
