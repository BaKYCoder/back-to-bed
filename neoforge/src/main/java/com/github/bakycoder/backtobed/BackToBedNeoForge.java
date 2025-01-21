package com.github.bakycoder.backtobed;


import com.github.bakycoder.backtobed.platform.NeoForgeModConfig;
import com.github.bakycoder.backtobed.platform.NeoForgeItemRegistrar;

import net.minecraft.world.item.CreativeModeTabs;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(BackToBed.MOD_ID)
public class BackToBedNeoForge {
    public BackToBedNeoForge(IEventBus modEventBus, ModContainer modContainer) {
        BackToBed.LOGGER.info("Hello NeoForge world!");

        BackToBed.initCommon();

        NeoForgeItemRegistrar.registerToEventBus(modEventBus);
        modEventBus.addListener(this::configureTabEntries);

        modContainer.registerConfig(ModConfig.Type.SERVER, NeoForgeModConfig.SPEC);
    }

    private void configureTabEntries(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            ItemRegistry.acceptItemsToTab(event);
        }
    }
}
