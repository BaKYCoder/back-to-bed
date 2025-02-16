package com.github.bakycoder.backtobed.util.lang;

import java.util.EnumMap;
import java.util.Map;

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

    public static final Map<LangKeys, String> LANG_KEY_CACHE = new EnumMap<>(LangKeys.class);

    public String getAsKey() {
        return name().toLowerCase();
    }
}
