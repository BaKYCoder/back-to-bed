package com.github.bakycoder.backtobed.item;

import com.github.bakycoder.backtobed.util.localization.LangKeyGenerator;
import com.github.bakycoder.backtobed.util.localization.LangHelper;
import com.github.bakycoder.backtobed.util.localization.TooltipKeys;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;

import org.joml.Vector3f;

import java.util.List;

public class MagicalReturner extends Item {
    private static final int ITEM_DURATION_USAGE_TICKS = 40;

    private static final int ITEM_COOLDOWN_TICKS = ITEM_DURATION_USAGE_TICKS;

    public MagicalReturner(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, context, components, flag);

        String key = LangKeyGenerator.getItemTooltip(this, TooltipKeys.FUNCTIONALITY);
        components.addAll(LangHelper.getFormatted(key, ChatFormatting.GRAY, true, true));

        if (Screen.hasShiftDown()) {
            key = LangKeyGenerator.getTooltip(TooltipKeys.DIMENSIONS);
            components.addAll(LangHelper.getFormatted(key, ChatFormatting.DARK_GRAY));

            key = LangKeyGenerator.getDimension("overworld");
            components.addAll(LangHelper.getFormatted(key, ChatFormatting.DARK_GREEN, true, true));

            key = LangKeyGenerator.getTooltip(TooltipKeys.COOLDOWN);
            components.addAll(LangHelper.getFormatted(key, ChatFormatting.DARK_GRAY));

            key = LangKeyGenerator.getItemTooltip(this, TooltipKeys.COOLDOWN);
            components.addAll(LangHelper.getFormatted(key, ChatFormatting.DARK_PURPLE, true, false));
        } else {
            key = LangKeyGenerator.getTooltip(TooltipKeys.KEY_HOLD);
            components.add(LangHelper.getHighlighted(key, "SHIFT", ChatFormatting.DARK_GRAY, ChatFormatting.WHITE));
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

    private void handleUseInterrupt(String condition, ItemStack stack, ServerPlayer player, boolean addCooldown) {
        if (condition != null) {
            String key = LangKeyGenerator.getItemCondition(this, condition);
            player.displayClientMessage(Component.translatable(key), true);
        }

        player.stopUsingItem();

        if (addCooldown) player.getCooldowns().addCooldown(this, ITEM_COOLDOWN_TICKS);
    }

    @Override
    public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int remainingUseDuration) {
        super.onUseTick(level, entity, stack, remainingUseDuration);

        if (!(entity instanceof ServerPlayer player) || level.isClientSide()) return;

        int duration = this.getUseDuration(stack, entity) - remainingUseDuration;
        if (duration < ITEM_DURATION_USAGE_TICKS) return;

        if (player.getCommandSenderWorld().dimension() != Level.OVERWORLD) {
            handleUseInterrupt("teleport_for_overworld", stack, player, false);
            return;
        }

        BlockPos respawn = player.getRespawnPosition();

        if (respawn == null) {
            handleUseInterrupt("no_respawn_point", stack, player, false);
            return;
        }

        if (!(level.getBlockState(respawn).getBlock() instanceof BedBlock)) {
            handleUseInterrupt("no_access_to_bed", stack, player, false);
            return;
        }

        double destX = respawn.getX() + 0.5;
        double destY = respawn.getY() + 0.60;
        double destZ = respawn.getZ() + 0.5;

        if (player.getVehicle() != null) {
            Entity vehicle = player.getVehicle();
            player.stopRiding();

            player.server.execute(() -> {
                vehicle.teleportTo(destX, destY, destZ);
            });
        }

        player.teleportTo(destX, destY, destZ);
        level.playSound(null, respawn, SoundEvents.AMETHYST_CLUSTER_HIT, SoundSource.PLAYERS, 1.0F, 1.0F);

        DustColorTransitionOptions dust = new DustColorTransitionOptions(
                new Vector3f(0.0F, 1.0F, 1.0F),
                new Vector3f(1.0F, 0.0F, 1.0F),
                1f
        );

        ((ServerLevel) level).sendParticles(
                player, dust, true, destX, destY, destZ,
                85, .85D, .75D, .85D,
                .005D
        );

        handleUseInterrupt(null, stack, player, true);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }
}
