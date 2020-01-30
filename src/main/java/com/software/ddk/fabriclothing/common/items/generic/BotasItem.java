package com.software.ddk.fabriclothing.common.items.generic;

import com.software.ddk.clothing.api.ICloth;
import com.software.ddk.fabriclothing.FabriClothing;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class BotasItem extends Item implements ICloth, DyeableItem {
    public BotasItem() {
        super(new Item.Settings().group(FabriClothing.GROUP));
    }

    @Override
    public boolean customEquip() {
        return true;
    }

    @Override
    public boolean[][] equipLayers() {
        return new boolean[][]{
                {false, false, true, false},
                {false, false, true, true}
        };
    }

    @Override
    public void setColor(ItemStack stack, int color) {
        stack.getOrCreateSubTag("display").putInt("color", color);
    }

    public void setColorOverlay(ItemStack stack, int color) {
        stack.getOrCreateSubTag("display").putInt("colorOverlay", color);
    }

    @Override
    public int getColor(ItemStack stack) {
        CompoundTag compoundTag = stack.getSubTag("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 0xffffff;
    }

    @Override
    public int getColorOverlay(ItemStack stack) {
        CompoundTag compoundTag = stack.getSubTag("display");
        return compoundTag != null && compoundTag.contains("colorOverlay", 99) ? compoundTag.getInt("colorOverlay") : 0xffffff;
    }

    @Override
    public EquipmentSlot slotType() {
        return EquipmentSlot.FEET;
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
