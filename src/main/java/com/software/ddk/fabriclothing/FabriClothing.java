package com.software.ddk.fabriclothing;

import com.software.ddk.fabriclothing.gui.ClothingLoomContainer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class FabriClothing implements ModInitializer {
    public static final String MOD_ID = "fabriclothing";

    //group
    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "fabriclothing_group"), () -> new ItemStack(Contents.SIMPLE_SHIRT));

    @Override
    public void onInitialize() {
        Contents.registerAll();

        //gui
        //todo - no idea wtf is broken here
        //ContainerProviderRegistry.INSTANCE.registerFactory(new Identifier(FabriClothing.MOD_ID,"clothing_loom_block"), (syncId, id, player, buf) -> new ClothingLoomContainer(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));

    }
}
