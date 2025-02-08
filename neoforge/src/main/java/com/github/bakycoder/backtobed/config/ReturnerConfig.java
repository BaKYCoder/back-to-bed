package com.github.bakycoder.backtobed.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ReturnerConfig {
    private final ModConfigSpec.ConfigValue<Integer> DURATION_USAGE;
    private final ModConfigSpec.ConfigValue<Integer> COOLDOWN;
    private final ModConfigSpec.ConfigValue<Boolean> SHOW_TOOLTIP;

    private int cachedDurationUsage;
    private int cachedCooldown;
    private boolean cachedShowTooltip;

    public ReturnerConfig(ModConfigSpec.Builder builder, int defaultDuration, int defaultCooldown, boolean defaultTooltip) {
        builder.comment("Global configuration for all returners")
                .comment("Specific returners can override global values.")
                .comment("(If set to -1, they will use the global configuration.)")
                .push("global");

        DURATION_USAGE = builder
                .comment("The time (in ticks) the player must wait while using the returner before teleportation occurs.")
                .comment("(A lower value makes teleportation faster.)")
                .defineInRange("duration_usage", defaultDuration, 0, 72000);

        COOLDOWN = builder
                .comment("The time (in ticks) before the returner can be used again after teleportation.")
                .comment("(A value of 0 allows immediate reuse.)")
                .defineInRange("cooldown", defaultCooldown, 0, 72000);

        SHOW_TOOLTIP = builder
                .comment("Display item details in tooltip.")
                .comment("If set to false, tooltips will be disabled for all returners, regardless of individual returner settings.")
                .define("show_tooltip", defaultTooltip);

        builder.pop();
    }

    public ReturnerConfig(String category, ModConfigSpec.Builder builder, int defaultDuration, int defaultCooldown, boolean defaultTooltip) {
        builder.comment("Specific settings for '" + category + "'").push(category);

        DURATION_USAGE = builder
                .comment("The duration for returner to activate")
                .comment("(-1 follows the global setting)")
                .defineInRange("duration_usage", defaultDuration, -1, 72000);

        COOLDOWN = builder
                .comment("Delay before reuse")
                .comment("(-1 follows the global setting)")
                .defineInRange("cooldown", defaultCooldown, -1, 72000);

        SHOW_TOOLTIP = builder
                .comment("Display tooltip for returner")
                .define("show_tooltip", defaultTooltip);

        builder.pop();
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

