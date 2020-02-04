package com.software.ddk.fabriclothing;

import com.software.ddk.fabriclothing.common.blocks.ClothingDyevatBlock;
import com.software.ddk.fabriclothing.common.items.*;
import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseBoots;
import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseCoat;
import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseLeggings;
import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseShirt;
import com.software.ddk.fabriclothing.common.items.clothes.lines.LinesCoat;
import com.software.ddk.fabriclothing.common.items.clothes.lines.LinesLeggings;
import com.software.ddk.fabriclothing.common.items.clothes.lines.LinesShirt;
import com.software.ddk.fabriclothing.common.items.clothes.simple.SimpleBoots;
import com.software.ddk.fabriclothing.common.items.clothes.simple.SimpleCoat;
import com.software.ddk.fabriclothing.common.items.clothes.simple.SimpleLeggings;
import com.software.ddk.fabriclothing.common.items.clothes.simple.SimpleShirt;
import com.software.ddk.fabriclothing.common.items.clothes.stripped.StrippedCoat;
import com.software.ddk.fabriclothing.common.items.clothes.stripped.StrippedShirt;
import com.software.ddk.fabriclothing.common.items.hats.generic.BaseHat;
import com.software.ddk.fabriclothing.common.items.hats.special.ChristmasHat;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Contents {

    public static final Item FABRIC_ITEM = new FabricItem();

    public static final Item BASE_SHIRT = new BaseShirt();
    public static final Item BASE_COAT = new BaseCoat();
    public static final Item BASE_LEGGINGS = new BaseLeggings();
    public static final Item BASE_BOOTS = new BaseBoots();

    public static final Item SIMPLE_SHIRT = new SimpleShirt();
    public static final Item SIMPLE_COAT = new SimpleCoat();
    //public static final Item SIMPLE_LEGGINGS = new SimpleLeggings();
    //public static final Item SIMPLE_BOOTS = new SimpleBoots();

    //hats
    public static final Item HAT_ITEM = new BaseHat();
    public static final Item HAT_STRIPPED_ITEM = new BaseHat();
    public static final Item HAT_STRIPPED2_ITEM = new BaseHat();
    public static final Item HAT_FLOWERED_ITEM = new BaseHat();
    public static final Item HAT_CHRISTMAS = new ChristmasHat();

    public static final Item STRIPPED_COAT = new StrippedCoat();
    public static final Item STRIPPED_SHIRT = new StrippedShirt();

    public static final Item LINES_COAT = new LinesCoat();
    public static final Item LINES_SHIRT = new LinesShirt();
    public static final Item LINES_LEGGINGS = new LinesLeggings();

    public static final Block CLOTHING_DYEVAT_BLOCK = new ClothingDyevatBlock();

    public static void registerAll(){

        registerBlock("clothing_dyevat_block", CLOTHING_DYEVAT_BLOCK, new Item.Settings().group(FabriClothing.GROUP));
        register("fabric_item", FABRIC_ITEM);

        register("base_shirt_item", BASE_SHIRT);
        register("base_coat_item", BASE_COAT);
        register("base_leggings_item", BASE_LEGGINGS);
        register("base_boots_item", BASE_BOOTS);

        register("simple_shirt_item", SIMPLE_SHIRT);
        register("simple_coat_item", SIMPLE_COAT);
        //register("simple_leggings_item", SIMPLE_LEGGINGS);
        //register("simple_boots_item", SIMPLE_BOOTS);
        
        register("stripped_shirt_item", STRIPPED_SHIRT);
        register("stripped_coat_item", STRIPPED_COAT);

        register("line_coat_item", LINES_COAT);
        register("line_shirt_item", LINES_SHIRT);
        register("line_leggings_item", LINES_LEGGINGS);

        //hats
        register("hat_item", HAT_ITEM);
        register("hat_stripped_item", HAT_STRIPPED_ITEM);
        register("hat_stripped2_item", HAT_STRIPPED2_ITEM);
        register("hat_flowered_item", HAT_FLOWERED_ITEM);

        //special hats
        register("hat_christmas_item", HAT_CHRISTMAS);

    }

    public static Item register(String id, Item item){
        return Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, id), item);
    }

    public static Item registerBlock(String id, Block block, Item.Settings settings){
        BlockItem item = new BlockItem(block, settings);
        Registry.register(Registry.BLOCK, new Identifier(FabriClothing.MOD_ID, id), block);
        return Registry.register(Registry.ITEM, new Identifier(FabriClothing.MOD_ID, id), item);
    }

}
