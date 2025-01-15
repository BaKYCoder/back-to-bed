package com.github.bakycoder.backtobed.item.returners.effects;

import com.github.bakycoder.backtobed.api.SoundEffect;
import com.github.bakycoder.backtobed.api.provider.IEffectProvider;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;

import org.joml.Vector3f;

import java.util.List;

public class HellsEffectProvider implements IEffectProvider {
    @Override
    public ParticleOptions getParticles() {
        return new DustColorTransitionOptions(
                new Vector3f(.95F, .95F, .95F),
                new Vector3f(.7F, 0.3F, .3F),
                1f
        );
    }

    @Override
    public List<SoundEffect> getSounds() {
        return List.of(
                new SoundEffect(SoundEvents.AMETHYST_BLOCK_RESONATE, 1.0F, .75F),
                new SoundEffect(SoundEvents.AMETHYST_BLOCK_HIT, 1.0F, 1F)
        );
    }
}
