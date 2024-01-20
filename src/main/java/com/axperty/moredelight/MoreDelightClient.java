package com.axperty.moredelight;

import com.axperty.moredelight.registry.ScreenHandlersRegistry;
import net.fabricmc.api.ClientModInitializer;

public class MoreDelightClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenHandlersRegistry.registerScreens();
    }
}