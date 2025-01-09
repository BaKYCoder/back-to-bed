package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.platform.Services;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackToBed {
    public static final String MOD_ID = "backtobed";
    public static final String MOD_NAME = "Back to Bed";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void initCommon() {
        LOGGER.info("Hello from Common init on {}! we are currently in a {} environment!",
                Services.getPlatform().getPlatformName(), Services.getPlatform().getEnvironmentName()
        );
        LOGGER.info("The ID for diamonds is {}", BuiltInRegistries.ITEM.getKey(Items.DIAMOND));

        ItemRegistry.registerItems();

        if (Services.getPlatform().isModLoaded(MOD_ID)) {
            LOGGER.info("Hello to " + MOD_NAME);
        }
    }
}
