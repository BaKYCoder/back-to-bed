package com.github.bakycoder.backtobed.config;

public class ReturnerConfigPresets {
    public static final Preset GLOBAL = new Preset("global", 60, 50, true);
    public static final Preset MAGICAL = new Preset("magical_returner", -1, -1, true);
    public static final Preset HELLS = new Preset("hells_returner" , 70, 60, true);
    public static final Preset MYSTERIOUS = new Preset("mysterious_returner" , 90, 70, true);
    public static final Preset TRESREALM = new Preset("tresrealm_returner" , 75, 95, true);

    public record Preset(String name, int durationUsage, int cooldown, boolean showTooltip) {}
}
