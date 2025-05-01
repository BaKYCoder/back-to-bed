package com.github.bakycoder.backtobed.item.returner.effects;

import com.github.bakycoder.backtobed.api.IEffectProvider;
import com.github.bakycoder.backtobed.api.SoundEffect;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.sounds.SoundEvents;
import org.joml.Vector3f;

import java.util.List;

public class TresrealmEffectProvider implements IEffectProvider {
    @Override
    public ParticleOptions getParticles() {
        return new DustColorTransitionOptions(
                new Vector3f(.50F, .38F, .35F),
                new Vector3f(.53F, .73F, .45F),
                1f
        );
    }

    @Override
    public List<SoundEffect> getSounds() {
        return List.of(
                new SoundEffect(SoundEvents.AMETHYST_CLUSTER_STEP, 1.0F, .75F),
                new SoundEffect(SoundEvents.AMETHYST_CLUSTER_HIT, 1.0F, 1F)
        );
    }
}
