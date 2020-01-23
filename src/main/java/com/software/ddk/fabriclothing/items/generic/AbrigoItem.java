package com.software.ddk.fabriclothing.items.generic;

import com.software.ddk.clothing.api.ICloth;
import com.software.ddk.fabriclothing.FabriClothing;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class AbrigoItem extends Item implements ICloth, DyeableItem {
    private int index = 0;

    public AbrigoItem() {
        super(new Item.Settings().group(FabriClothing.GROUP));
    }

    @Override
    public int getColor(ItemStack stack) {
        CompoundTag compoundTag = stack.getSubTag("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 0xffffff;
        //if (index == 0){
        //            return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 0xffffff;
        //        } else {
        //            return compoundTag != null && compoundTag.contains("color1", 99) ? compoundTag.getInt("color1") : 0xffffff;
        //        }
    }

    @Override
    public void setColor(ItemStack stack, int color) {
        stack.getOrCreateSubTag("display").putInt("color", color);
        //if (index == 0){
        //            stack.getOrCreateSubTag("display").putInt("color", color);
        //        } else {
        //            stack.getOrCreateSubTag("display").putInt("color1", color);
        //        }
    }

    @Override
    public boolean customEquip() {
        return true;
    }

    @Override
    public boolean[][] equipLayers() {
        return new boolean[][]{
                {false, false, false, false},
                {false, true, false, false}
        };
    }

    public void setIndex(int index){
        this.index = index;
    }

    @Override
    public EquipmentSlot slotType() {
        return EquipmentSlot.CHEST;
    }

    @Override
    public String clothId() {
        return "simple";
    }

    @Override
    public String modId() {
        return FabriClothing.MOD_ID;
    }
}
