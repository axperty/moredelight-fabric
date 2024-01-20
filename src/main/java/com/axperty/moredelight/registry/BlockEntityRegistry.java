package com.axperty.moredelight.registry;

import com.axperty.moredelight.MoreDelight;
import com.axperty.moredelight.block.BlockList;
import com.axperty.moredelight.block.entity.DrawerBlockEntity;
import com.axperty.moredelight.block.entity.GlassCabinetBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder.Factory;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;


public class BlockEntityRegistry {
    public static BlockEntityType<DrawerBlockEntity> DRAWER_BLOCK_ENTITY;
    public static BlockEntityType<GlassCabinetBlockEntity> GLASS_CABINET_BLOCK_ENTITY;

    public static void registerBlockEntity() {
        DRAWER_BLOCK_ENTITY = entity("drawer_block", DrawerBlockEntity::new, BlockList.DRAWER_BLOCK);
        GLASS_CABINET_BLOCK_ENTITY = entity("glass_cabinet_block", GlassCabinetBlockEntity::new, BlockList.GLASS_CABINET_BLOCK);
    }

    private static BlockEntityType entity(String name, Factory<? extends BlockEntity> entity, Block block) {
        return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(MoreDelight.MOD_ID, name),
                FabricBlockEntityTypeBuilder.create(entity, block).build(null));
    }
}
