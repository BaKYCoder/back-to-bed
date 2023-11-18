package com.github.bakycoder.backtobed.forge.item;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.item.MagicalReturner;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * The {@code ModItems} class handles the registration of items for the BackToBed mod. (Forge version)
 */
public class ModItems {
    /**
     * The deferred register for items, associated with the mod ID.
     * This register is responsible for the deferred registration of items during mod initialization.
     */
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, BackToBed.MOD_ID);

    /**
     * Registers items for the BackToBed mod.
     * This method should be called during mod initialization to register all custom items.
     *
     * @param eventBus The mod event bus used for item registration.
     * @see MagicalReturner
     */
    public static void registerItems(IEventBus eventBus) {
        BackToBed.LOGGER.info("Registering items...");

        ITEMS.register(MagicalReturner.ITEM_NAME, MagicalReturner::new);

        ITEMS.register(eventBus);

        BackToBed.LOGGER.info("Items registered successfully!");
    }
}
