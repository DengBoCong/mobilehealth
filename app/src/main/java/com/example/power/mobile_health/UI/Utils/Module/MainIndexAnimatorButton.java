package com.example.power.mobile_health.UI.Utils.Module;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.widget.Button;

import com.example.power.mobile_health.R;

/**
 * Created by Power on 2019/1/14.
 */

public class MainIndexAnimatorButton extends Button {
    private int width;
    private int height;
    private boolean isMove;
    private GradientDrawable gradientDrawable;


    public MainIndexAnimatorButton(Context context){
        super(context);
        init(context);
    }

    public MainIndexAnimatorButton(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init(context);
    }

    public MainIndexAnimatorButton(Context context, AttributeSet attributeSet, int deStyleAttr){
        super(context, attributeSet, deStyleAttr);
        init(context);
    }

    private void init(Context context){
        isMove = false;
        gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(context.getResources().getColor(R.color.colorBlue));
        gradientDrawable.setCornerRadius(120);
        setBackground(gradientDrawable);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        if(widthMode == MeasureSpec.EXACTLY){
            width = widthSize;
        }
        if(heightMode == MeasureSpec.EXACTLY){
            height = heightSize;
        }
    }

    public void startAnim(float StartX, float EndX, float StartY, float EndY){
        ObjectAnimator objectAnimator;
        ObjectAnimator objectAnimator1;
        ObjectAnimator objectAnimator2;

        if(isMove){
            isMove = false;
            objectAnimator = ObjectAnimator.ofFloat(this, "alpha", 1);
            objectAnimator1 = ObjectAnimator.ofFloat(this, "translationX", EndX, StartX);
            objectAnimator2 = ObjectAnimator.ofFloat(this, "translationY", EndY, StartY);
        }else{
            isMove = true;
            objectAnimator = ObjectAnimator.ofFloat(this, "alpha", 0);
            objectAnimator1 = ObjectAnimator.ofFloat(this, "translationX", StartX, EndX);
            objectAnimator2 = ObjectAnimator.ofFloat(this, "translationY", StartY, EndY);
        }

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(800);
        animatorSet.playTogether(objectAnimator, objectAnimator1, objectAnimator2);
        animatorSet.start();
    }
}
