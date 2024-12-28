package com.github.bakycoder.backtobed.util;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

import java.util.ArrayList;
import java.util.List;

public class TooltipUtil {
    private static final int MAX_TEXT_LINE_LENGTH = 28;

    public static List<Component> formatComponent(String key, ChatFormatting style, boolean newLineAtEnd) {
        List<Component> tooltipLines = new ArrayList<>();

        String text = I18n.get(key);

        String[] words = text.split(" ");
        StringBuilder currentLine = new StringBuilder();

        if (!currentLine.isEmpty()) {
            tooltipLines.add(Component.literal(currentLine.toString()).withStyle(style));
        }

        for (String word : words) {
            if (currentLine.length() + word.length() + 1 <= MAX_TEXT_LINE_LENGTH) {
                if (!currentLine.isEmpty()) {
                    currentLine.append(" ");
                }
                currentLine.append(word);
            } else {
                tooltipLines.add(Component.literal(currentLine.toString()).withStyle(style));
                currentLine = new StringBuilder(word);
            }
        }

        if (!currentLine.isEmpty()) {
            tooltipLines.add(Component.literal(currentLine.toString()).withStyle(style));
        }

        if (newLineAtEnd) {
            tooltipLines.add(Component.literal(""));
        }

        return tooltipLines;
    }

    public static List<Component> formatComponent(String key, ChatFormatting style) {
        return formatComponent(key, style, false);
    }

    public static MutableComponent highlightComponentArg(String key, String arg, ChatFormatting keyStyle, ChatFormatting argStyle) {
        String text = I18n.get(key);

        MutableComponent textComponent = Component.literal(String.format(text, arg));

        int argStart = textComponent.getString().indexOf(arg);

        MutableComponent argComponent = Component.literal(arg).withStyle(argStyle);

        MutableComponent textBeforeArg = Component.literal(textComponent.getString().substring(0, argStart)).withStyle(keyStyle);
        MutableComponent textAfterArg = Component.literal(textComponent.getString().substring(argStart + arg.length())).withStyle(keyStyle);

        return textBeforeArg.append(argComponent).append(textAfterArg);
    }
}
