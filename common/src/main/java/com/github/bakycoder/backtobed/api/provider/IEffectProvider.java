package com.github.bakycoder.backtobed.api.provider;

import com.github.bakycoder.backtobed.api.SoundEffect;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.phys.Vec3;

import java.util.List;

public interface IEffectProvider {
    ParticleOptions getParticles();

    List<SoundEffect> getSounds();

    default void applyEffects(ServerLevel rLevel, BlockPos rPosition, ServerPlayer player, Vec3 destination) {
        for (SoundEffect sound : getSounds()) {
            rLevel.playSound(null, rPosition, sound.event(), SoundSource.PLAYERS, sound.volume(), sound.pitch());
        }

        (rLevel).sendParticles(
                player, getParticles(), true, destination.x(), destination.y(), destination.z(),
                85, .85D, .75D, .85D,
                .005D
        );
    }
}
