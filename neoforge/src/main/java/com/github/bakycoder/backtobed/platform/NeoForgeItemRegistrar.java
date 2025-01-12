package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.platform.services.IItemRegistrar;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class NeoForgeItemRegistrar implements IItemRegistrar {
    public record NeoForgeRegisteredItem<T extends Item>(DeferredItem<T> item) implements IRegisteredItem<T> {
        @Override
        public T get() {
            return item.get();
        }
    }

    private static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(BackToBed.MOD_ID);

    @Override
    public <T extends Item> IRegisteredItem<T> register(String path, Supplier<T> item) {
        return new NeoForgeRegisteredItem<>(ITEMS.register(path, item));
    }

    public static void registerToEventBus(IEventBus eBus) {
        ITEMS.register(eBus);
    }
}
