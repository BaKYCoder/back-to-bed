package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.platform.Services;

import net.fabricmc.api.ModInitializer;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.ItemStack;

public class BackToBed implements ModInitializer {
    
    @Override
    public void onInitialize() {
        
        // This method is invoked by the Fabric mod loader when it is ready
        // to load your mod. You can access Fabric and Common code in this
        // project.

        // Use Fabric to bootstrap the Common mod.
        Constants.LOG.info("Hello Fabric world!");
        CommonClass.init();

        Services.ITEM_REGISTRY.addToCreativeTab(CreativeModeTabs.TOOLS_AND_UTILITIES,
                new ItemStack(AllItems.MAGICAL_RETURNER),
                new ItemStack(AllItems.HELLS_RETURNER)
        );
    }
}
