package com.software.ddk.fabriclothing.items;

import com.software.ddk.fabriclothing.FabriClothing;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class FabricItem extends Item implements DyeableItem {
    public FabricItem() {
        super(new Item.Settings().group(FabriClothing.GROUP));
    }

    @Override
    public int getColor(ItemStack stack) {
        CompoundTag compoundTag = stack.getSubTag("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 0xffffff;
    }
}
