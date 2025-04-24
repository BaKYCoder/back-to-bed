package com.github.bakycoder.backtobed.util;

public interface ConfigBuilder {
    ConfigBuilder comment(String comment);
    void push(String path);
    void pop();
    <T> ConfigValue<T> define(String name, T defaultValue);
    ConfigValue<Integer> defineInRange(String name, int defaultValue, int min, int max);
}
