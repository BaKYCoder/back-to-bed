package com.github.bakycoder.backtobed.platform;

import com.github.bakycoder.backtobed.BackToBed;
import com.github.bakycoder.backtobed.platform.services.IModConfig;
import com.github.bakycoder.backtobed.platform.services.IItemRegistrar;
import com.github.bakycoder.backtobed.platform.services.IPlatformHelper;

import java.util.ServiceLoader;

public class Services {
    private static final IPlatformHelper PLATFORM = load(IPlatformHelper.class);
    private static final IItemRegistrar ITEM_REGISTRAR = load(IItemRegistrar.class);
    private static final IModConfig MOD_CONFIG = load(IModConfig.class);

    public static IPlatformHelper getPlatform() {
        return PLATFORM;
    }

    public static IItemRegistrar getItemRegistrar() {
        return ITEM_REGISTRAR;
    }

    public static IModConfig getModConfig() {
        return MOD_CONFIG;
    }

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        BackToBed.LOGGER.debug("Loaded {} for service {}", loadedService, clazz);
        return loadedService;
    }
}
