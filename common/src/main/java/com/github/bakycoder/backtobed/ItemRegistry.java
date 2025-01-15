package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.item.returners.effects.HellsEffectProvider;
import com.github.bakycoder.backtobed.item.returners.effects.MagicalEffectProvider;
import com.github.bakycoder.backtobed.item.returners.Returner;
import com.github.bakycoder.backtobed.platform.Services;
import com.github.bakycoder.backtobed.platform.services.IItemRegistrar;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

public class ItemRegistry {
    private static final IItemRegistrar REGISTRAR = Services.getItemRegistrar();

    public static final IItemRegistrar.IRegisteredItem<Item>
            MAGICAL_RETURNER = REGISTRAR.register("magical_returner", () -> new Returner(Level.OVERWORLD, MagicalEffectProvider::new)),
            HELLS_RETURNER = REGISTRAR.register("hells_returner", () -> new Returner(Level.NETHER, HellsEffectProvider::new));

    public static void acceptItemsToTab(CreativeModeTab.Output output) {
        output.accept(MAGICAL_RETURNER.get());
        output.accept(HELLS_RETURNER.get());
    }

    public static void registerItems() {}
}
