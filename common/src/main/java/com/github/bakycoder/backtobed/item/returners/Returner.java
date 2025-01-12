package com.github.bakycoder.backtobed.item.returners;

import com.github.bakycoder.backtobed.util.localization.LocalizationHelper;
import com.github.bakycoder.backtobed.util.localization.LocalizationKeyGenerator;
import com.github.bakycoder.backtobed.util.localization.LocalizationKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class Returner extends Item {
    private final ResourceKey<Level> allowedLevel;

    public Returner(ResourceKey<Level> allowedLevel) {
        super(new Properties().stacksTo(1));
        this.allowedLevel = allowedLevel;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, context, components, flag);

        String itemId = this.getClass().getSimpleName().toLowerCase();

        String key = LocalizationKeyGenerator.getItemTooltip(itemId, LocalizationKeys.FUNCTIONALITY);
        components.addAll(LocalizationHelper.getFormatted(key, ChatFormatting.GRAY, true, true));

        if(Screen.hasShiftDown()) {
            key = LocalizationKeyGenerator.getItemTooltip(itemId, LocalizationKeys.COOLDOWN);
            components.addAll(LocalizationHelper.getFormatted(key, ChatFormatting.GRAY, true, true));

            key = LocalizationKeyGenerator.getItemTooltip(itemId, LocalizationKeys.DIMENSIONS);
            components.addAll(LocalizationHelper.getFormatted(key, ChatFormatting.DARK_GRAY));

            String dimName = allowedLevel.location().getPath();
            key = LocalizationKeyGenerator.getDimension(dimName);
            components.addAll(LocalizationHelper.getFormatted(key, ChatFormatting.YELLOW, true, false));
        } else {
            key = LocalizationKeyGenerator.getItemTooltip(itemId, LocalizationKeys.KEY_HOLD);
            components.add(LocalizationHelper.getHighlighted(key, "SHIFT", ChatFormatting.DARK_GRAY, ChatFormatting.WHITE));
        }
    }
}
