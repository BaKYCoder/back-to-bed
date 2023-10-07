package com.baky.backtobed.forge;

import com.baky.backtobed.BackToBed;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BackToBed.MOD_ID)
public class BackToBedForge {
    public BackToBedForge() {
        BackToBed.init();
    }
}