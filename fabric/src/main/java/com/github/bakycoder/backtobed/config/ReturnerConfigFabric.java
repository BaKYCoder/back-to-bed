package com.github.bakycoder.backtobed.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class ReturnerConfigFabric extends MidnightConfig {
    @Entry(min = 0, max = 72000)
    public static int duration_usage = 60;

    @Entry(min = 0, max = 72000)
    public static int cooldown = 50;

    @Entry
    public static boolean show_tooltip = true;
}