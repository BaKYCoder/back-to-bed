package com.github.bakycoder.backtobed.item;

import com.github.bakycoder.backtobed.BackToBed;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.DeferredItem;
import net.minecraft.world.item.Item;

public class ModItems {

    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BackToBed.MOD_ID);

    private static final DeferredItem<Item> MAGICAL_RETURNER = ITEMS.registerSimpleItem("magical_returner");

    public static void register(IEventBus eventBus) {
        BackToBed.LOGGER.info("Registering items...");

        ITEMS.register(eventBus);

        BackToBed.LOGGER.info("Items registered successfully!");
    }
}