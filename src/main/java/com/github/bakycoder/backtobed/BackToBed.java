package com.github.bakycoder.backtobed;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(BackToBed.MOD_ID)
public class BackToBed {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "backtobed";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public BackToBed(IEventBus modEventBus) {
        // Register the Deferred Register to the mod event bus so items get registered
    }
}
