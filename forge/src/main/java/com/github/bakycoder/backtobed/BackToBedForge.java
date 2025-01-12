package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.platform.ForgeItemRegistrar;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;

import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

@Mod(BackToBed.MOD_ID)
public class BackToBedForge {
    private static final IEventBus MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

    public BackToBedForge() {
        BackToBed.LOGGER.info("Hello Forge world!");

        BackToBed.initCommon();

        ForgeItemRegistrar.registerToEventBus(MOD_EVENT_BUS);

        MOD_EVENT_BUS.addListener(this::manageTabs);
    }

    private void manageTabs(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(((RegistryObject<Item>) ItemRegistry.MAGICAL_RETURNER).get());
            event.accept(((RegistryObject<Item>) ItemRegistry.HELLS_RETURNER).get());
        }
    }
}
