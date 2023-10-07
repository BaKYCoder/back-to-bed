package com.baky.backtobed.forge;

import com.baky.backtobed.BackToBed;
import com.baky.backtobed.forge.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(BackToBed.MOD_ID)
public class BackToBedForge {
    public BackToBedForge() {
        ModItems.register(FMLJavaModLoadingContext.get().getModEventBus());

        MinecraftForge.EVENT_BUS.register(this);

        BackToBed.init();
    }
}