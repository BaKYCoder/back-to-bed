package com.github.bakycoder.backtobed.fabric.item;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.fabric.BackToBedFabric;
import com.github.bakycoder.backtobed.item.MagicalReturner;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

/**
 * The utility class for registering items associated with the BackToBed mod. (Fabric version)
 * It provides methods to register individual items and is responsible for handling item registration.
 *
 * @see BackToBed
 * @see Item
 * @see ResourceLocation
 * @see Registry
 */
public class ModItems {
    /**
     * Registers a single item with the specified name and item instance.
     *
     * @param itemName The name of the item to register.
     * @param item     The item instance to register.
     */
    private static void registerItem(String itemName, Item item) {
        ResourceLocation resourceLocation = new ResourceLocation(BackToBed.MOD_ID, itemName);
        Registry.register(Registry.ITEM, resourceLocation, item);
    }

    /**
     * Registers all items associated with the BackToBed mod.
     * This method is called during mod initialization.
     *
     * @see BackToBedFabric
     */
    public static void registerItems() {
        BackToBed.LOGGER.info("Registering items...");

        registerItem(MagicalReturner.ITEM_NAME, new MagicalReturner());

        BackToBed.LOGGER.info("Items registered successfully!");
    }
}
