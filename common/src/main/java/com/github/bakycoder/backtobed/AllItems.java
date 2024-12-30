package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.item.HellsReturner;
import com.github.bakycoder.backtobed.item.MagicalReturner;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class AllItems {
    public static final Item MAGICAL_RETURNER = register("magical_returner",
            new MagicalReturner(new Item
                    .Properties()
                    .stacksTo(1)
            )
    );

    public static final Item HELLS_RETURNER = register("hells_returner",
            new HellsReturner(new Item
                    .Properties()
                    .stacksTo(1)
            )
    );

    private static <T extends Item> T register(String path, T item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.tryBuild(Constants.MOD_ID, path), item);
    }

    public static void register() {}
}
