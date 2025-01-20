package com.github.bakycoder.backtobed.item.returner;

import com.github.bakycoder.backtobed.api.provider.IEffectProvider;
import com.github.bakycoder.backtobed.api.provider.IFeatureInjector;
import com.github.bakycoder.backtobed.util.localization.LocalizationHelper;
import com.github.bakycoder.backtobed.util.localization.LocalizationKeyGenerator;
import com.github.bakycoder.backtobed.util.localization.LocalizationKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Supplier;

public class Returner extends Item {
    private static final String CLASS_NAME_AS_ID = Returner.class.getSimpleName().toLowerCase();
    private static final int ITEM_DURATION_USAGE_TICKS = 40;
    private static final int ITEM_COOLDOWN_TICKS = ITEM_DURATION_USAGE_TICKS;
    private final ResourceKey<Level> allowedDimension;
    private final IEffectProvider effectProvider;
    private final IFeatureInjector featureInjector;
    public Returner(ResourceKey<Level> allowedLevel, Supplier<IEffectProvider> effectProvider, Supplier<IFeatureInjector> featureInjector) {
        super(new Properties().stacksTo(1));
        this.allowedDimension = allowedLevel;
        this.effectProvider = effectProvider.get();
        this.featureInjector = featureInjector.get();
    }

    public Returner(ResourceKey<Level> allowedLevel, Supplier<IEffectProvider> effectProvider) {
        super(new Properties().stacksTo(1));
        this.allowedDimension = allowedLevel;
        this.effectProvider = effectProvider.get();
        this.featureInjector = null;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, context, components, flag);

        String key = LocalizationKeyGenerator.getItemTooltip(CLASS_NAME_AS_ID, LocalizationKeys.FUNCTIONALITY);
        components.addAll(LocalizationHelper.getFormatted(key, ChatFormatting.GRAY, true, true));

        if (Screen.hasShiftDown()) {
            key = LocalizationKeyGenerator.getItemTooltip(CLASS_NAME_AS_ID, LocalizationKeys.COOLDOWN);
            components.addAll(LocalizationHelper.getFormatted(key, ChatFormatting.GRAY, true, true));

            key = LocalizationKeyGenerator.getItemTooltip(CLASS_NAME_AS_ID, LocalizationKeys.DIMENSIONS);
            components.addAll(LocalizationHelper.getFormatted(key, ChatFormatting.DARK_GRAY));

            String dimName = allowedDimension.location().getPath();
            key = LocalizationKeyGenerator.getDimension(dimName);
            components.addAll(LocalizationHelper.getFormatted(key, ChatFormatting.YELLOW, true, false));

            if (featureInjector != null) {
                components.add(Component.empty());

                key = LocalizationKeyGenerator.getItemTooltip(CLASS_NAME_AS_ID, LocalizationKeys.FEATURE);
                components.addAll(LocalizationHelper.getFormatted(key, ChatFormatting.DARK_GRAY));

                key = LocalizationKeyGenerator.getItemTooltip(this, LocalizationKeys.FEATURE);
                components.addAll(LocalizationHelper.getFormatted(key, ChatFormatting.DARK_PURPLE, true, false));
            }
        } else {
            key = LocalizationKeyGenerator.getItemTooltip(CLASS_NAME_AS_ID, LocalizationKeys.KEY_HOLD);
            components.add(LocalizationHelper.getHighlighted(key, "SHIFT", ChatFormatting.DARK_GRAY, ChatFormatting.WHITE));
        }
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return InteractionResultHolder.consume(player.getItemInHand(usedHand));
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    private void interruptItemUsage(InterruptionReason reason, ServerPlayer player) {
        if (reason != null) {
            String key = LocalizationKeyGenerator.getItemCondition(CLASS_NAME_AS_ID, reason.getAsKey());
            player.displayClientMessage(Component.translatable(key), true);
        }

        player.stopUsingItem();
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, entity, stack, remainingUseDuration);

        if (!(entity instanceof ServerPlayer player) || level.isClientSide()) return;

        int usageDuration = this.getUseDuration(stack, entity) - remainingUseDuration;
        if (usageDuration < ITEM_DURATION_USAGE_TICKS) return;

        if (player.getCommandSenderWorld().dimension() != allowedDimension) {
            interruptItemUsage(InterruptionReason.FORBIDDEN_DIMENSION, player);
            return;
        }

        BlockPos respawnPosition = player.getRespawnPosition();

        if (respawnPosition == null) {
            interruptItemUsage(InterruptionReason.NO_RESPAWN_POINT, player);
            return;
        }

        ResourceKey<Level> respawnDimension = player.getRespawnDimension();
        ServerLevel respawnLevel = player.getServer().getLevel(respawnDimension);

        if (respawnLevel == null || !(respawnLevel.getBlockState(respawnPosition).getBlock() instanceof BedBlock)) {
            interruptItemUsage(InterruptionReason.NO_ACCESS_TO_BED, player);
            return;
        }

        Vec3 destination = new Vec3(
                respawnPosition.getX() + 0.5D,
                respawnPosition.getY() + 0.6D,
                respawnPosition.getZ() + 0.5D
        );

        if (featureInjector != null) {
            featureInjector.inject(player, respawnLevel, stack, destination);

            if (!featureInjector.handlesTeleportation()) {
                player.teleportTo(respawnLevel, destination.x(), destination.y(), destination.z(), player.getYRot(), player.getXRot());
            }
        } else {
            player.teleportTo(respawnLevel, destination.x(), destination.y(), destination.z(), player.getYRot(), player.getXRot());
        }

        effectProvider.applyEffects(respawnLevel, respawnPosition, player, destination);

        player.stopUsingItem();
        player.getCooldowns().addCooldown(stack.getItem(), ITEM_COOLDOWN_TICKS);
    }

    private enum InterruptionReason {
        FORBIDDEN_DIMENSION,
        NO_RESPAWN_POINT,
        NO_ACCESS_TO_BED;

        public String getAsKey() {
            return name().toLowerCase();
        }
    }
}
