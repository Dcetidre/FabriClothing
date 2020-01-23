package com.software.ddk.fabriclothing;

import com.software.ddk.fabriclothing.items.*;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleBoots;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleCoat;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleLeggings;
import com.software.ddk.fabriclothing.items.clothes.simple.SimpleShirt;
import com.software.ddk.fabriclothing.items.generic.*;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;

public class ClientFabriClothing implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((FabricItem) stack.getItem()).getColor(stack), Contents.FABRIC_ITEM);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((SimpleShirt) stack.getItem()).getColor(stack), Contents.SIMPLE_SHIRT);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((SimpleCoat) stack.getItem()).getColor(stack), Contents.SIMPLE_COAT);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((SimpleLeggings) stack.getItem()).getColor(stack), Contents.SIMPLE_LEGGINGS);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((SimpleBoots) stack.getItem()).getColor(stack), Contents.SIMPLE_BOOTS);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((HatItem) stack.getItem()).getColor(stack), Contents.HAT_ITEM);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((HatItem) stack.getItem()).getColor(stack), Contents.HAT_STRIPPED_ITEM);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((HatItem) stack.getItem()).getColor(stack), Contents.HAT_STRIPPED2_ITEM);
        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> ((HatItem) stack.getItem()).getColor(stack), Contents.HAT_FLOWERED_ITEM);

    }
}
