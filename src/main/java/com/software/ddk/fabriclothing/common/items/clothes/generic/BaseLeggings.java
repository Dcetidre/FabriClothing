package com.software.ddk.fabriclothing.common.items.clothes.generic;

import com.software.ddk.clothing.api.ICloth;
import com.software.ddk.fabriclothing.FabriClothing;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class BaseLeggings extends Item implements ICloth, DyeableItem {
    public BaseLeggings() {
        super(new Item.Settings().group(FabriClothing.GROUP));
    }

    @Override
    public int getColor(ItemStack stack) {
        CompoundTag compoundTag = stack.getSubTag("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 0xffffff;
    }

    @Override
    public boolean customEquip() {
        return true;
    }

    @Override
    public boolean[][] equipLayers() {
        return new boolean[][]{
                {false, false, false, true},
                {false, false, false, false}
        };
    }

    @Override
    public EquipmentSlot slotType() {
        return EquipmentSlot.LEGS;
    }

    @Override
    public String clothId() {
        return "base";
    }

    @Override
    public String modId() {
        return FabriClothing.MOD_ID;
    }
}
