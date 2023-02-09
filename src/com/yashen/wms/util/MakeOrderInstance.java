package com.yashen.wms.util;

import com.yashen.wms.contraller.MakeOrderContraller;

public class MakeOrderInstance {
    public static MakeOrderContraller makeOrderContraller;
    public static MakeOrderContraller getMakeOrderContrallerInstance(){
        if (makeOrderContraller == null) {
            makeOrderContraller = new MakeOrderContraller();
        }
        return makeOrderContraller;
    }

}
