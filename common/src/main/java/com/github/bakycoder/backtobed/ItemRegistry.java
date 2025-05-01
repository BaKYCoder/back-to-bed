package com.github.bakycoder.backtobed;

import com.github.bakycoder.backtobed.item.returner.effects.HellsEffectProvider;
import com.github.bakycoder.backtobed.item.returner.effects.MagicalEffectProvider;
import com.github.bakycoder.backtobed.item.returner.Returner;
import com.github.bakycoder.backtobed.item.returner.effects.MysteriousEffectProvider;
import com.github.bakycoder.backtobed.item.returner.effects.TresrealmEffectProvider;
import com.github.bakycoder.backtobed.item.returner.features.MountFeatureInjector;
import com.github.bakycoder.backtobed.platform.Services;
import com.github.bakycoder.backtobed.platform.services.IItemRegistrar;
import net.minecraft.network.chat.TextColor;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;

import java.util.List;

public class ItemRegistry {
    private static final IItemRegistrar REGISTRAR = Services.getItemRegistrar();

    public static final IItemRegistrar.IRegisteredItem<Item>
            MAGICAL_RETURNER = REGISTRAR.register("magical_returner", () -> new Returner(TextColor.fromRgb(0x84FFF0), List.of(Level.OVERWORLD), MagicalEffectProvider::new, MountFeatureInjector::new)),
            HELLS_RETURNER = REGISTRAR.register("hells_returner", () -> new Returner(TextColor.fromRgb(0xFFFCB4), List.of(Level.NETHER), HellsEffectProvider::new)),
            MYSTERIOUS_RETURNER = REGISTRAR.register("mysterious_returner", () -> new Returner(TextColor.fromRgb(0xC6FCFF), List.of(Level.END), MysteriousEffectProvider::new)),
            TRESREALM_RETURNER = REGISTRAR.register("tresrealm_returner", () -> new Returner(TextColor.fromRgb(0xFFDBCA), List.of(Level.OVERWORLD, Level.NETHER, Level.END), TresrealmEffectProvider::new));

    public static void acceptItemsToTab(CreativeModeTab.Output output) {
        output.accept(MAGICAL_RETURNER.get());
        output.accept(HELLS_RETURNER.get());
        output.accept(MYSTERIOUS_RETURNER.get());
        output.accept(TRESREALM_RETURNER.get());
    }

    public static void registerItems() {
    }
}
