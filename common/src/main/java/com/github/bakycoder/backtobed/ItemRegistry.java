package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.util.ItemHolder;
import com.github.bakycoder.backtobed.item.returners.Returner;
import com.github.bakycoder.backtobed.platform.Services;
import com.github.bakycoder.backtobed.platform.services.IItemRegistrar;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemRegistry {
    private static final IItemRegistrar REGISTRAR = Services.getItemRegistrar();

    public static final List<ItemHolder> RETURNERS = List.of(
            new ItemHolder("magical_returner", () -> new Returner(Level.OVERWORLD)),
            new ItemHolder("hells_returner", () -> new Returner(Level.NETHER))
    );

    public static void registerItems() {
        for (ItemHolder holder : RETURNERS) {
            REGISTRAR.register(holder.path(), holder.item());
        }
    }
}
