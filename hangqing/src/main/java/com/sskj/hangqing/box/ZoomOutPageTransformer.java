package com.sskj.hangqing.box;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * 实现的原理是，在当前显示页面放大至原来的MAX_SCALE
 * 其他页面才是正常的的大小MIN_SCALE
 */
public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
    @Override
    public void transformPage(View view, float position) {

        float distance = 1 - Math.abs(position);
        if (position < 1 && position > -1) {
            float v = distance * 0.1f;
            view.setScaleX(0.9f + v);
            view.setScaleY(0.9f + v);
        }
        if (position <= -1) {
            view.setScaleX(0.9f);
            view.setScaleY(0.9f);
        }
        if (position >= 1) {
            view.setScaleX(0.9f);
            view.setScaleY(0.9f);
        }
    }
}

