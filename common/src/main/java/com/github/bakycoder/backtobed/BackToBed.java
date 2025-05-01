package com.github.bakycoder.backtobed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BackToBed {
    public static final String MOD_ID = "backtobed";
    public static final String MOD_NAME = "Back to Bed";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void initCommon() {
        ItemRegistry.registerItems();
    }
}
