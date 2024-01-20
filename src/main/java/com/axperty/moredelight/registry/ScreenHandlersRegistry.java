package com.axperty.moredelight.registry;

import com.axperty.moredelight.MoreDelight;
import com.axperty.moredelight.util.inventory.screen.*;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry.SimpleClientHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ScreenHandlersRegistry {
    public static ScreenHandlerType<DrawerBlockScreenHandler> DRAWER_BLOCK_HANDLER;
    public static ScreenHandlerType<GlassCabinetBlockScreenHandler> GLASS_CABINET_BLOCK_HANDLER;

    public static void registerHandlers() {
        DRAWER_BLOCK_HANDLER = handler("drawer_block", DrawerBlockScreenHandler::new);
        GLASS_CABINET_BLOCK_HANDLER = handler("glass_cabinet_block", GlassCabinetBlockScreenHandler::new);
    }

    public static void registerScreens() {
        ScreenRegistry.register(DRAWER_BLOCK_HANDLER, DrawerBlockScreen::new);
        ScreenRegistry.register(GLASS_CABINET_BLOCK_HANDLER, GlassCabinetBlockScreen::new);
    }

    private static ScreenHandlerType handler(String name, SimpleClientHandlerFactory<ScreenHandler> handler) {
        return ScreenHandlerRegistry.registerSimple(new Identifier(MoreDelight.MOD_ID, name), handler);
    }
}
