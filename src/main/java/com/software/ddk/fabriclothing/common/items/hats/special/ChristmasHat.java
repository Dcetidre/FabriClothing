package com.software.ddk.fabriclothing.common.items.hats.special;

import com.software.ddk.clothing.api.ClothRenderData;
import com.software.ddk.fabriclothing.common.items.generic.HatItem;
import net.minecraft.entity.EquipmentSlot;

public class ChristmasHat extends HatItem {

    @Override
    public ClothRenderData renderData() {
        ClothRenderData clothRenderData = new ClothRenderData(EquipmentSlot.HEAD,
                1.1f, 1.1f, 1.2f,
                0.0f, 0.45f, 0.08f);
        clothRenderData.setRenderMode(ClothRenderData.RENDER_ITEMMODEL);
        clothRenderData.setRotable(true);
        clothRenderData.setRotation(180.0f,180.0f, 0.0f);
        return clothRenderData;
    }
}
