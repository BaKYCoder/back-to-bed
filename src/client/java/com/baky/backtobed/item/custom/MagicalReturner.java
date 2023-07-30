package com.baky.backtobed.item.custom;

import net.minecraft.block.BedBlock;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DustColorTransitionParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MagicalReturner extends Item {
    // By using a scheduled executor, we can introduce a delay between teleporting the player and the ridden entity.
    // This helps prevent visual glitches or rendering issues that might occur if both are teleported instantly.
    private final ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

    public MagicalReturner(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);

        tooltip.add(Text.translatable("magical_returner.info1").formatted(Formatting.GRAY));
        tooltip.add(Text.translatable("magical_returner.info2").formatted(Formatting.GRAY));
        tooltip.add(Text.literal(""));

        if(Screen.hasShiftDown()) {
            tooltip.add(Text.translatable("magical_returner.pet_teleport1").formatted(Formatting.GREEN));
            tooltip.add(Text.translatable("magical_returner.pet_teleport2").formatted(Formatting.GREEN));
        }else {
            tooltip.add(Text.translatable("magical_returner.shift_press").formatted(Formatting.YELLOW));
        }
    }

    private void stopUsingItemWithCooldown(ServerPlayerEntity player) {
        player.stopUsingItem();
        player.getItemCooldownManager().set(this, 40);
    }

    @Override
    public void usageTick(World world, LivingEntity user, ItemStack stack, int remainingUseTicks) {
        super.usageTick(world, user, stack, remainingUseTicks);

        // Skip if it's client-side or not a player
        if(!(user instanceof ServerPlayerEntity player) || world.isClient()) {
            return;
        }

        int duration = this.getMaxUseTime(stack) - remainingUseTicks;

        if(duration < 40) {
            return;
        }

        if(!world.getRegistryKey().equals(World.OVERWORLD)) {
            player.sendMessage(Text.translatable("magical_returner.teleport_for_overworld"));
            stopUsingItemWithCooldown(player);
            return;
        }

        BlockPos respawnPos = player.getSpawnPointPosition();

        if(respawnPos == null) {
            player.sendMessage(Text.translatable("magical_returner.no_respawn_point"));
            stopUsingItemWithCooldown(player);
            return;
        }

        if(world.getBlockState(respawnPos).getBlock() instanceof BedBlock) {
            double destinationX = respawnPos.getX() + 0.5;
            double destinationY = respawnPos.getY() + 0.6D;
            double destinationZ = respawnPos.getZ() + 0.5;

            // Check if player is riding an entity
            if(player.getVehicle() != null) {
                // Get the entity being ridden
                Entity riddenEntity = player.getVehicle();

                // Stop the player from riding the entity
                player.stopRiding();

                // Delayed teleportation of the ridden entity
                executor.schedule(() -> {
                    assert riddenEntity != null;
                    riddenEntity.teleport(destinationX, destinationY, destinationZ);
                }, 100, TimeUnit.MILLISECONDS);

                player.teleport(destinationX, destinationY, destinationZ);
            }else {
                player.teleport(destinationX, destinationY, destinationZ);
            }

            world.playSound(null, respawnPos, SoundEvents.BLOCK_AMETHYST_CLUSTER_HIT, SoundCategory.PLAYERS, 1.0F, 1.0F);

            DustColorTransitionParticleEffect dustColorTransitionParticleEffect = new DustColorTransitionParticleEffect(
                    new Vec3f(0.0f, 1.0f, 1.0f),
                    new Vec3f(1.0f, 0.0f, 1.0f),
                    1.0f
            );

            ServerWorld serverWorld = (ServerWorld) world;

            serverWorld.spawnParticles(player, dustColorTransitionParticleEffect, true, respawnPos.getX() + 0.5, respawnPos.getY() + 0.6D, respawnPos.getZ() + 0.5, 85, 0.85D, 0.75D, .85D, 0.005D);

            stopUsingItemWithCooldown(player);
         }else {
            player.sendMessage(Text.translatable("magical_returner.no_access_to_bed"));
            stopUsingItemWithCooldown(player);
        }
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 72000; // This is how long the player can hold right-click (1 hour)
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW; // This makes the player perform the bow drawing animation while holding right-click
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        user.setCurrentHand(hand);
        return TypedActionResult.consume(user.getStackInHand(hand));
    }
}
