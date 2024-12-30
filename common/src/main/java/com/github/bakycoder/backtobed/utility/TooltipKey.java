package com.github.bakycoder.backtobed.utility;

public enum TooltipKey {
    BEHAVIOR("behavior"),
    FEATURE("feature"),
    KEY_HOLD("key_hold"),
    DIMENSIONS("dimensions");

    private final String key;

    TooltipKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
