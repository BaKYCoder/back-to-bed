package com.github.bakycoder.backtobed;


import com.github.bakycoder.backtobed.platform.NeoForgeItemRegistrar;

import net.minecraft.world.item.CreativeModeTabs;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

@Mod(BackToBed.MOD_ID)
public class BackToBedNeoForge {

    public BackToBedNeoForge(IEventBus eBus) {
        BackToBed.LOGGER.info("Hello NeoForge world!");

        BackToBed.initCommon();

        NeoForgeItemRegistrar.registerToEventBus(eBus);

        eBus.addListener(this::manageTabs);
    }

    private void manageTabs(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ItemRegistry.MAGICAL_RETURNER.get());
            event.accept(ItemRegistry.HELLS_RETURNER.get());
        }
    }
}
