package com.github.bakycoder.backtobed;


import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(BackToBed.MOD_ID)
public class BackToBedNeoForge {

    public BackToBedNeoForge(IEventBus eventBus) {
        // This method is invoked by the NeoForge mod loader when it is ready
        // to load your mod. You can access NeoForge and Common code in this
        // project.

        // Use NeoForge to bootstrap the Common mod.
        BackToBed.LOGGER.info("Hello NeoForge world!");
        BackToBed.init();
    }
}
