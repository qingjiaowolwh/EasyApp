package com.edu.zum.easyapp.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by lwh on 2016/6/22.
 */
public class AnimUtil {
    public static AnimatorSet startTranslationLX(Context mContext, View view) {
        view.setX(-view.getWidth());
        view.setY(ScreenUtils.getScreenHeight(mContext) / 3 - view.getHeight());
        float currentX = view.getTranslationX();
        ObjectAnimator translationX = ObjectAnimator.ofFloat(view, "translationX", currentX, 0.0f);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translationX);
        animSet.start();
        return null;
    }

    public static AnimatorSet startTranslationPK(Context mContext, View leftview, View rightview) {
        leftview.setX(-leftview.getWidth());
        leftview.setY(ScreenUtils.getScreenHeight(mContext) / 3 - leftview.getHeight());
        float currentXL = leftview.getTranslationX();
        ObjectAnimator translationXL = ObjectAnimator.ofFloat(leftview, "translationX", currentXL, 0.0f);

        rightview.setX(ScreenUtils.getScreenWidth(mContext));
        rightview.setY(ScreenUtils.getScreenHeight(mContext) * 2 / 3 - rightview.getHeight());
        float currentXR = rightview.getTranslationX();
        ObjectAnimator translationXR = ObjectAnimator.ofFloat(rightview, "translationX", currentXR, currentXR - rightview.getWidth());
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translationXL).with(translationXR);
        animSet.start();
        return animSet;
    }

    public static AnimatorSet startTranslationBottom(Context mContext, View bottom1, View bottom2, View bottom3) {
        bottom1.setY(ScreenUtils.getScreenHeight(mContext));
        float currentY1 = bottom1.getTranslationY();
        ObjectAnimator translationY1 = ObjectAnimator.ofFloat(bottom1, "translationY", currentY1, 360f);
        translationY1.setDuration(500);
        translationY1.setInterpolator(new AccelerateInterpolator());

        bottom2.setY(ScreenUtils.getScreenHeight(mContext));
        float currentY2 = bottom2.getTranslationY();
        ObjectAnimator translationY2 = ObjectAnimator.ofFloat(bottom2, "translationY", currentY2, 720f);
        translationY2.setDuration(500);

        bottom3.setY(ScreenUtils.getScreenHeight(mContext));
        float currentY3 = bottom3.getTranslationY();
        ObjectAnimator translationY3 = ObjectAnimator.ofFloat(bottom3, "translationY", currentY3, 0f);
        translationY3.setDuration(500);

        AnimatorSet animSet = new AnimatorSet();
        animSet.play(translationY2).after(translationY1);
        animSet.play(translationY3).after(translationY2);
        animSet.start();
        return animSet;
    }
}
