package com.baky.backtobed.fabric;

import com.baky.backtobed.BackToBed;
import net.fabricmc.api.ModInitializer;

public class BackToBedFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        BackToBed.init();
    }
}