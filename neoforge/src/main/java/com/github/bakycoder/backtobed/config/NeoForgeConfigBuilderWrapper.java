package com.github.bakycoder.backtobed.config;

import com.github.bakycoder.backtobed.util.ConfigBuilder;
import com.github.bakycoder.backtobed.util.ConfigValue;
import net.neoforged.neoforge.common.ModConfigSpec;

public class NeoForgeConfigBuilderWrapper implements ConfigBuilder {
    private final ModConfigSpec.Builder builder;

    public NeoForgeConfigBuilderWrapper(ModConfigSpec.Builder builder) {
        this.builder = builder;
    }

    @Override public ConfigBuilder comment(String comment) { builder.comment(comment); return this; }
    @Override public void push(String path) { builder.push(path); }
    @Override public void pop() { builder.pop(); }

    @Override
    public <T> NeoForgeConfigValueWrapper<T> define(String name, T defaultValue) {
        return new NeoForgeConfigValueWrapper<>(builder.define(name, defaultValue));
    }

    @Override
    public ConfigValue<Integer> defineInRange(String name, int defaultValue, int min, int max) {
        return new NeoForgeConfigValueWrapper<>(builder.defineInRange(name, defaultValue, min, max));
    }
}

