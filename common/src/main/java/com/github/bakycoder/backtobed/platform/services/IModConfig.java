package com.github.bakycoder.backtobed.platform.services;

import com.github.bakycoder.backtobed.item.returner.Returner;

public interface IModConfig {
    int getDefaultReturnerDurationUsage();
    int getDefaultReturnerCooldown();
    int getSpecificReturnerDurationUsage(Returner returner);
    int getSpecificReturnerCooldown(Returner returner);
}
