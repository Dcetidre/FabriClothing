package com.software.ddk.fabriclothing;

import com.software.ddk.fabriclothing.gui.DyeVatContainer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.container.BlockContext;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class FabriClothing implements ModInitializer {
    public static final String MOD_ID = "fabriclothing";
    public static final Identifier DYE_VAT_CONTAINER = new Identifier(FabriClothing.MOD_ID,"dye_vat_container");

    //group
    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "fabriclothing_group"), () -> new ItemStack(Contents.SIMPLE_SHIRT));

    @Override
    public void onInitialize() {
        Contents.registerAll();

        //gui
        ContainerProviderRegistry.INSTANCE.registerFactory(DYE_VAT_CONTAINER, (syncId, id, player, buf) -> new DyeVatContainer(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())));

    }
}
