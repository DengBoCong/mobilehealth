package com.example.power.mobile_health.Utils.Module;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Power on 2019/1/18.
 */

public class NoSlideViewPager extends ViewPager {
    private boolean noScroll = true;

    public NoSlideViewPager(Context context){
        super(context);
    }

    public NoSlideViewPager(Context context, AttributeSet attributeSet){
        super(context, attributeSet);
    }

    public void setNoScroll(boolean noScroll){
        this.noScroll = noScroll;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(noScroll){
            return false;
        }else{
            return super.onTouchEvent(ev);
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if(noScroll){
            return false;
        }else{
            return super.onInterceptTouchEvent(ev);
        }
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item);
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
}
