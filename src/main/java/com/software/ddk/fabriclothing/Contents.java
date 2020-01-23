package com.software.ddk.fabriclothing;

import com.software.ddk.fabriclothing.items.*;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleBoots;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleCoat;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleLeggings;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleShirt;
import com.software.ddk.fabriclothing.items.generic.HatItem;
import com.software.ddk.fabriclothing.items.hats.special.ChristmasHat;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Contents {

    public static final Item FABRIC_ITEM = new FabricItem();
    //simple set
    public static final Item SIMPLE_SHIRT = new SimpleShirt();
    public static final Item SIMPLE_COAT = new SimpleCoat();
    public static final Item SIMPLE_LEGGINGS = new SimpleLeggings();
    public static final Item SIMPLE_BOOTS = new SimpleBoots();
    //hats
    public static final Item HAT_ITEM = new HatItem();
    public static final Item HAT_STRIPPED_ITEM = new HatItem();
    public static final Item HAT_STRIPPED2_ITEM = new HatItem();
    public static final Item HAT_FLOWERED_ITEM = new HatItem();
    public static final Item HAT_CHRISTMAS = new ChristmasHat();

    public static void registerAll(){
        Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, "fabric_item"), FABRIC_ITEM);

        //simple set
        Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, "simple_shirt_item"), SIMPLE_SHIRT);
        Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, "simple_coat_item"), SIMPLE_COAT);
        Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, "simple_leggings_item"), SIMPLE_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, "simple_boots_item"), SIMPLE_BOOTS);

        //hats
        Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, "hat_item"), HAT_ITEM);
        Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, "hat_stripped_item"), HAT_STRIPPED_ITEM);
        Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, "hat_stripped2_item"), HAT_STRIPPED2_ITEM);
        Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, "hat_flowered_item"), HAT_FLOWERED_ITEM);

        //special hats
        Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, "hat_christmas_item"), HAT_CHRISTMAS);

    }

}
