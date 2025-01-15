package com.github.bakycoder.backtobed.api;

import net.minecraft.sounds.SoundEvent;

public record SoundEffect(SoundEvent event, float volume, float pitch) {
}
