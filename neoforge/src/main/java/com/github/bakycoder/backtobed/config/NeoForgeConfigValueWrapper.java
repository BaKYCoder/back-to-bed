package com.github.bakycoder.backtobed.config;

import com.github.bakycoder.backtobed.util.ConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeConfigValueWrapper<T> implements ConfigValue<T> {
    private final ModConfigSpec.ConfigValue<T> configValue;
    public NeoForgeConfigValueWrapper(ModConfigSpec.ConfigValue<T> configValue) {
        this.configValue = configValue;
    }
    @Override
    public T get() {
        return configValue.get();
    }
}
