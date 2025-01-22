package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.platform.services.IModConfig;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = BackToBed.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeoForgeModConfig implements IModConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ModConfigSpec.ConfigValue<Integer> DEFAULT_RETURNER_DURATION_USAGE = BUILDER
            .comment("Default duration usage of all returners (in ticks)")
            .defineInRange(List.of("default", "returner_duration_usage"), 60, 0, 72000);

    private static final ModConfigSpec.ConfigValue<Integer> DEFAULT_RETURNER_COOLDOWN = BUILDER
            .comment("Default cooldown duration for all returners after usage (in ticks)")
            .defineInRange(List.of("default", "returner_cooldown"), 50, 0, 72000);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private static int defaultReturnerDurationUsage;
    private static int defaultReturnerCooldown;

    @Override
    public int getDefaultReturnerDurationUsage() {
        return defaultReturnerDurationUsage;
    }

    @Override
    public int getDefaultReturnerCooldown() {
        return defaultReturnerCooldown;
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        defaultReturnerDurationUsage = DEFAULT_RETURNER_DURATION_USAGE.get();
        defaultReturnerCooldown = DEFAULT_RETURNER_COOLDOWN.get();
    }
}
