package com.github.bakycoder.backtobed.config;

import com.github.bakycoder.backtobed.api.IConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeConfigValueWrapper<T> implements IConfigValue<T> {
    private final ModConfigSpec.ConfigValue<T> configValue;
    public NeoForgeConfigValueWrapper(ModConfigSpec.ConfigValue<T> configValue) {
        this.configValue = configValue;
    }
    @Override
    public T get() {
        return configValue.get();
    }
}
