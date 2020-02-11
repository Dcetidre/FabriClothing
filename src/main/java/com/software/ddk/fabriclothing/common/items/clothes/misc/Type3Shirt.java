package com.software.ddk.fabriclothing.common.items.clothes.misc;

import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseShirt;

public class Type3Shirt extends BaseShirt {
    @Override
    public String clothId() {
        return "type3";
    }

    @Override
    public boolean multiLayer() {
        return true;
    }
}
