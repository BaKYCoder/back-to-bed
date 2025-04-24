package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.config.ForgeConfigBuilderWrapper;
import com.github.bakycoder.backtobed.config.ReturnerConfig;
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

    private static final ReturnerConfig GLOBAL_CONFIG = new ReturnerConfig(BUILDER, 60, 50, true);
    private static final Map<String, ReturnerConfig> RETURNER_CONFIGS = new HashMap<>();

    static {
        RETURNER_CONFIGS.put("magical_returner", new ReturnerConfig("magical_returner", BUILDER, -1, -1, true));
        RETURNER_CONFIGS.put("hells_returner", new ReturnerConfig("hells_returner", BUILDER, 70, 60, true));
    }

    public static final ForgeConfigSpec SPEC = RAW_BUILDER.build();

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
    public static void onLoad(final ModConfigEvent.Loading event) {
        if (event.getConfig().getSpec() == SPEC) {
            GLOBAL_CONFIG.load();
            RETURNER_CONFIGS.values().forEach(ReturnerConfig::load);
        }
    }

    @FunctionalInterface
    private interface ConfigValueGetter<T> {
        T get(ReturnerConfig config, T globalDefault);
    }
}
