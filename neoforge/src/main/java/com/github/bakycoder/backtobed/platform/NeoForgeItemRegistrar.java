package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.platform.services.IItemRegistrar;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeItemRegistrar implements IItemRegistrar {
    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BackToBed.MOD_ID);

    @Override
    public <T extends Item> void register(String path, Supplier<T> item) {
        ITEMS.register(path, item);
    }

    public static void registerToEventBus(IEventBus eBus) {
        ITEMS.register(eBus);
    }
}
