package com.github.bakycoder.backtobed.mixin;

import com.github.bakycoder.backtobed.BackToBed;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class MixinTitleScreen {
    
    @Inject(at = @At("HEAD"), method = "init()V")
    private void init(CallbackInfo info) {
        BackToBed.LOGGER.info("This line is printed by the Back to Bed mixin from Fabric!");
        BackToBed.LOGGER.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}
