package com.software.ddk.fabriclothing;

import com.software.ddk.fabriclothing.items.*;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleBoots;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleCoat;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleLeggings;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleShirt;
import com.software.ddk.fabriclothing.items.hats.special.ChristmasHat;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class FabriClothing implements ModInitializer {
    public static final String MOD_ID = "fabriclothing";

    //group
    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "fabriclothing_group"), () -> new ItemStack(Contents.SIMPLE_SHIRT));

    @Override
    public void onInitialize() {
        Contents.registerAll();
    }
}
