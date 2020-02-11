package com.software.ddk.fabriclothing.common.items.clothes.misc;

import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseCoat;

public class Type3Coat extends BaseCoat {
    @Override
    public String clothId() {
        return "type3";
    }

    @Override
    public boolean multiLayer() {
        return true;
    }
}
