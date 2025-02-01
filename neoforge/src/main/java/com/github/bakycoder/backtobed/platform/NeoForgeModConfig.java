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

    private static final ModConfigSpec.ConfigValue<Integer> RETURNER_DURATION_USAGE = BUILDER
            .comment("Duration usage of all returners (in ticks)")
            .defineInRange("returner_duration_usage", 60, 0, 72000);

    private static final ModConfigSpec.ConfigValue<Integer> RETURNER_COOLDOWN = BUILDER
            .comment("Cooldown duration for all returners after usage (in ticks)")
            .defineInRange("returner_cooldown", 50, 0, 72000);

    public static final ModConfigSpec SPEC = BUILDER.build();

    private static int returnerDurationUsage;
    private static int returnerCooldown;

    @Override
    public int getReturnerDurationUsage() {
        return returnerDurationUsage;
    }

    @Override
    public int getReturnerCooldown() {
        return returnerCooldown;
    }

    @Override
    public int getReturnerDurability() {
        return 20;
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event)
    {
        returnerDurationUsage = RETURNER_DURATION_USAGE.get();
        returnerCooldown = RETURNER_COOLDOWN.get();
    }
}
