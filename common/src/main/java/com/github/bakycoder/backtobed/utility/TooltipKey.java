package com.github.bakycoder.backtobed.utility;

public enum TooltipKey {
    BEHAVIOR("behavior"),
    FURTHER("further"),
    KEY_HOLD("key_hold");

    private final String key;

    TooltipKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
