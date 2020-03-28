package com.software.ddk.fabriclothing.common.items.clothes.checkered;

import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseShirt;

public class CheckeredShirt extends BaseShirt {
    @Override
    public String clothId() {
        return "type3";
    }

    @Override
    public boolean multiLayer() {
        return true;
    }
}
