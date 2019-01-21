package com.example.power.mobile_health.UI.Model;

/**
 * Created by Power on 2019/1/21.
 */

public class TIModel {
    private String text;
    private int image;

    public TIModel(String text, int image){
        this.image = image;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
