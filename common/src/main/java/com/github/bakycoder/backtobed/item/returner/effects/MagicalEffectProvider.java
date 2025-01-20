package com.github.bakycoder.backtobed.item.returner.effects;

import com.github.bakycoder.backtobed.api.SoundEffect;
import com.github.bakycoder.backtobed.api.IEffectProvider;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;

import org.joml.Vector3f;

import java.util.List;

public class MagicalEffectProvider implements IEffectProvider {
    @Override
    public ParticleOptions getParticles() {
        return new DustColorTransitionOptions(
                new Vector3f(0.0F, 1.0F, 1.0F),
                new Vector3f(1.0F, 0.0F, 1.0F),
                1f
        );
    }

    @Override
    public List<SoundEffect> getSounds() {
        return List.of(
                new SoundEffect(SoundEvents.AMETHYST_CLUSTER_HIT, 1.0F, 1.0F)
        );
    }
}