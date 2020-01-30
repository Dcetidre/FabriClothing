package com.software.ddk.fabriclothing;

import com.software.ddk.clothing.api.ICloth;
import com.software.ddk.fabriclothing.common.items.*;
import com.software.ddk.fabriclothing.common.items.clothes.simple.SimpleBoots;
import com.software.ddk.fabriclothing.common.items.clothes.simple.SimpleCoat;
import com.software.ddk.fabriclothing.common.items.clothes.simple.SimpleLeggings;
import com.software.ddk.fabriclothing.common.items.clothes.simple.SimpleShirt;
import com.software.ddk.fabriclothing.common.items.clothes.stripped.StrippedShirt;
import com.software.ddk.fabriclothing.common.items.generic.*;
import com.software.ddk.fabriclothing.gui.ClothingLoomContainer;
import com.software.ddk.fabriclothing.gui.ClothingLoomScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.screen.ScreenProviderRegistry;
import net.fabricmc.fabric.api.container.ContainerProviderRegistry;
import net.minecraft.client.color.item.ItemColorProvider;
import net.minecraft.container.BlockContext;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Identifier;

public class ClientFabriClothing implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((FabricItem) stack.getItem()).getColor(stack), Contents.FABRIC_ITEM);

        //simple items, one layered.
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((DyeableItem) stack.getItem()).getColor(stack),
                Contents.SIMPLE_SHIRT, Contents.SIMPLE_COAT, Contents.SIMPLE_LEGGINGS, Contents.SIMPLE_BOOTS);

        //layered hats.
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> (tintIndex == 0) ? ((DyeableItem) stack.getItem()).getColor(stack) : ((ICloth) stack.getItem()).getColorOverlay(stack),
                Contents.HAT_ITEM, Contents.HAT_STRIPPED_ITEM, Contents.HAT_STRIPPED2_ITEM, Contents.HAT_FLOWERED_ITEM);

        //layered items.
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> (tintIndex == 0) ? ((DyeableItem) stack.getItem()).getColor(stack) : ((ICloth) stack.getItem()).getColorOverlay(stack),
                Contents.STRIPPED_COAT, Contents.STRIPPED_SHIRT,
                Contents.LINES_COAT, Contents.LINES_SHIRT, Contents.LINES_LEGGINGS);

        //gui
        ScreenProviderRegistry.INSTANCE.registerFactory(new Identifier(FabriClothing.MOD_ID,"clothing_loom_block"), (syncId, identifier, player, buf) -> new ClothingLoomScreen(new ClothingLoomContainer(syncId, player.inventory, BlockContext.create(player.world, buf.readBlockPos())), player.inventory));

    }
}
