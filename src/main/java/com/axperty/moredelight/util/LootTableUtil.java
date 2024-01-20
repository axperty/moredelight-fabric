package com.axperty.moredelight.util;

import com.axperty.moredelight.item.ItemList;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.item.Item;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

public class LootTableUtil {
    private static final Identifier DESERT_HOUSE = table(villageHouse("desert_house"));
    private static final Identifier PLAINS_HOUSE = table(villageHouse("plains_house"));
    private static final Identifier SAVANNA_HOUSE = table(villageHouse("savanna_house"));
    private static final Identifier SNOWY_HOUSE = table(villageHouse("snowy_house"));
    private static final Identifier TAIGA_HOUSE = table(villageHouse("taiga_house"));

    public static void modifyLootTables() {
        lootTable(DESERT_HOUSE, ItemList.WOODEN_KNIFE, 0.3f, 1.0f, 1.0f);
        lootTable(PLAINS_HOUSE, ItemList.WOODEN_KNIFE, 0.5f, 1.0f, 1.0f);
        lootTable(SAVANNA_HOUSE, ItemList.WOODEN_KNIFE, 0.5f, 1.0f, 1.0f);
        lootTable(SNOWY_HOUSE, ItemList.WOODEN_KNIFE, 0.6f, 1.0f, 1.0f);
        lootTable(TAIGA_HOUSE, ItemList.WOODEN_KNIFE, 0.4f, 1.0f, 1.0f);

        lootTable(DESERT_HOUSE, ItemList.BREAD_SLICE, 0.3f, 1.0f, 3.0f);
        lootTable(PLAINS_HOUSE, ItemList.BREAD_SLICE, 0.5f, 1.0f, 4.0f);
        lootTable(SAVANNA_HOUSE, ItemList.BREAD_SLICE, 0.5f, 1.0f, 5.0f);
        lootTable(SNOWY_HOUSE, ItemList.BREAD_SLICE, 0.6f, 1.0f, 5.0f);
        lootTable(TAIGA_HOUSE, ItemList.BREAD_SLICE, 0.4f, 1.0f, 6.0f);

        lootTable(DESERT_HOUSE, ItemList.TOAST, 0.3f, 1.0f, 3.0f);
        lootTable(PLAINS_HOUSE, ItemList.TOAST, 0.5f, 1.0f, 4.0f);
        lootTable(SAVANNA_HOUSE, ItemList.TOAST, 0.5f, 1.0f, 5.0f);
        lootTable(SNOWY_HOUSE, ItemList.TOAST, 0.6f, 1.0f, 5.0f);
        lootTable(TAIGA_HOUSE, ItemList.TOAST, 0.4f, 1.0f, 6.0f);
    }

    private static void lootTable(Identifier identifier, Item item, float chance, float min, float max) {
        LootTableEvents.MODIFY.register(((resourceManager, manager, id, supplier, setter) -> {
            if (identifier.equals(id)) {
                LootPool.Builder poolBuilder = LootPool.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .conditionally(RandomChanceLootCondition.builder(chance))
                        .with(ItemEntry.builder(item))
                        .apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(min, max)).build());
                supplier.pool(poolBuilder.build());
            }
        }));
    }

    private static Identifier table(String file) {
        return new Identifier("minecraft", file);
    }

    private static String block(String type) {
        return "blocks/" + type;
    }

    private static String chest(String type) {
        return "chests/" + type;
    }

    private static String villageHouse(String type) {
        return "chests/village/village_" + type;
    }

    private static String entity(String type) {
        return "entities/" + type;
    }

    private static String fishing(String type) {
        return "gameplay/fishing/" + type;
    }
}