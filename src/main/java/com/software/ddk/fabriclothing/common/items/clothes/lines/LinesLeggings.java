package com.software.ddk.fabriclothing.common.items.clothes.lines;

import com.software.ddk.fabriclothing.common.items.generic.PantalonItem;

public class LinesLeggings extends PantalonItem {

    @Override
    public String clothId() {
        return "type2";
    }

    @Override
    public boolean multiLayer() {
        return true;
    }
}
