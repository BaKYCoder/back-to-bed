package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.item.HellsReturner;
import com.github.bakycoder.backtobed.item.Returner;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class AllItems {
    public static final Item MAGICAL_RETURNER = register("magical_returner", new Returner(Level.OVERWORLD));
    public static final Item HELLS_RETURNER = register("hells_returner", new Returner(Level.NETHER));

    private static <T extends Item> T register(String path, T item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(Constants.MOD_ID, path), item);
    }

    public static void register() {}
}
