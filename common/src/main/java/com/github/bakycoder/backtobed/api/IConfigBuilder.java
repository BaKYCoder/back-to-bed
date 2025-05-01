package com.github.bakycoder.backtobed.api;

public interface IConfigBuilder {
    IConfigBuilder comment(String comment);
    void push(String path);
    void pop();
    <T> IConfigValue<T> define(String name, T defaultValue);
    IConfigValue<Integer> defineInRange(String name, int defaultValue, int min, int max);
}
