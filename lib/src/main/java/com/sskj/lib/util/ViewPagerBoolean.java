package com.sskj.lib.util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 *
 */
public class ViewPagerBoolean extends ViewPager {
    private boolean scrollable = true;
    private  float lastX=0;
    private  float last=0;
    private OnScrollListener onScrollListener;

    public boolean isScrollable() {
        return scrollable;
    }

    public void setScrollable(boolean scrollable) {
        this.scrollable = scrollable;
    }

    public ViewPagerBoolean(Context context) {
        super(context);
    }

    public ViewPagerBoolean(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!scrollable) {
            return false;
        }
        return super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()){
            case MotionEvent.ACTION_MOVE:
                float x=ev.getX();
                float y=ev.getY();

                if (x<lastX){  // 右滑动
                    if (onScrollListener!=null){
                        if (!onScrollListener.onScrollRight()){
                            return  false;
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_DOWN:
                lastX=ev.getX();
                break;
        }

        if (!scrollable) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }


    public interface  OnScrollListener{
        boolean onScrollRight();
    }

    public  void setListener(OnScrollListener onScrollListener){
        this.onScrollListener=onScrollListener;
    }


    public boolean isCanScrollble() {
        return scrollable;
    }

    public void setCanScrollble(boolean scrollble) {
        this.scrollable = scrollble;
    }
}
