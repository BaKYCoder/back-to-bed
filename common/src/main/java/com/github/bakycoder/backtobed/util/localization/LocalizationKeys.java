package com.github.bakycoder.backtobed.util.localization;

public enum LocalizationKeys {
    ITEM("item"),
    TOOLTIP("tooltip"),
    DIMENSION("dimension"),
    CONDITION("condition"),

    FUNCTIONALITY("functionality"),
    COOLDOWN("cooldown"),
    KEY_HOLD("key_hold"),
    DIMENSIONS("dimensions");

    private final String key;

    LocalizationKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
