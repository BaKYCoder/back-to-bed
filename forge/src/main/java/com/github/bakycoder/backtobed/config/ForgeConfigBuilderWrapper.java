package com.github.bakycoder.backtobed.config;

import com.github.bakycoder.backtobed.util.ConfigBuilder;
import com.github.bakycoder.backtobed.util.ConfigValue;
import net.minecraftforge.common.ForgeConfigSpec;

public class ForgeConfigBuilderWrapper implements ConfigBuilder {
    private final ForgeConfigSpec.Builder builder;

    public ForgeConfigBuilderWrapper(ForgeConfigSpec.Builder builder) {
        this.builder = builder;
    }

    @Override public ConfigBuilder comment(String comment) { builder.comment(comment); return this; }
    @Override public void push(String path) { builder.push(path); }
    @Override public void pop() { builder.pop(); }

    @Override
    public <T> ForgeConfigValueWrapper<T> define(String name, T defaultValue) {
        return new ForgeConfigValueWrapper<>(builder.define(name, defaultValue));
    }

    @Override
    public ConfigValue<Integer> defineInRange(String name, int defaultValue, int min, int max) {
        return new ForgeConfigValueWrapper<>(builder.defineInRange(name, defaultValue, min, max));
    }
}

