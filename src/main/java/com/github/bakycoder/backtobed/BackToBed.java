package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.item.ModItems;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

/**
 * Main class representing the BackToBed mod.
 */
@Mod(BackToBed.MOD_ID)
public class BackToBed {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "backtobed";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    /**
     * Constructor for the BackToBed mod class.
     *
     * @param modEventBus The event bus for registering mod components.
     */
    public BackToBed(IEventBus modEventBus) {
        BackToBed.LOGGER.info("Initializing mod...");

        try {
            // Register the Deferred Register to the mod event bus so items get registered
            ModItems.initialize(modEventBus);

            BackToBed.LOGGER.info("Mod initialized successfully!");
        } catch (Exception e) {
            BackToBed.LOGGER.error("Failed to initialize mod", e);
        }
    }
}
