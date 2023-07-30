package com.baky.backtobed.item;

import com.baky.backtobed.BackToBed;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {
    public static final Item MAGICAL_RETURNER = registerItem("magical_returner",
            new Item(new FabricItemSettings().group(ItemGroup.TOOLS)));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(BackToBed.MOD_ID, name), item);
    }
    public static void register() {
        BackToBed.LOGGER.debug("Registering mod items for {}", BackToBed.MOD_ID);
    }
}
