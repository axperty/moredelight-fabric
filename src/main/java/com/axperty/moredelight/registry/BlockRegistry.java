package com.axperty.moredelight.registry;

import com.axperty.moredelight.MoreDelight;
import com.axperty.moredelight.block.BlockList;
import com.axperty.moredelight.block.custom.*;
import com.nhoryzon.mc.farmersdelight.block.CabinetBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class BlockRegistry {

    public static void registerBlocks() {

        // Oak Drawer Registry
        BlockList.OAK_DRAWER = block("oak_drawer",
                new DrawerBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.WOOD).nonOpaque()));

        // Birch Drawer Registry
        BlockList.BIRCH_DRAWER = block("birch_drawer",
                new DrawerBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.WOOD).nonOpaque()));

        // Spruce Drawer Registry
        BlockList.SPRUCE_DRAWER = block("spruce_drawer",
                new DrawerBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.WOOD).nonOpaque()));

        // Jungle Drawer Registry
        BlockList.JUNGLE_DRAWER = block("jungle_drawer",
                new DrawerBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.WOOD).nonOpaque()));

        // Acacia Drawer Registry
        BlockList.ACACIA_DRAWER = block("acacia_drawer",
                new DrawerBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.WOOD).nonOpaque()));

        // Dark Oak Drawer Registry
        BlockList.DARK_OAK_DRAWER = block("dark_oak_drawer",
                new DrawerBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.WOOD).nonOpaque()));

        // Mangrove Drawer Registry
        BlockList.MANGROVE_DRAWER = block("mangrove_drawer",
                new DrawerBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.WOOD).nonOpaque()));

        // Crimson Drawer Registry
        BlockList.CRIMSON_DRAWER = block("crimson_drawer",
                new DrawerBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.WOOD).nonOpaque()));

        // Warped Drawer Registry
        BlockList.WARPED_DRAWER = block("warped_drawer",
                new DrawerBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.WOOD).nonOpaque()));

        // Glass Oak Cabinet Registry
        BlockList.GLASS_OAK_CABINET = block("glass_oak_cabinet",
                new GlassCabinetBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.GLASS).nonOpaque()));

        // Glass Birch Cabinet Registry
        BlockList.GLASS_BIRCH_CABINET = block("glass_birch_cabinet",
                new GlassCabinetBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.GLASS).nonOpaque()));

        // Glass Spruce Cabinet Registry
        BlockList.GLASS_SPRUCE_CABINET = block("glass_spruce_cabinet",
                new GlassCabinetBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.GLASS).nonOpaque()));

        // Glass Jungle Cabinet Registry
        BlockList.GLASS_JUNGLE_CABINET = block("glass_jungle_cabinet",
                new GlassCabinetBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.GLASS).nonOpaque()));

        // Glass Acacia Cabinet Registry
        BlockList.GLASS_ACACIA_CABINET = block("glass_acacia_cabinet",
                new GlassCabinetBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.GLASS).nonOpaque()));

        // Glass Dark Oak Cabinet Registry
        BlockList.GLASS_DARK_OAK_CABINET = block("glass_dark_oak_cabinet",
                new GlassCabinetBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.GLASS).nonOpaque()));

        // Glass Mangrove Cabinet Registry
        BlockList.GLASS_MANGROVE_CABINET = block("glass_mangrove_cabinet",
                new GlassCabinetBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.GLASS).nonOpaque()));

        // Glass Crimson Cabinet Registry
        BlockList.GLASS_CRIMSON_CABINET = block("glass_crimson_cabinet",
                new GlassCabinetBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.GLASS).nonOpaque()));

        // Glass Warped Cabinet Registry
        BlockList.GLASS_WARPED_CABINET = block("glass_warped_cabinet",
                new GlassCabinetBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.GLASS).nonOpaque()));
    }

    public static final Block DRAWER_BLOCK = withoutBlockItem("drawer_block",
            new DrawerBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.WOOD).nonOpaque()));

    public static final Block GLASS_CABINET_BLOCK = withoutBlockItem("glass_cabinet_block",
            new GlassCabinetBlock(blockSettings(1.0f, 2.0f, BlockSoundGroup.GLASS).nonOpaque()));

    private static FabricBlockSettings blockSettings(float hardness, float resistance, BlockSoundGroup sound) {
        return FabricBlockSettings.create().strength(hardness, resistance).sounds(sound);
    }

    private static Block block(String name, Block block) {
        blockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(MoreDelight.MOD_ID, name), block);
    }

    private static Item blockItem(String name, Block block) {
        Item item = Registry.register(Registries.ITEM, new Identifier(MoreDelight.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
        ItemGroupEvents.modifyEntriesEvent(MoreDelight.GROUP).register(entries -> entries.add(item));
        return item;
    }

    private static Block withoutBlockItem(String name, Block block) {
        return Registry.register(Registries.BLOCK, new Identifier(MoreDelight.MOD_ID, name), block);
    }
}
