package com.github.bakycoder.backtobed.config;

import com.github.bakycoder.backtobed.api.IConfigValue;
import net.minecraftforge.common.ForgeConfigSpec;

public class ForgeConfigValueWrapper<T> implements IConfigValue<T> {
    private final ForgeConfigSpec.ConfigValue<T> configValue;
    public ForgeConfigValueWrapper(ForgeConfigSpec.ConfigValue<T> configValue) {
        this.configValue = configValue;
    }
    @Override
    public T get() {
        return configValue.get();
    }
}
