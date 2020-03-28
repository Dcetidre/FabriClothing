package com.software.ddk.fabriclothing.common.items.clothes.dotted;

import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseShirt;

public class DottedShirt extends BaseShirt {
    @Override
    public String clothId() {
        return "type5";
    }

    @Override
    public boolean multiLayer() {
        return true;
    }
}
