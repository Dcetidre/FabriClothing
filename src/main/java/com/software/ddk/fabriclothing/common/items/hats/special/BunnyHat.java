package com.software.ddk.fabriclothing.common.items.hats.special;

import com.software.ddk.clothing.api.ClothRenderData;
import com.software.ddk.fabriclothing.common.items.hats.generic.BaseHat;
import net.minecraft.entity.EquipmentSlot;

public class BunnyHat extends BaseHat {
    @Override
    public ClothRenderData renderData() {
        ClothRenderData clothRenderData = new ClothRenderData(EquipmentSlot.HEAD,
                0.55f, 0.8f, 1.0f,
                0.0f, 0.38f, 0.08f);
        clothRenderData.setRenderMode(ClothRenderData.RENDER_ITEMMODEL);
        clothRenderData.setRotable(true);
        clothRenderData.setRotation(180.0f,180.0f, 0.0f);
        return clothRenderData;
    }
}
