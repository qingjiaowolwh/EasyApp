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

    public static ObjectAnimator translationBottom(View v, float y) {
        return ObjectAnimator.ofFloat(v, "translationY", 0.0f, y);
    }

    public static ObjectAnimator translationTop(View v, float y) {
        return ObjectAnimator.ofFloat(v, "translationY", y, 0.0f);
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

    //送礼物
    public static AnimatorSet sendGift(View anim_ll, View anim_img, View anim_count, int count) {
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translationLeft(anim_ll, -anim_ll.getWidth())).before(translationLeft(anim_img, -anim_ll.getWidth()));
        animSet.play(alfaAppear(anim_img)).with(translationLeft(anim_img, -anim_ll.getWidth()));
        animSet.play(translationLeft(anim_count, -anim_ll.getWidth())).after(alfaAppear(anim_img));
        animSet.play(alfaAppear(anim_count)).with(translationLeft(anim_count, -anim_ll.getWidth()));
        animSet.play(scaleX(anim_count, 3)).after(alfaAppear(anim_count));
        animSet.play(scaleX(anim_count, 3)).with(scaleY(anim_count, 3));
        animSet.play(translationTop(anim_ll, -anim_ll.getHeight() * 3)).after(scaleX(anim_count, 3));
        animSet.play(alfaDisappear(anim_ll)).with(translationTop(anim_ll, -anim_ll.getHeight() * 3));
        return animSet;
    }

    public static AnimatorSet translationBottom(View bottom1, float b1, View bottom2, float b2, View bottom3, float b3) {
        AnimatorSet animSet = new AnimatorSet();
        return animSet;
    }

}
