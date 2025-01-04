package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.platform.Services;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;

public class BackToBedFabric implements ModInitializer {
    
    @Override
    public void onInitialize() {
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        BackToBed.LOGGER.info("Hello Fabric world!");
        BackToBed.init();

        Services.ITEM_REGISTRY.addToCreativeTab(CreativeModeTabs.TOOLS_AND_UTILITIES,
                new ItemStack(ModItems.MAGICAL_RETURNER),
                new ItemStack(ModItems.HELLS_RETURNER)
        );
    }
}
