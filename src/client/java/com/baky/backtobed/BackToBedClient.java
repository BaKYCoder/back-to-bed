package com.baky.backtobed;

import com.baky.backtobed.item.ModItems;
import net.fabricmc.api.ClientModInitializer;

public class BackToBedClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ModItems.register();
	}
}