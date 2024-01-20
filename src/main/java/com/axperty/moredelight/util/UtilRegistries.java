package com.axperty.moredelight.util;

import com.axperty.moredelight.block.BlockList;
import com.axperty.moredelight.item.ItemList;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

public class UtilRegistries {

    public static void registerUtil() {
        registerFlammables();
        LootTableUtil.modifyLootTables();
        VillageTradeUtil.registerTrades();
    }

    public static void registerFlammables() {
        FuelRegistry.INSTANCE.add(ItemList.WOODEN_KNIFE, 200);
    }
}
