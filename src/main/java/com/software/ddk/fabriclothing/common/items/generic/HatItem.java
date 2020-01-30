package com.software.ddk.fabriclothing.common.items.generic;

import com.software.ddk.clothing.api.ClothRenderData;
import com.software.ddk.clothing.api.ICloth;
import com.software.ddk.fabriclothing.FabriClothing;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.DyeableItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class HatItem extends Item implements ICloth, DyeableItem {

    public HatItem() {
        super(new Item.Settings().group(FabriClothing.GROUP));
    }

    @Override
    public int getColor(ItemStack stack) {
        CompoundTag compoundTag = stack.getSubTag("display");
        return compoundTag != null && compoundTag.contains("color", 99) ? compoundTag.getInt("color") : 0xffffff;
    }

    @Override
    public EquipmentSlot slotType() {
        return EquipmentSlot.HEAD;
    }

    @Override
    public String clothId() {
        return "type0";
    }

    @Override
    public String modId() {
        return FabriClothing.MOD_ID;
    }

    @Override
    public boolean customEquip() {
        return true;
    }

    @Override
    public boolean[][] equipLayers() {
        return new boolean[][]{
                {false, false, false, false},
                {false, false, false, false}
        };
    }

    @Override
    public boolean customModel() {
        return true;
    }

    @Override
    public ClothRenderData renderData() {
        //create model renderer
        ClothRenderData clothRenderData = new ClothRenderData(EquipmentSlot.HEAD,
                1.3f, 1.1f, 1.2f,
                0.0f, 0.45f, 0.08f);
        clothRenderData.setRenderMode(ClothRenderData.RENDER_ITEMMODEL);
        clothRenderData.setRotable(true);
        clothRenderData.setRotation(180.0f, 0.0f, 0.0f);
        return clothRenderData;
    }
}
