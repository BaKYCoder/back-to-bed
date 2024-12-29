package com.github.bakycoder.backtobed.utility;

import com.github.bakycoder.backtobed.Constants;
import net.minecraft.ChatFormatting;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Lang {
    private static final int MAX_TEXT_LINE_LENGTH = 28;

    public static List<Component> getFormatted(String key, ChatFormatting style, boolean newLineAtEnd) {
        List<Component> lines = new ArrayList<>();

        String text = I18n.get(key);

        String[] words = text.split(" ");
        StringBuilder currLine = new StringBuilder();

        if (!currLine.isEmpty()) {
            lines.add(Component.literal(currLine.toString()).withStyle(style));
        }

        for (String word : words) {
            if (currLine.length() + word.length() + 1 <= MAX_TEXT_LINE_LENGTH) {
                if (!currLine.isEmpty()) {
                    currLine.append(" ");
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
        return getFormatted(key, style, false);
    }

    public static MutableComponent getHighlighted(String key, String arg, ChatFormatting keyStyle, ChatFormatting argStyle) {
        String text = String.format(I18n.get(key), arg) ;
        String[] parts = text.split(arg, 2);

        return Component.literal(parts[0]).withStyle(keyStyle)
                .append(Component.literal(arg).withStyle(argStyle))
                .append(Component.literal(parts.length > 1 ? parts[1] : "").withStyle(keyStyle));
    }

    private static String getSeparatedArgs(String... args) {
        return String.join(".", args);
    }

    private static String generateItemLocalizationKey(ItemStack stack, String category, String suffix) {
        Item item = stack.getItem();
        String itemId = BuiltInRegistries.ITEM.getKey(item).getPath();
        return getSeparatedArgs("item", Constants.MOD_ID, itemId, category, suffix);
    }

    public static String getTooltipKey(TooltipKey keyType) {
        return getSeparatedArgs(Constants.MOD_ID, "tooltip", keyType.getKey());
    }

    public static String getItemTooltipKey(ItemStack stack, TooltipKey keyType) {
        return generateItemLocalizationKey(stack, "tooltip", keyType.getKey());
    }

    public static String getItemConditionKey(ItemStack stack, String condition) {
        return generateItemLocalizationKey(stack, "condition", condition);
    }
}
