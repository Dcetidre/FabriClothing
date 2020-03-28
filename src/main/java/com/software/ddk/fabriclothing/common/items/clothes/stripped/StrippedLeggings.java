package com.software.ddk.fabriclothing.common.items.clothes.stripped;

import com.software.ddk.fabriclothing.common.items.clothes.generic.BaseLeggings;

public class StrippedLeggings extends BaseLeggings {
    @Override
    public String clothId() {
        return "type1";
    }

    @Override
    public boolean multiLayer() {
        return true;
    }
}
