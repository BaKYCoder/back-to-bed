package com.github.bakycoder.backtobed.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class ReturnerConfigFabric extends MidnightConfig {
    @Entry(min = -1, max = 72000)
    public int duration_usage;

    @Entry(min = -1, max = 72000)
    public int cooldown;

    @Entry
    public boolean show_tooltip;

    public ReturnerConfigFabric(ReturnerConfigPresets.Preset preset) {
        this.duration_usage = preset.durationUsage();
        this.cooldown = preset.cooldown();
        this.show_tooltip = preset.showTooltip();
    }
}