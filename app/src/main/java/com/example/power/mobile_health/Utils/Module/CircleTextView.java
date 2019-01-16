package com.example.power.mobile_health.Utils.Module;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Power on 2019/1/15.
 */

public class CircleTextView extends TextView {
    private GradientDrawable gradientDrawable;

    public CircleTextView(Context context){
        super(context);
    }

    public CircleTextView(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    public CircleTextView(Context context, AttributeSet attributeSet, int deStyleAttr){
        super(context, attributeSet, deStyleAttr);
    }

    public void defineDrawable(int cornerRadius, int colorId){
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(getResources().getColor(colorId));
        gradientDrawable.setCornerRadius(cornerRadius);
        setBackground(gradientDrawable);

    }
}
