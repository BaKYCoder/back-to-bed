package com.github.bakycoder.backtobed;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

public class BackToBedFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.s
        BackToBed.LOGGER.info("Hello Fabric world!");

        BackToBed.initCommon();

        manageTabs();
    }

    public void manageTabs() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(content -> {
            content.accept((Item) ItemRegistry.MAGICAL_RETURNER);
            content.accept((Item) ItemRegistry.HELLS_RETURNER);
        });
    }
}
