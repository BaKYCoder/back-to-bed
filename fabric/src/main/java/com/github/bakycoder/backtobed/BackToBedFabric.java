package com.github.bakycoder.backtobed;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;

public class BackToBedFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.s
        BackToBed.LOGGER.info("Hello Fabric world!");

        BackToBed.initCommon();
        this.configureTabEntries();
    }

    public void configureTabEntries() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES)
                .register(ItemRegistry::acceptItemsToTab);
    }
}
