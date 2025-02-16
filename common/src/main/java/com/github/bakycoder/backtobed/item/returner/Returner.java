package com.github.bakycoder.backtobed.item.returner;

import com.github.bakycoder.backtobed.api.IEffectProvider;
import com.github.bakycoder.backtobed.api.IFeatureInjector;
import com.github.bakycoder.backtobed.platform.Services;
import com.github.bakycoder.backtobed.platform.services.IModConfig;
import com.github.bakycoder.backtobed.util.TooltipBuilder;
import com.github.bakycoder.backtobed.util.lang.LangKeyGenerator;
import com.github.bakycoder.backtobed.util.lang.LangKeys;
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
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Supplier;

public class Returner extends Item {
    private static final String CLASS_NAME_AS_ID = Returner.class.getSimpleName().toLowerCase();
    private static final IModConfig MOD_CONFIG = Services.getModConfig();

    private final ChatFormatting ITEM_COLOR_NAME;
    private final ResourceKey<Level> ALLOWED_LEVEL;
    private final IEffectProvider EFFECT_PROVIDER;
    private final IFeatureInjector FEATURE_INJECTOR;

    public Returner(Properties properties, ChatFormatting itemColorName, ResourceKey<Level> level, Supplier<IEffectProvider> provider, Supplier<IFeatureInjector> injector) {
        super(properties);

        this.ITEM_COLOR_NAME = itemColorName;
        this.ALLOWED_LEVEL = level;
        this.EFFECT_PROVIDER = (provider != null) ? provider.get() : null;
        this.FEATURE_INJECTOR = (injector != null) ? injector.get() : null;
    }

    public Returner(ChatFormatting itemColorName, ResourceKey<Level> level, Supplier<IEffectProvider> provider, Supplier<IFeatureInjector> injector) {
        this(new Item.Properties().stacksTo(1), itemColorName, level, provider, injector);
    }

    public Returner(ChatFormatting itemColorName, ResourceKey<Level> level, Supplier<IEffectProvider> provider) {
        this(itemColorName, level, provider, null);
    }

    private static String resolveLangKey(LangKeys key) {
        return LangKeys.LANG_KEY_CACHE.computeIfAbsent(key, lk -> LangKeyGenerator.getItemTooltip(CLASS_NAME_AS_ID, lk));
    }

    @Override
    public Component getName(ItemStack pStack) {
        return Component.translatable(this.getDescriptionId(pStack)).withStyle(this.ITEM_COLOR_NAME);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, context, components, flag);

        if (!MOD_CONFIG.showReturnerTooltip(this)) return;

        TooltipBuilder builder = new TooltipBuilder();
        float durationUsage = MOD_CONFIG.getReturnerDurationUsage(this) / 20f;

        builder
                .primary(resolveLangKey(LangKeys.FUNCTIONALITY), String.format("%.1f", durationUsage), true)
                .empty();

        if (!Screen.hasShiftDown()) {
            builder.secondary(resolveLangKey(LangKeys.KEY_HOLD), "SHIFT", false);
            components.addAll(builder.build());
            return;
        }

        float cooldown = MOD_CONFIG.getReturnerCooldown(this) / 20f;
        String levelKey = ALLOWED_LEVEL.location().getPath();

        builder
                .primary(resolveLangKey(LangKeys.COOLDOWN), String.format("%.1f", cooldown), true)
                .empty()
                .secondary(resolveLangKey(LangKeys.AVAILABILITY), false)
                .highlighted(LangKeyGenerator.getDimension(levelKey), true);

        if (FEATURE_INJECTOR != null) {
            builder
                    .empty()
                    .secondary(resolveLangKey(LangKeys.FEATURE), false)
                    .special(LangKeyGenerator.getItemTooltip(this, LangKeys.FEATURE), true);
        }

        components.addAll(builder.build());
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
        String key = LangKeyGenerator.getItemCondition(CLASS_NAME_AS_ID, reason.getAsKey());
        player.displayClientMessage(Component.translatable(key), true);

        player.stopUsingItem();
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, entity, stack, remainingUseDuration);

        if (!(entity instanceof ServerPlayer player) || level.isClientSide()) return;

        int usageDuration = this.getUseDuration(stack, entity) - remainingUseDuration;
        if (usageDuration < MOD_CONFIG.getReturnerDurationUsage(this)) return;

        if (player.getCommandSenderWorld().dimension() != ALLOWED_LEVEL) {
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

        if (FEATURE_INJECTOR != null) {
            FEATURE_INJECTOR.inject(player, respawnLevel, stack, destination);
        }

        if (FEATURE_INJECTOR == null || !FEATURE_INJECTOR.handlesTeleportation()) {
            player.teleportTo(respawnLevel, destination.x(), destination.y(), destination.z(), player.getYRot(), player.getXRot());
        }

        EFFECT_PROVIDER.applyEffects(respawnLevel, player, destination);

        player.stopUsingItem();
        player.getCooldowns().addCooldown(stack.getItem(), MOD_CONFIG.getReturnerCooldown(this));
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
