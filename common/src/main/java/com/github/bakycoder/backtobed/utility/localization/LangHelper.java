package com.github.bakycoder.backtobed.utility.localization;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;

public class LangHelper {
    private static final int MAX_TEXT_LINE_LENGTH = 31;

    public static List<Component> getFormatted(String key, ChatFormatting style, boolean startWithSpace, boolean newLineAtEnd) {
        List<Component> lines = new ArrayList<>();

        String text = I18n.get(key);

        String[] words = text.split(" ");
        StringBuilder currLine = new StringBuilder();

        for (String word : words) {
            if (currLine.length() + word.length() + 1 <= MAX_TEXT_LINE_LENGTH) {
                if (!currLine.isEmpty() || startWithSpace) {
                    currLine.append(" ");
                    startWithSpace = false;
                }
                currLine.append(word);
            } else {
                lines.add(Component.literal(currLine.toString()).withStyle(style));
                currLine = new StringBuilder(word);
            }
        }

        if (!currLine.isEmpty()) {
            lines.add(Component.literal(currLine.toString()).withStyle(style));
        }

        if (newLineAtEnd) {
            lines.add(Component.literal(""));
        }

        return lines;
    }

    public static List<Component> getFormatted(String key, ChatFormatting style) {
        return getFormatted(key, style, false, false);
    }

    public static MutableComponent getHighlighted(String key, String arg, ChatFormatting keyStyle, ChatFormatting argStyle) {
        String text = String.format(I18n.get(key), arg) ;
        String[] parts = text.split(arg, 2);

        return Component.literal(parts[0]).withStyle(keyStyle)
                .append(Component.literal(arg).withStyle(argStyle))
                .append(Component.literal(parts.length > 1 ? parts[1] : "").withStyle(keyStyle));
    }
}
