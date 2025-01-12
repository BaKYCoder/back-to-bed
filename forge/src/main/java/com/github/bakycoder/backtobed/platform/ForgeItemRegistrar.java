package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.platform.services.IItemRegistrar;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ForgeItemRegistrar implements IItemRegistrar {
    public record ForgeRegisteredItem<T extends Item>(RegistryObject<T> item) implements IRegisteredItem<T> {
        @Override
            public T get() {
                return item.get();
            }
        }

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BackToBed.MOD_ID);

    @Override
    public <T extends Item> IRegisteredItem<T> register(String path, Supplier<T> item) {
        return new ForgeRegisteredItem<>(ITEMS.register(path, item));
    }

    public static void registerToEventBus(IEventBus eBus) {
        ITEMS.register(eBus);
    }
}
