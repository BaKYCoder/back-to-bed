package com.baky.backtobed.fabric.item;

import com.baky.backtobed.BackToBed;
import com.baky.backtobed.item.custom.MagicalReturner;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

public class ModItems {
    public static void init() {
        registerItem("magical_returner",
                new MagicalReturner(new Item.Properties().tab(CreativeModeTab.TAB_TOOLS)));
    }

    private static void registerItem(String name, Item item) {
        ResourceLocation resourceLocation = new ResourceLocation(BackToBed.MOD_ID, name);
        Registry.register(Registry.ITEM, resourceLocation, item);
        Registry.ITEM.get(resourceLocation);
    }
}
