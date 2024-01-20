package com.axperty.moredelight.util;

import com.axperty.moredelight.item.ItemList;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class VillageTradeUtil {

    public static void registerTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FARMER, 1, factories -> factories.add((entity, random) ->
                new TradeOffer(new ItemStack(ItemList.BREAD_SLICE, 32), new ItemStack(Items.EMERALD), 16, 2, 0.05F)));
    }
}