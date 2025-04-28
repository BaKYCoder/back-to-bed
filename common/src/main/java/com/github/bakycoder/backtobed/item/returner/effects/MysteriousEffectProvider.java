package com.github.bakycoder.backtobed.item.returner.effects;

import com.github.bakycoder.backtobed.api.IEffectProvider;
import com.github.bakycoder.backtobed.api.SoundEffect;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import org.joml.Vector3f;

import java.util.List;

public class MysteriousEffectProvider implements IEffectProvider {
    @Override
    public ParticleOptions getParticles() {
        return new DustColorTransitionOptions(
                new Vector3f(.35F, .18F, .35F),
                new Vector3f(.35F, .55F, .25F),
                1f
        );
    }

    @Override
    public List<SoundEffect> getSounds() {
        return List.of(
                new SoundEffect(SoundEvents.AMETHYST_BLOCK_RESONATE, 1.0F, .55F),
                new SoundEffect(SoundEvents.AMETHYST_BLOCK_HIT, 1.0F, 0.85F)
        );
    }
}
