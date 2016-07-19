package com.edu.zum.easyapp.utils;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * Created by lwh on 2016/7/18.
 */
public class ValueAnimUtil {
    public static void sendGift(final View anim_ll) {
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer i = (Integer) animation.getAnimatedValue();
                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(anim_ll, "translationX", i, 0.0f);
                objectAnimator.start();
            }
        });
    }
}
