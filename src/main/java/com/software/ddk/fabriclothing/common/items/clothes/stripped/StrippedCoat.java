package com.software.ddk.fabriclothing.common.items.clothes.stripped;

import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseCoat;

public class StrippedCoat extends BaseCoat {

    @Override
    public String clothId() {
        return "type1";
    }

    @Override
    public boolean multiLayer() {
        return true;
    }

}
