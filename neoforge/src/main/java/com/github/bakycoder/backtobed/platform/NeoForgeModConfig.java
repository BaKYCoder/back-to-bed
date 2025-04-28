package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.config.NeoForgeConfigBuilderWrapper;
import com.github.bakycoder.backtobed.config.ConfigEntry;
import com.github.bakycoder.backtobed.config.ReturnerConfigPresets;
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
    private static final ModConfigSpec.Builder RAW_BUILDER = new ModConfigSpec.Builder();
    private static final NeoForgeConfigBuilderWrapper BUILDER = new NeoForgeConfigBuilderWrapper(RAW_BUILDER);

    private static final ConfigEntry GLOBAL_CONFIG = new ConfigEntry(BUILDER, ReturnerConfigPresets.GLOBAL, true);
    private static final Map<String, ConfigEntry> RETURNER_CONFIGS = new HashMap<>();

    static {
        RETURNER_CONFIGS.put("magical_returner", new ConfigEntry(BUILDER, ReturnerConfigPresets.MAGICAL));
        RETURNER_CONFIGS.put("hells_returner", new ConfigEntry(BUILDER, ReturnerConfigPresets.HELLS));
        RETURNER_CONFIGS.put("mysterious_returner", new ConfigEntry(BUILDER, ReturnerConfigPresets.MYSTERIOUS));
    }

    public static final ModConfigSpec SPEC = RAW_BUILDER.build();

    @Override
    public int getReturnerDurationUsage(Returner returner) {
        return getConfigValue(returner, ConfigEntry::getDurationUsage, GLOBAL_CONFIG.getDurationUsage());
    }

    @Override
    public int getReturnerCooldown(Returner returner) {
        return getConfigValue(returner, ConfigEntry::getCooldown, GLOBAL_CONFIG.getCooldown());
    }

    @Override
    public boolean showReturnerTooltip(Returner returner) {
        return getConfigValue(returner, ConfigEntry::showTooltip, GLOBAL_CONFIG.showTooltip());
    }

    private <T> T getConfigValue(Returner returner, ConfigValueGetter<T> getter, T globalDefault) {
        String key = BuiltInRegistries.ITEM.getKey(returner).getPath();
        ConfigEntry config = RETURNER_CONFIGS.getOrDefault(key, GLOBAL_CONFIG);
        return getter.get(config, globalDefault);
    }

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        GLOBAL_CONFIG.load();
        RETURNER_CONFIGS.values().forEach(ConfigEntry::load);
    }

    @FunctionalInterface
    private interface ConfigValueGetter<T> {
        T get(ConfigEntry config, T globalDefault);
    }
}
