package com.software.ddk.fabriclothing.common.items.hats.special;

import com.software.ddk.clothing.api.ClothRenderData;
import com.software.ddk.fabriclothing.common.items.hats.generic.BaseTrinketsHat;
import net.minecraft.entity.EquipmentSlot;

public class BunnyHat extends BaseTrinketsHat {

    @Override
    public ClothRenderData renderData() {

        //cloth render
        ClothRenderData clothRenderData = new ClothRenderData(EquipmentSlot.HEAD,
                0.55f, 0.8f, 0.5f,
                0.0f, 0.38f, 0.08f);
        clothRenderData.setRenderMode(ClothRenderData.RENDER_ITEMMODEL);
        clothRenderData.setRotable(true);
        clothRenderData.setRotation(180.0f,180.0f, 0.0f);

        //trinkets render
        ClothRenderData trinketsRenderData = new ClothRenderData(EquipmentSlot.HEAD,
                0.55f, 0.8f, 0.5f,
                0.0f, 0.38f, 0.68f);
        trinketsRenderData.setRenderMode(ClothRenderData.RENDER_ITEMMODEL);
        trinketsRenderData.setRotable(true);
        trinketsRenderData.setRotation(180.0f, 180.0f, 0.0f);

        return (isSlotCloth()) ? clothRenderData : trinketsRenderData;
    }

}
