package com.example.power.mobile_health.UI.Model;

import java.io.Serializable;

/**
 * Created by Power on 2019/1/19.
 */

public class ItemModel implements Serializable {
    public static final int CHAT_A = 1001;
    public static final int CHAT_B = 1002;
    public int type;
    public Object object;

    public ItemModel(int type, Object object) {
        this.type = type;
        this.object = object;
    }
}
