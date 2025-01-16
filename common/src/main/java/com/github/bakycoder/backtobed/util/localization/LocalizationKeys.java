package com.github.bakycoder.backtobed.util.localization;

public enum LocalizationKeys {
    ITEM,
    TOOLTIP,
    DIMENSION,
    CONDITION,

    FUNCTIONALITY,
    COOLDOWN,
    KEY_HOLD,
    DIMENSIONS,
    FEATURE;

    public String getAsKey() {
        return name().toLowerCase();
    }
}
