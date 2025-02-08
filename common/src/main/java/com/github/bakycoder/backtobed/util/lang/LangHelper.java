package com.github.bakycoder.backtobed.util.lang;

import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.network.chat.Component;

import java.util.ArrayList;
import java.util.List;

public class LangHelper {
    private static final int MAX_TEXT_LINE_LENGTH = 31;

    public static List<Component> split(String text, ChatFormatting format) {
        List<Component> lines = new ArrayList<>();

        String[] words = text.split(" ");
        StringBuilder currLine = new StringBuilder();

        for (String word : words) {
            if (currLine.length() + word.length() + 1 <= MAX_TEXT_LINE_LENGTH) {
                if (!currLine.isEmpty())
                    currLine.append(" ");
                currLine.append(word);
            } else {
                lines.add(Component.literal(currLine.toString()).withStyle(format));
                currLine = new StringBuilder(word);
            }
        }

        if (!currLine.isEmpty()) {
            lines.add(Component.literal(currLine.toString()).withStyle(format));
        }

        return lines;
    }

    public static List<Component> format(String key, ChatFormatting format) {
        String text = I18n.get(key);
        return split(text, format);
    }

    public static List<Component> format(String key, String arg, ChatFormatting keyFormat, ChatFormatting argFormat) {
        String text = String.format(I18n.get(key), arg);

        List<Component> splits = split(text, keyFormat);

        for(int i = 0; i < splits.size(); i++) {
            String split = splits.get(i).getString();

            String[] parts = split.split(arg, 2);
            if(parts.length == 2) {
                splits.set(i, Component.literal(parts[0]).withStyle(keyFormat)
                        .append(Component.literal(arg).withStyle(argFormat))
                        .append(Component.literal(parts[1]).withStyle(keyFormat)));
                break;
            }
        }

        return splits;
    }
}
