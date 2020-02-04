package com.software.ddk.fabriclothing.common.items.clothes.lines;

import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseCoat;

public class LinesCoat extends BaseCoat {

    @Override
    public String clothId() {
        return "type2";
    }

    @Override
    public boolean multiLayer() {
        return true;
    }
}
