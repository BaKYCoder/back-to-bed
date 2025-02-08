package com.github.bakycoder.backtobed.util.lang;

public enum LangKeys {
    ITEM,
    TOOLTIP,
    DIMENSION,
    CONDITION,

    FUNCTIONALITY,
    COOLDOWN,
    KEY_HOLD,
    AVAILABILITY,
    FEATURE;

    public String getAsKey() {
        return name().toLowerCase();
    }
}
