package com.software.ddk.fabriclothing.common.items.clothes.stripped;

import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseShirt;

public class StrippedShirt extends BaseShirt {

    @Override
    public String clothId() {
        return "type1";
    }

    @Override
    public boolean multiLayer() {
        return true;
    }
}
