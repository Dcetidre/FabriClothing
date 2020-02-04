package com.software.ddk.fabriclothing.common.items.clothes.lines;

import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseShirt;

public class LinesShirt extends BaseShirt {

    @Override
    public String clothId() {
        return "type2";
    }

    @Override
    public boolean multiLayer() {
        return true;
    }
}
