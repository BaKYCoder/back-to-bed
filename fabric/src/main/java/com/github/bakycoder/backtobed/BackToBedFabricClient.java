package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.config.ReturnerConfigFabric;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;

public class BackToBedFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        MidnightConfig.init(BackToBed.MOD_ID, ReturnerConfigFabric.class);
    }
}
