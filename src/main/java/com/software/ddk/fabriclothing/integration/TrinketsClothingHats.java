package com.software.ddk.fabriclothing.integration;

import com.software.ddk.fabriclothing.Contents;
import com.software.ddk.fabriclothing.common.items.hats.generic.BaseTrinketsHat;
import com.software.ddk.fabriclothing.common.items.hats.special.BunnyHat;
import com.software.ddk.fabriclothing.common.items.hats.special.ChristmasHat;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class TrinketsClothingHats {

    //hats
    public static Item HAT_ITEM = new BaseTrinketsHat();
    public static Item HAT_STRIPPED_ITEM = new BaseTrinketsHat();
    public static Item HAT_STRIPPED2_ITEM = new BaseTrinketsHat();
    public static Item HAT_FLOWERED_ITEM = new BaseTrinketsHat();
    public static Item HAT_CHRISTMAS = new ChristmasHat();
    public static Item HAT_BUNNY = new BunnyHat();

    public static void initTrinkets(){
        TrinketSlots.addSlot(SlotGroups.HEAD, Slots.MASK, new Identifier("trinkets", "textures/item/empty_trinket_slot_mask.png"));
    }

    public static void registerHats(){
        //hats
        Contents.register("hat_item", HAT_ITEM);
        Contents.register("hat_stripped_item", HAT_STRIPPED_ITEM);
        Contents.register("hat_stripped2_item", HAT_STRIPPED2_ITEM);
        Contents.register("hat_flowered_item", HAT_FLOWERED_ITEM);

        //special hats
        Contents.register("hat_christmas_item", HAT_CHRISTMAS);
        Contents.register("hat_bunny_item", HAT_BUNNY);
    }


}
