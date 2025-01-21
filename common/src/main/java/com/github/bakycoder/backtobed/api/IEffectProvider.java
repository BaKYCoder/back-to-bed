package com.github.bakycoder.backtobed.api;

import com.jcraft.jorbis.Block;
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

    default void applyEffects(ServerLevel respawnLevel, ServerPlayer player, Vec3 destination) {
        ParticleOptions particles = getParticles();
        if (particles == null) return;

        respawnLevel.sendParticles(
                player, getParticles(), true, destination.x(), destination.y(), destination.z(),
                85, .85D, .75D, .85D,
                .005D
        );

        List<SoundEffect> sounds = getSounds();
        if (sounds == null || sounds.isEmpty()) return;

        for (SoundEffect sound : sounds) {
            respawnLevel.playSound(null, BlockPos.containing(destination), sound.event(), SoundSource.PLAYERS, sound.volume(), sound.pitch());
        }
    }
}
