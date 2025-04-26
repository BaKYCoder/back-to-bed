package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.config.ForgeConfigBuilderWrapper;
import com.github.bakycoder.backtobed.config.ConfigEntry;
import com.github.bakycoder.backtobed.config.ReturnerConfigPresets;
import com.github.bakycoder.backtobed.item.returner.Returner;
import com.github.bakycoder.backtobed.platform.services.IModConfig;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;

import java.util.HashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = BackToBed.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeModConfig implements IModConfig {
    private static final ForgeConfigSpec.Builder RAW_BUILDER = new ForgeConfigSpec.Builder();
    private static final ForgeConfigBuilderWrapper BUILDER = new ForgeConfigBuilderWrapper(RAW_BUILDER);

    private static final ConfigEntry GLOBAL_CONFIG = new ConfigEntry(BUILDER, ReturnerConfigPresets.GLOBAL, true);
    private static final Map<String, ConfigEntry> RETURNER_CONFIGS = new HashMap<>();

    static {
        RETURNER_CONFIGS.put("magical_returner", new ConfigEntry(BUILDER, ReturnerConfigPresets.MAGICAL));
        RETURNER_CONFIGS.put("hells_returner", new ConfigEntry(BUILDER, ReturnerConfigPresets.HELLS));
    }

    public static final ForgeConfigSpec SPEC = RAW_BUILDER.build();

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
    public static void onLoad(final ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == SPEC) {
            GLOBAL_CONFIG.load();
            RETURNER_CONFIGS.values().forEach(ConfigEntry::load);
        }
    }

    @FunctionalInterface
    private interface ConfigValueGetter<T> {
        T get(ConfigEntry config, T globalDefault);
    }
}
