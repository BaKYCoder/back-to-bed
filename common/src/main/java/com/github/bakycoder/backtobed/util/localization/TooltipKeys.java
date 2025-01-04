package com.github.bakycoder.backtobed.util.localization;

public enum TooltipKeys {
    FUNCTIONALITY("functionality"),
    COOLDOWN("cooldown"),
    KEY_HOLD("key_hold"),
    DIMENSIONS("dimensions");

    private final String key;

    TooltipKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
