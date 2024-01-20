package com.axperty.moredelight.registry;

import com.axperty.moredelight.MoreDelight;
import com.axperty.moredelight.item.ItemList;
import com.axperty.moredelight.item.ToolMaterials;
import com.nhoryzon.mc.farmersdelight.FarmersDelightMod;
import com.nhoryzon.mc.farmersdelight.item.KnifeItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.nhoryzon.mc.farmersdelight.item.ModItemSettings.food;

public class ItemRegistry {

    public static void registerItems() {
        // Wooden Knife
        ItemList.WOODEN_KNIFE = knife("wooden_knife", new KnifeItem(ToolMaterials.WOOD_MATERIAL, new FabricItemSettings()));

        // Omelette
        ItemList.OMELETTE = item("omelette", new Item(food(null, 5, 0.7f)));

        // Wheat Flour
        // ItemList.WHEAT_FLOUR = item("wheat_flour", new Item(new FabricItemSettings()));

        // Bread Slice
        ItemList.BREAD_SLICE = item("bread_slice", new Item(food(null, 1, 0.3f)));

        // Toast
        ItemList.TOAST = item("toast", new Item(food(null, 2, 0.5f)));

        // Egg in Toast
        ItemList.TOAST_WITH_EGG = item("toast_with_egg", new Item(food(null, 3, 0.5f)));

        // Toast with Honey
        ItemList.TOAST_WITH_HONEY = item("toast_with_honey", new Item(food(null, 3, 0.5f)));

        // Toast with Honey
        ItemList.TOAST_WITH_SWEET_BERRIES = item("toast_with_sweet_berries", new Item(food(null, 3, 0.5f)));
    }

    private static Item knife(String name, Item item) {
        ItemGroupEvents.modifyEntriesEvent(MoreDelight.GROUP).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, new Identifier(MoreDelight.MOD_ID, name), item);
    }

    private static Item item(String name, Item item) {
        ItemGroupEvents.modifyEntriesEvent(MoreDelight.GROUP).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, new Identifier(MoreDelight.MOD_ID, name), item);
    }

    private static FabricItemSettings food(Item remainder, int hunger, float saturation) {
        return new FabricItemSettings().recipeRemainder(remainder)
                .food(new FoodComponent.Builder().hunger(hunger).saturationModifier(saturation).build());
    }

    private static FabricItemSettings basic() {
        return new FabricItemSettings();
    }
}
