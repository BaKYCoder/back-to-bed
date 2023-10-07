package com.baky.backtobed.fabric;

import com.baky.backtobed.BackToBed;
import com.baky.backtobed.fabric.item.ModItems;
import net.fabricmc.api.ModInitializer;

public class BackToBedFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ModItems.init();
        BackToBed.init();
    }
}