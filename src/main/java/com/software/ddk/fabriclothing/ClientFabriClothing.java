package com.software.ddk.fabriclothing;

import com.software.ddk.clothing.api.ICloth;
import com.software.ddk.fabriclothing.common.items.*;
import com.software.ddk.fabriclothing.gui.DyeVatContainer;
import com.software.ddk.fabriclothing.gui.DyeVatScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.container.BlockContext;
import net.minecraft.item.DyeableItem;

public class ClientFabriClothing implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((FabricItem) stack.getItem()).getColor(stack), Contents.FABRIC_ITEM);

        //simple items, one layered.
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DyeableItem) stack.getItem()).getColor(stack),
                Contents.BASE_SHIRT, Contents.BASE_COAT, Contents.BASE_LEGGINGS, Contents.BASE_BOOTS,
                Contents.SIMPLE_SHIRT, Contents.SIMPLE_COAT);

        //layered hats.
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> (tintIndex == 0) ? ((DyeableItem) stack.getItem()).getColor(stack) : ((ICloth) stack.getItem()).getColorOverlay(stack),
                Contents.HAT_ITEM, Contents.HAT_STRIPPED_ITEM, Contents.HAT_STRIPPED2_ITEM, Contents.HAT_FLOWERED_ITEM);

        //layered items.
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> (tintIndex == 0) ? ((DyeableItem) stack.getItem()).getColor(stack) : ((ICloth) stack.getItem()).getColorOverlay(stack),
                Contents.STRIPPED_COAT, Contents.STRIPPED_SHIRT,
                Contents.LINES_COAT, Contents.LINES_SHIRT, Contents.LINES_LEGGINGS,
                Contents.TYPE3_COAT, Contents.TYPE3_SHIRT, Contents.TYPE3_LEGGINGS, Contents.TYPE3_BOOTS);

        //gui
        ScreenProviderRegistry.INSTANCE.registerFactory(FabriClothing.DYE_VAT_CONTAINER, (syncId, identifier, player, buf) -> new DyeVatScreen(new DyeVatContainer(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos()))));

        //render layers
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(), Contents.CLOTHING_DYEVAT_BLOCK);

    }
}
