package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.config.ReturnerConfigFabric;
import com.github.bakycoder.backtobed.config.ReturnerConfigPresets;
import com.github.bakycoder.backtobed.item.returner.Returner;
import com.github.bakycoder.backtobed.platform.services.IModConfig;
import eu.midnightdust.lib.config.MidnightConfig;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.HashMap;
import java.util.Map;

public class FabricModConfig extends MidnightConfig implements IModConfig {

    @Entry
    public static ReturnerConfigFabric global = new ReturnerConfigFabric(ReturnerConfigPresets.GLOBAL);

    @Entry
    public static Map<String, ReturnerConfigFabric> returners = new HashMap<>();

    static {
        returners.put("magical_returner", new ReturnerConfigFabric(ReturnerConfigPresets.MAGICAL));
        returners.put("hells_returner", new ReturnerConfigFabric(ReturnerConfigPresets.HELLS));
        returners.put("mysterious_returner", new ReturnerConfigFabric(ReturnerConfigPresets.MYSTERIOUS));
    }

    @Override
    public int getReturnerDurationUsage(Returner returner) {
        String key = BuiltInRegistries.ITEM.getKey(returner).getPath();
        ReturnerConfigFabric config = returners.getOrDefault(key, global);
        int value = config.duration_usage;
        return value == -1 ? global.duration_usage : value;
    }

    @Override
    public int getReturnerCooldown(Returner returner) {
        String key = BuiltInRegistries.ITEM.getKey(returner).getPath();
        ReturnerConfigFabric config = returners.getOrDefault(key, global);
        int value = config.cooldown;
        return value == -1 ? global.cooldown : value;
    }

    @Override
    public boolean showReturnerTooltip(Returner returner) {
        String key = BuiltInRegistries.ITEM.getKey(returner).getPath();
        ReturnerConfigFabric config = returners.getOrDefault(key, global);
        return global.show_tooltip && config.show_tooltip;
    }
}
