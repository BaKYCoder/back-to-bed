package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.item.Returner;
import com.github.bakycoder.backtobed.platform.Services;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class AllItems {
    public static final Item MAGICAL_RETURNER = Services.ITEM_REGISTRY
            .register("magical_returner", new Returner(Level.OVERWORLD));

    public static final Item HELLS_RETURNER = Services.ITEM_REGISTRY
            .register("hells_returner", new Returner(Level.NETHER));

    public static void register() {}
}
