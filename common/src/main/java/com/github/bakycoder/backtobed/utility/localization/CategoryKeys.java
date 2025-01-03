package com.github.bakycoder.backtobed.utility.localization;

public enum CategoryKeys {
    ITEM("item"),
    TOOLTIP("tooltip"),
    DIMENSION("dimension"),
    CONDITION("condition");

    private final String key;

    CategoryKeys(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
