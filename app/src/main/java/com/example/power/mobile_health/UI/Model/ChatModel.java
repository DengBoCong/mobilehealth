package com.example.power.mobile_health.UI.Model;

import java.io.Serializable;

/**
 * Created by Power on 2019/1/19.
 */

public class ChatModel implements Serializable {
    private String icon="";
    private String content="";
    private String type="";

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
