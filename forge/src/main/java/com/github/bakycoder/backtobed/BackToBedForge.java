package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.platform.ForgeItemRegistrar;

import net.minecraft.world.item.CreativeModeTabs;

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BackToBed.MOD_ID)
public class BackToBedForge {
    private static final IEventBus MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

    public BackToBedForge() {
        BackToBed.LOGGER.info("Hello Forge world!");

        BackToBed.initCommon();

        ForgeItemRegistrar.registerToEventBus(MOD_EVENT_BUS);
        MOD_EVENT_BUS.addListener(this::configureTabEntries);
    }

    private void configureTabEntries(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            ItemRegistry.acceptItemsToTab(event);
        }
    }
}
