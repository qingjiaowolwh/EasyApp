package com.edu.zum.easyapp.base;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;

/**
 * Created by lwh on 2016/7/13.
 */
public class AnimatorUtil {
    public static ObjectAnimator rotationCloseToRight(View v) {
        return ObjectAnimator.ofFloat(v, "rotationY", 0, -90);
    }

    public static ObjectAnimator rotationOpenFromRight(View v) {
        return ObjectAnimator.ofFloat(v, "rotationY", -90, 0);
    }

    public static ObjectAnimator rotationCloseVertical(View v) {
        return ObjectAnimator.ofFloat(v, "rotationX", 0, -90);
    }

    public static ObjectAnimator rotationOpenVertical(View v) {
        return ObjectAnimator.ofFloat(v, "rotationX", -90, 0);
    }

    public static ObjectAnimator translationLeft(View v, float x) {
        return ObjectAnimator.ofFloat(v, "translationX", x, 0.0f);
    }

    public static ObjectAnimator scaleX(View v, float x) {
        return ObjectAnimator.ofFloat(v, "scaleX", x);
    }

    public static ObjectAnimator scaleY(View v, float y) {
        return ObjectAnimator.ofFloat(v, "scaleY", y);
    }

    public static ObjectAnimator translationRight(View v, float x) {
        return ObjectAnimator.ofFloat(v, "translationX", 0.0f, x);
    }

    public static ObjectAnimator alfaDisappear(View v) {
        return ObjectAnimator.ofFloat(v, "alpha", 1, 0);
    }

    public static ObjectAnimator alfaAppear(View v) {
        return ObjectAnimator.ofFloat(v, "alpha", 0, 1);
    }

    public static AnimatorSet translationPK(View left, float l, View right, float r) {
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translationRight(left, l)).with(translationRight(right, r));
        animSet.playTogether(alfaAppear(left), alfaAppear(right));
        animSet.setDuration(500);
        return animSet;
    }

    public static AnimatorSet animCountDown(View tv, float s) {
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(scaleX(tv, s)).with(scaleY(tv, s));
        animSet.play(alfaDisappear(tv)).with(scaleX(tv, s));
        animSet.setDuration(500);
        return animSet;
    }

    public static AnimatorSet translationBottom(View bottom1, float b1, View bottom2, float b2, View bottom3, float b3) {
        AnimatorSet animSet = new AnimatorSet();
        return animSet;
    }

}
