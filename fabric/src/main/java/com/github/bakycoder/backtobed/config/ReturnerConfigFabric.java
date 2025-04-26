package com.github.bakycoder.backtobed.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class ReturnerConfigFabric extends MidnightConfig {
    @Entry(min = -1, max = 72000)
    public int duration_usage;

    @Entry(min = -1, max = 72000)
    public int cooldown;

    @Entry
    public boolean show_tooltip;

    public ReturnerConfigFabric(int duration, int cooldown, boolean tooltip) {
        this.duration_usage = duration;
        this.cooldown = cooldown;
        this.show_tooltip = tooltip;
    }
}