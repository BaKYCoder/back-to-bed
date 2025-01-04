package com.github.bakycoder.backtobed.item;

import com.github.bakycoder.backtobed.util.localization.LangKeyGenerator;
import com.github.bakycoder.backtobed.util.localization.LangHelper;
import com.github.bakycoder.backtobed.util.localization.TooltipKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
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
import org.joml.Vector3f;

import java.util.List;

public class HellsReturner extends Item {
    private static final int ITEM_DURATION_USAGE_TICKS = 40;

    private static final int ITEM_COOLDOWN_TICKS = ITEM_DURATION_USAGE_TICKS;

    public HellsReturner(Properties properties) {
        super(properties);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> components, TooltipFlag flag) {
        super.appendHoverText(stack, context, components, flag);

        String key = LangKeyGenerator.getItemTooltip(stack.getItem(), TooltipKeys.FUNCTIONALITY);
        components.addAll(LangHelper.getFormatted(key, ChatFormatting.GRAY, true, true));

        if (Screen.hasShiftDown()) {
            key = LangKeyGenerator.getTooltip(TooltipKeys.DIMENSIONS);
            components.addAll(LangHelper.getFormatted(key, ChatFormatting.DARK_GRAY));

            key = LangKeyGenerator.getDimension("nether");
            components.addAll(LangHelper.getFormatted(key, ChatFormatting.DARK_RED, true, false));
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
            String key = LangKeyGenerator.getItemCondition(stack.getItem(), condition);
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

        if (player.getCommandSenderWorld().dimension() != Level.NETHER) {
            handleUseInterrupt("teleport_for_nether", stack, player, false);
            return;
        }

        BlockPos respawn = player.getRespawnPosition();

        if (respawn == null) {
            handleUseInterrupt("no_respawn_point", stack, player, false);
            return;
        }

        ResourceKey<Level> dimension = player.getRespawnDimension();
        ServerLevel dimWorld = player.getServer().getLevel(dimension);

        if (dimWorld == null || !(dimWorld.getBlockState(respawn).getBlock() instanceof BedBlock)) {
            handleUseInterrupt("no_access_to_bed", stack, player, false);
            return;
        }

        double destX = respawn.getX() + 0.5;
        double destY = respawn.getY() + 0.60;
        double destZ = respawn.getZ() + 0.5;

        player.teleportTo(dimWorld, destX, destY, destZ, player.getYRot(), player.getXRot());

        dimWorld.playSound(null, respawn, SoundEvents.AMETHYST_BLOCK_RESONATE, SoundSource.PLAYERS, 1.0F, .75F);
        dimWorld.playSound(null, respawn, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.PLAYERS, 1.0F, 1F);

        DustColorTransitionOptions dust = new DustColorTransitionOptions(
                new Vector3f(.95F, .95F, .95F),
                new Vector3f(.7F, 0.3F, .3F),
                1f
        );

        (dimWorld).sendParticles(
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
