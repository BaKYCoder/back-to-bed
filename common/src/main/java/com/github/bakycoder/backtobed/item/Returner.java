package com.github.bakycoder.backtobed.item;

import com.github.bakycoder.backtobed.utility.localization.LangHelper;
import com.github.bakycoder.backtobed.utility.localization.LangKeyGenerator;
import com.github.bakycoder.backtobed.utility.localization.TooltipKeys;
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

        String key = LangKeyGenerator.getItemTooltip(itemId, TooltipKeys.FUNCTIONALITY);
        components.addAll(LangHelper.getFormatted(key, ChatFormatting.GRAY, true, true));

        if(Screen.hasShiftDown()) {
            key = LangKeyGenerator.getItemTooltip(itemId, TooltipKeys.DIMENSIONS);
            components.addAll(LangHelper.getFormatted(key, ChatFormatting.DARK_GRAY));

            String dimName = allowedLevel.location().getPath().toUpperCase();
            components.add(Component.literal(' ' + dimName).withStyle(ChatFormatting.YELLOW));
        } else {
            key = LangKeyGenerator.getItemTooltip(itemId, TooltipKeys.KEY_HOLD);
            components.add(LangHelper.getHighlighted(key, "SHIFT", ChatFormatting.DARK_GRAY, ChatFormatting.WHITE));
        }
    }
}
