package com.github.bakycoder.backtobed;


import com.github.bakycoder.backtobed.platform.NeoForgeItemRegistrar;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(BackToBed.MOD_ID)
public class BackToBedNeoForge {

    public BackToBedNeoForge(IEventBus eBus) {
        BackToBed.LOGGER.info("Hello NeoForge world!");

        BackToBed.initCommon();

        NeoForgeItemRegistrar.registerToEventBus(eBus);
    }
}
