package com.example.power.mobile_health.Utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.animation.LinearInterpolator;
import android.widget.Button;

import com.example.power.mobile_health.R;

import java.sql.Time;

/**
 * Created by Power on 2019/1/7.
 */

public class AnimatorButton extends Button {
    private int width;
    private int height;
    private GradientDrawable gradientDrawable;
    private boolean isMorphing;
    private int startAngle;
    private int judgeAngle;
    private Paint paint;
    private ValueAnimator arcValueAnimator;

    public AnimatorButton(Context context){
        super(context);
        init(context);
    }

    public AnimatorButton(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        init(context);
    }

    public AnimatorButton(Context context, AttributeSet attributeSet, int deStyleAttr){
        super(context, attributeSet, deStyleAttr);
        init(context);
    }

    private void init(Context context){
        isMorphing = false;
        gradientDrawable = new GradientDrawable();
        int colorDrawable = context.getResources().getColor(R.color.colorBlue);
        gradientDrawable.setColor(colorDrawable);
        gradientDrawable.setCornerRadius(80);
        setBackground(gradientDrawable);
        setText("立即检测");
        setTextSize(20);

        paint = new Paint();
        paint.setColor(context.getResources().getColor(R.color.colorWhile));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setTextSize(2);
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

    public void startAnim(){
        isMorphing = true;

        setText("100");
        ValueAnimator valueAnimator = ValueAnimator.ofInt(width, height);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int value = (int)animation.getAnimatedValue();
                int leftOffset = (width - value) / 2;
                int rightOffset = width - leftOffset;

                gradientDrawable.setBounds(leftOffset, 0, rightOffset, height);
            }
        });
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(gradientDrawable, "cornerRadius", 80, height/2);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(800);
        animatorSet.playTogether(valueAnimator, objectAnimator);
        animatorSet.start();

        //画中间的白色圆圈
        showArc();
    }

    public void gotoNew(){
        isMorphing = false;
        arcValueAnimator.cancel();
        setVisibility(GONE);
    }

    public void regainBackground(){
        setVisibility(VISIBLE);
        gradientDrawable.setBounds(0, 0, width, height);
        gradientDrawable.setCornerRadius(80);
        setBackground(gradientDrawable);
        setText("立即检测");
        isMorphing = false;
    }

    private void showArc(){
        setTextSize(30);
        arcValueAnimator = ValueAnimator.ofInt(width, height);
        arcValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                startAngle = (int)animation.getAnimatedValue();
                /*Log.i("a", ":" + (startAngle - 401)/79.0);*/
                judgeAngle = Math.abs(startAngle - 440);
                /*Log.i("a", "" + judgeAngle);*/
                invalidate();
            }
        });
        arcValueAnimator.setInterpolator(new LinearInterpolator());
        arcValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        arcValueAnimator.setDuration(1500);
        arcValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(isMorphing == true){
            final RectF rectF = new RectF(getWidth()/8, getHeight()/20, getWidth()*7/8, getHeight()-getHeight()/20);
            final RectF rectFS = new RectF(getWidth()/6, getHeight()/10, getWidth()*5/6, getHeight()-getHeight()/10);
            canvas.drawArc(rectF, startAngle*(float)4.5, (float) (judgeAngle/40.0)*120, false, paint);
            canvas.drawArc(rectF, startAngle*(float)4.5 + 180, (float) (judgeAngle/40.0)*120, false, paint);
            canvas.drawArc(rectFS, -startAngle*(float)4.5 + 90, (float) (judgeAngle/40.0)*80, false, paint);
            canvas.drawArc(rectFS, -startAngle*(float)4.5 - 90, (float) (judgeAngle/40.0)*80, false, paint);
        }
    }
}
