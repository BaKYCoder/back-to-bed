package com.github.bakycoder.backtobed;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(BackToBed.MOD_ID)
public class BackToBedForge {
    private static final IEventBus MOD_EVENT_BUS = FMLJavaModLoadingContext.get().getModEventBus();

    public BackToBedForge() {
        BackToBed.LOGGER.info("Hello Forge world!");

        BackToBed.setup();
    }
}
