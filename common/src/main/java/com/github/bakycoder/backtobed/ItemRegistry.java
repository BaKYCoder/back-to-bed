package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.item.returners.Returner;
import com.github.bakycoder.backtobed.platform.Services;
import com.github.bakycoder.backtobed.platform.services.IItemRegistrar;
import net.minecraft.world.level.Level;

public class ItemRegistry {
    private static final IItemRegistrar REGISTRAR = Services.getItemRegistrar();

    public static final Object
            MAGICAL_RETURNER = REGISTRAR.register("magical_returner", () -> new Returner(Level.OVERWORLD)),
            HELLS_RETURNER = REGISTRAR.register("hells_returner", () -> new Returner(Level.NETHER));

    public static void registerItems() {}
}
