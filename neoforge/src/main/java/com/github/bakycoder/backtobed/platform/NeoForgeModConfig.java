package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.config.ReturnerConfig;
import com.github.bakycoder.backtobed.item.returner.Returner;
import com.github.bakycoder.backtobed.platform.services.IModConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.Map;

@EventBusSubscriber(modid = BackToBed.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class NeoForgeModConfig implements IModConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    private static final ReturnerConfig GLOBAL_CONFIG = new ReturnerConfig(BUILDER, 60, 50, true);
    private static final Map<String, ReturnerConfig> RETURNER_CONFIGS = new HashMap<>();

    static {
        RETURNER_CONFIGS.put("magical_returner", new ReturnerConfig("magical_returner", BUILDER, -1, -1, true));
        RETURNER_CONFIGS.put("hells_returner", new ReturnerConfig("hells_returner", BUILDER, 70, 60, true));
    }

    public static final ModConfigSpec SPEC = BUILDER.build();

    @Override
    public int getReturnerDurationUsage(Returner returner) {
        return getConfigValue(returner, ReturnerConfig::getDurationUsage, GLOBAL_CONFIG.getDurationUsage());
    }

    @Override
    public int getReturnerCooldown(Returner returner) {
        return getConfigValue(returner, ReturnerConfig::getCooldown, GLOBAL_CONFIG.getCooldown());
    }

    @Override
    public boolean showReturnerTooltip(Returner returner) {
        return getConfigValue(returner, ReturnerConfig::showTooltip, GLOBAL_CONFIG.showTooltip());
    }

    private <T> T getConfigValue(Returner returner, ConfigValueGetter<T> getter, T globalDefault) {
        String key = BuiltInRegistries.ITEM.getKey(returner).getPath();
        ReturnerConfig config = RETURNER_CONFIGS.getOrDefault(key, GLOBAL_CONFIG);
        return getter.get(config, globalDefault);
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        GLOBAL_CONFIG.load();
        RETURNER_CONFIGS.values().forEach(ReturnerConfig::load);
    }

    @FunctionalInterface
    private interface ConfigValueGetter<T> {
        T get(ReturnerConfig config, T globalDefault);
    }
}
