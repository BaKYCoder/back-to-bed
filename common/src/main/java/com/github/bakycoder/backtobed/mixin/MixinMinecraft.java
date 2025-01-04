package com.github.bakycoder.backtobed.mixin;

import com.github.bakycoder.backtobed.BackToBed;
import net.minecraft.client.Minecraft;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MixinMinecraft {
    
    @Inject(at = @At("TAIL"), method = "<init>")
    private void init(CallbackInfo info) {
        BackToBed.LOGGER.info("This line is printed by the Back to Bed common mixin!");
        BackToBed.LOGGER.info("MC Version: {}", Minecraft.getInstance().getVersionType());
    }
}
