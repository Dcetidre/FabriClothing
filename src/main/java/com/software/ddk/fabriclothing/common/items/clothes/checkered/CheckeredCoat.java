package com.software.ddk.fabriclothing.common.items.clothes.checkered;

import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseCoat;

public class CheckeredCoat extends BaseCoat {
    @Override
    public String clothId() {
        return "type3";
    }

    @Override
    public boolean multiLayer() {
        return true;
    }
}
