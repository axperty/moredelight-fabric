package com.axperty.moredelight;

import com.axperty.moredelight.item.ItemList;
import com.axperty.moredelight.registry.*;
import com.axperty.moredelight.util.UtilRegistries;
import com.axperty.moredelight.util.VillageTradeUtil;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class MoreDelight implements ModInitializer {
    public static final String MOD_ID = "moredelight";
    public static final RegistryKey<ItemGroup> GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, new Identifier(MOD_ID, "group"));

    @Override
    public void onInitialize() {
        Registry.register(Registries.ITEM_GROUP, GROUP, FabricItemGroup.builder()
                .displayName(Text.translatable("More Delight"))
                .icon(() -> new ItemStack(ItemList.WOODEN_KNIFE))
                .build());

        ItemRegistry.registerItems();
        BlockRegistry.registerBlocks();

        BlockEntityRegistry.registerBlockEntity();
        ScreenHandlersRegistry.registerHandlers();
        UtilRegistries.registerUtil();
    }
}
