package com.github.bakycoder.backtobed.item.custom;

import com.github.bakycoder.backtobed.utils.TooltipUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

/**
 * The MagicalReturner class represents an item that allows players to teleport to their respawn point.
 */
public class MagicalReturner extends Item {
    /**
     * Representing the name of the item.
     */
    public static final String ITEM_NAME = "magical_returner";

    private static final int ITEM_DURATION_USAGE_TICKS = 40;
    /**
     * Representing the cooldown duration of the item in ticks.
     */
    private static final int ITEM_COOLDOWN_TICKS = ITEM_DURATION_USAGE_TICKS;
    /**
     * The executor service for scheduling asynchronous tasks.
     */
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    /**
     * Constructs a MagicalReturner item with specified properties.
     */
    public MagicalReturner() {
        super(new Properties()
                .stacksTo(1));
    }

    /**
     * Appends hover text to the item's tooltip.
     *
     * @param stack           The ItemStack being hovered over.
     * @param tooltipContext  Context information for the tooltip.
     * @param componentList   The list of tooltip components to append to.
     * @param tooltipFlag     The flag indicating advanced tooltip rendering options.
     */
    @Override
    public void appendHoverText(@NotNull ItemStack stack, @NotNull TooltipContext tooltipContext, @NotNull List<Component> componentList, @NotNull TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, tooltipContext, componentList, tooltipFlag);

        // Add tooltip text about the item's behavior
        componentList.addAll(TooltipUtils.formatComponent("item.backtobed.magical_returner.tooltip.behavior", ChatFormatting.GRAY, true));

        // Add additional tooltip text if shift key is held down
        if (Screen.hasShiftDown())
            componentList.addAll(TooltipUtils.formatComponent("item.backtobed.magical_returner.tooltip.further", ChatFormatting.GREEN));
        else
            componentList.add(TooltipUtils.highlightComponentArg("backtobed.tooltip.key_hold", "SHIFT", ChatFormatting.YELLOW, ChatFormatting.GOLD));
    }


}
