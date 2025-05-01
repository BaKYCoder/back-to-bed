package com.github.bakycoder.backtobed.config;

import com.github.bakycoder.backtobed.api.IConfigBuilder;
import com.github.bakycoder.backtobed.api.IConfigValue;

public class ConfigEntry {
    private final IConfigValue<Integer> DURATION_USAGE;
    private final IConfigValue<Integer> COOLDOWN;
    private final IConfigValue<Boolean> SHOW_TOOLTIP;

    private int cachedDurationUsage;
    private int cachedCooldown;
    private boolean cachedShowTooltip;

    public ConfigEntry(IConfigBuilder builder, ReturnerConfigPresets.Preset preset, boolean isGlobal) {
        if (isGlobal) {
            builder.comment("Global configuration for all returners.")
                    .comment("Individual returners can override these settings.")
                    .push("global");

            DURATION_USAGE = builder
                    .comment("Time (in ticks) a player must wait before teleportation occurs.")
                    .defineInRange("duration_usage", preset.durationUsage(), 0, 72000);

            COOLDOWN = builder
                    .comment("Time (in ticks) before the returner can be used again after teleportation.")
                    .defineInRange("cooldown", preset.cooldown(), 0, 72000);

            SHOW_TOOLTIP = builder
                    .comment("Set to false to disable tooltips for all returners, regardless of individual settings.")
                    .define("show_tooltip", preset.showTooltip());
        } else {
            builder.comment("Specific configuration for '" + preset.name() + "' settings.")
                    .push(preset.name());

            DURATION_USAGE = builder
                    .comment("A value of -1 means it will use the global setting.")
                    .defineInRange("duration_usage", preset.durationUsage(), -1, 72000);

            COOLDOWN = builder
                    .comment("A value of -1 means it will use the global setting.")
                    .defineInRange("cooldown", preset.cooldown(), -1, 72000);

            SHOW_TOOLTIP = builder
                    .comment("Enable or disable the tooltip for this specific returner.")
                    .define("show_tooltip", preset.showTooltip());
        }

        builder.pop();
    }

    public ConfigEntry(IConfigBuilder builder, ReturnerConfigPresets.Preset preset) {
        this(builder, preset, false);
    }

        public void load() {
        cachedDurationUsage = DURATION_USAGE.get();
        cachedCooldown = COOLDOWN.get();
        cachedShowTooltip = SHOW_TOOLTIP.get();
    }

    public int getDurationUsage(int globalDefault) {
        return cachedDurationUsage == -1 ? globalDefault : cachedDurationUsage;
    }

    public int getCooldown(int globalDefault) {
        return cachedCooldown == -1 ? globalDefault : cachedCooldown;
    }

    public boolean showTooltip(boolean globalDefault) {
        return globalDefault && cachedShowTooltip;
    }

    public int getDurationUsage() {
        return cachedDurationUsage;
    }

    public int getCooldown() {
        return cachedCooldown;
    }

    public boolean showTooltip() {
        return cachedShowTooltip;
    }
}

