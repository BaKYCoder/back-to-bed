package com.github.bakycoder.backtobed;

import net.minecraftforge.fml.common.Mod;

@Mod(BackToBed.MOD_ID)
public class BackToBedForge {

    public BackToBedForge() {
        // This method is invoked by the Forge mod loader when it is ready
        // to load your mod. You can access Forge and Common code in this
        // project.

        // Use Forge to bootstrap the Common mod.
        BackToBed.LOGGER.info("Hello Forge world!");
        BackToBed.init();

    }
}
