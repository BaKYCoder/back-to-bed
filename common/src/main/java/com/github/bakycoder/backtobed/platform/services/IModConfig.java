package com.github.bakycoder.backtobed.platform.services;

import com.github.bakycoder.backtobed.item.returner.Returner;

public interface IModConfig {
    int getReturnerDurationUsage(Returner returner);
    int getReturnerCooldown(Returner returner);
    boolean showReturnerTooltip(Returner returner);
}
