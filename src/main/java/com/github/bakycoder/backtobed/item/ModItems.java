package com.github.bakycoder.backtobed.item;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.item.custom.MagicalReturner;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.minecraft.world.item.Item;

/**
 * Class responsible for registering custom items in the mod.
 */
public final class ModItems {

    // Create a deferred register for items
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BackToBed.MOD_ID);

    // Define and register the magical returner item
    public static final DeferredItem<Item> MAGICAL_RETURNER = ITEMS.register(MagicalReturner.ITEM_NAME, MagicalReturner::new);

    /**
     * Initializes the mod items.
     *
     * @param eventBus The event bus to register the items with.
     */
    public static void initialize(IEventBus eventBus) {
        BackToBed.LOGGER.info("Registering items...");

        // Register items with the event bus
        ITEMS.register(eventBus);

        // Add listener for building creative mode tab contents
        eventBus.addListener(ModItems::buildModeTabContents);

        BackToBed.LOGGER.info("Items registered successfully!");
    }

    /**
     * Listener method for building creative mode tab contents.
     *
     * @param event The event for building creative mode tab contents.
     */
    private static void buildModeTabContents(BuildCreativeModeTabContentsEvent event) {
        // Only accept the magical returner item if the tab is for tools and utilities
        if (event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) event.accept(MAGICAL_RETURNER);
    }
}