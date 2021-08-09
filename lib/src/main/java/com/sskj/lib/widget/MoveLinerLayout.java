package com.sskj.lib.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

public class MoveLinerLayout extends LinearLayout {

    private OnRemoveListener onRemoveListener;

    public void setOnRemoveListener(OnRemoveListener onRemoveListener) {
        this.onRemoveListener = onRemoveListener;
    }

    private int beginX;
    private int moveAllX = 0;
    private int width;
    private boolean isDeal = false;
    private boolean isFirst = true;
    private int startLeft;

    public MoveLinerLayout(Context context) {
        super(context);
    }

    public MoveLinerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MoveLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getRawX();//获取触摸位置
        switch (event.getAction()) {
            //触摸屏幕
            case MotionEvent.ACTION_DOWN:
                moveAllX=0;
                if (isFirst) {
                    isFirst = false;
                    startLeft = getLeft();
                }
                beginX = x;
                width = getRight() - getLeft();
                isDeal = false;
                break;

            //在屏幕上拖动
            case MotionEvent.ACTION_MOVE:
                //计算拖动距离
                int moveX = x - beginX;
                moveAllX += moveX;
                layout(getLeft() + moveX, getTop(), getRight() + moveX, getBottom());
                beginX = x;
                isDeal = true;
                break;

            //触摸离开屏幕
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                if (Math.abs(moveAllX) < width / 2) {

                    ValueAnimator anim = ValueAnimator.ofInt(moveAllX, 0);
                    anim.setDuration(400);
                    anim.addUpdateListener(animation -> {
                        int currentValue = (int) animation.getAnimatedValue();
                        layout(startLeft + currentValue, getTop(), startLeft + currentValue + width, getBottom());

                    });
                    anim.start();
                } else {
                    int end = moveAllX > 0 ? width : -width;

                    ValueAnimator anim = ValueAnimator.ofInt(moveAllX, end);
                    anim.setDuration(400);
                    anim.addUpdateListener(animation -> {
                        int currentValue = (int) animation.getAnimatedValue();
                        layout(startLeft + currentValue, getTop(), startLeft + currentValue + width, getBottom());
                        if (currentValue == end) {
                            setVisibility(View.GONE);
                            if (onRemoveListener != null) {
                                layout(startLeft, getTop(), startLeft + width, getBottom());
                                onRemoveListener.onRemove();
                            }
                        }
                    });


                    anim.start();
                }
                break;
        }
        if (isDeal) {
            return true;
        } else {
            return super.onTouchEvent(event);
        }
    }

    public interface OnRemoveListener {
        void onRemove();
    }

}
