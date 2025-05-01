package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.platform.FabricModConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class BackToBedFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        BackToBed.initCommon();
        this.configureTabEntries();

        MidnightConfig.init(BackToBed.MOD_ID, FabricModConfig.class);
    }

    public void configureTabEntries() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(ItemRegistry::acceptItemsToTab);
    }
}
