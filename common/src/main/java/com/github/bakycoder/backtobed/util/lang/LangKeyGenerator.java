package com.github.bakycoder.backtobed.util.lang;

import com.github.bakycoder.backtobed.BackToBed;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

public class LangKeyGenerator {
    private static String getSeparatedArgs(String... args) {
        return String.join(".", args);
    }

    private static String generateItemLocalization(Item item, String category, String suffix) {
        String itemId = BuiltInRegistries.ITEM.getKey(item).getPath();
        return getSeparatedArgs(LangKeys.ITEM.getAsKey(), BackToBed.MOD_ID, itemId, category, suffix);
    }

    private static String generateItemLocalization(String itemId, String category, String suffix) {
        return getSeparatedArgs(LangKeys.ITEM.getAsKey(), BackToBed.MOD_ID, itemId, category, suffix);
    }

    public static String getTooltip(LangKeys lKey) {
        return getSeparatedArgs(BackToBed.MOD_ID, LangKeys.TOOLTIP.getAsKey(), lKey.getAsKey());
    }

    public static String getDimension(String dimension) {
        return getSeparatedArgs(BackToBed.MOD_ID, LangKeys.DIMENSION.getAsKey(), dimension);
    }

    public static String getItemTooltip(Item item, LangKeys lKey) {
        return generateItemLocalization(item, LangKeys.TOOLTIP.getAsKey(), lKey.getAsKey());
    }

    public static String getItemTooltip(String itemId, LangKeys lKey) {
        return generateItemLocalization(itemId, LangKeys.TOOLTIP.getAsKey(), lKey.getAsKey());
    }

    public static String getItemCondition(String itemId, String condition) {
        return generateItemLocalization(itemId, LangKeys.CONDITION.getAsKey(), condition);
    }
}
