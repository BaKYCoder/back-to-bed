package com.github.bakycoder.backtobed.utility.localization;

import com.github.bakycoder.backtobed.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class LangKeyGenerator {
    private static String getSeparatedArgs(String... args) {
        return String.join(".", args);
    }

    private static String generateItemLocalization(Item item, String category, String suffix) {
        String itemId = BuiltInRegistries.ITEM.getKey(item).getPath();
        return getSeparatedArgs(CategoryKeys.ITEM.getKey(), Constants.MOD_ID, itemId, category, suffix);
    }

    private static String generateItemLocalization(String itemId, String category, String suffix) {
        return getSeparatedArgs(CategoryKeys.ITEM.getKey(), Constants.MOD_ID, itemId, category, suffix);
    }

    public static String getTooltip(TooltipKeys lKey) {
        return getSeparatedArgs(Constants.MOD_ID, CategoryKeys.TOOLTIP.getKey(), lKey.getKey());
    }

    public static String getDimension(String dimension) {
        return getSeparatedArgs(Constants.MOD_ID, CategoryKeys.DIMENSION.getKey(), dimension);
    }

    public static String getItemTooltip(Item item, TooltipKeys keyType) {
        return generateItemLocalization(item, CategoryKeys.TOOLTIP.getKey(), keyType.getKey());
    }

    public static String getItemTooltip(String itemId, TooltipKeys keyType) {
        return generateItemLocalization(itemId, CategoryKeys.TOOLTIP.getKey(), keyType.getKey());
    }

    public static String getItemCondition(Item item, String condition) {
        return generateItemLocalization(item, CategoryKeys.CONDITION.getKey(), condition);
    }
}
