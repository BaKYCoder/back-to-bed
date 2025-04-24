package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.config.ReturnerConfigFabric;
import com.github.bakycoder.backtobed.item.returner.Returner;
import com.github.bakycoder.backtobed.platform.services.IModConfig;

public class FabricModConfig implements IModConfig {

    @Override
    public int getReturnerDurationUsage(Returner returner) {
        return ReturnerConfigFabric.duration_usage;
    }

    @Override
    public int getReturnerCooldown(Returner returner) {
        return ReturnerConfigFabric.cooldown;
    }

    @Override
    public boolean showReturnerTooltip(Returner returner) {
        return ReturnerConfigFabric.show_tooltip;
    }
}
