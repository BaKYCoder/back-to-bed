package com.github.bakycoder.backtobed.config;

import com.github.bakycoder.backtobed.util.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec;

public class ForgeConfigValueWrapper<T> implements ConfigValue<T> {
    private final ForgeConfigSpec.ConfigValue<T> configValue;
    public ForgeConfigValueWrapper(ForgeConfigSpec.ConfigValue<T> configValue) {
        this.configValue = configValue;
    }
    @Override
    public T get() {
        return configValue.get();
    }
}
