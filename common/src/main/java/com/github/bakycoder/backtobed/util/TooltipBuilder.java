package com.github.bakycoder.backtobed.util;

import com.github.bakycoder.backtobed.util.lang.LangHelper;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class TooltipBuilder {
    private final List<Component> COMPONENTS;

    public TooltipBuilder() {
        this.COMPONENTS = new ArrayList<>();
    }

    public TooltipBuilder primary(String key, String arg, boolean spacing) {
        COMPONENTS.addAll(LangHelper.format(key, arg, ChatFormatting.GRAY, ChatFormatting.WHITE, spacing));
        return this;
    }

    public TooltipBuilder secondary(String key, boolean spacing) {
        COMPONENTS.addAll(LangHelper.format(key, ChatFormatting.GRAY, spacing));
        return this;
    }

    public void secondary(String key, String arg, boolean spacing) {
        COMPONENTS.addAll(LangHelper.format(key, arg, ChatFormatting.DARK_GRAY, ChatFormatting.WHITE, spacing));
    }

    public void highlighted(String key, boolean spacing) {
        COMPONENTS.addAll(LangHelper.format(key, ChatFormatting.YELLOW, spacing));
    }

    public void special(String key, boolean spacing) {
        COMPONENTS.addAll(LangHelper.format(key, ChatFormatting.DARK_PURPLE, spacing));
    }

    public TooltipBuilder empty() {
        COMPONENTS.add(Component.empty());
        return this;
    }

    public List<Component> build() {
        return COMPONENTS;
    }
}
