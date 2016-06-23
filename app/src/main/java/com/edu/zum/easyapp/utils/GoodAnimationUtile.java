package com.edu.zum.easyapp.utils;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;

import com.edu.zum.easyapp.R;

import java.text.NumberFormat;


public class GoodAnimationUtile {

	/*
     * 动画逻辑
	 * 动画时长分为 3.5秒  ,  
	 * 
	 * 0-3.5 秒 向上 位移
	 * 
	 * 0- 0.8 秒     放大   位移 0.5
	 * 
	 * 1-2  秒         左右 位移 
	 * 
	 * 2-3 秒      左右位移
	 *  
	 * 2.6-3.5秒    渐隐消失
	 *   
	 */

    public static AnimationSet createAnimation(Context context) {

        AnimationSet animSet = new AnimationSet(false);

        Animation anim = AnimationUtils.loadAnimation(context, R.anim.live_im_goodmsg_base);
        animSet.addAnimation(anim);

        animSet.addAnimation(getTranceAnim01(context));
        animSet.addAnimation(getTranceAnim12(context));
        animSet.addAnimation(getTranceAnim23(context));
        animSet.addAnimation(getTranceAnim34(context));

        return animSet;

    }

    public static Animation getTranceAnim01(Context context) {

        TranslateAnimation inAnim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0.0f, TranslateAnimation.RELATIVE_TO_SELF, getRandParam(), TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0f);
        inAnim.setDuration(700);
        inAnim.setStartOffset(300);

        return inAnim;
    }

    public static Animation getTranceAnim12(Context context) {

        TranslateAnimation inAnim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, getRandParam(), TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0f);
        inAnim.setDuration(1000);
        inAnim.setStartOffset(1000);
        return inAnim;
    }

    public static Animation getTranceAnim23(Context context) {

        TranslateAnimation inAnim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, getRandParam(), TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0f);
        inAnim.setDuration(1000);
        inAnim.setStartOffset(2000);
        return inAnim;
    }

    public static Animation getTranceAnim34(Context context) {

        TranslateAnimation inAnim = new TranslateAnimation(TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, getRandParam(), TranslateAnimation.RELATIVE_TO_SELF, 0, TranslateAnimation.RELATIVE_TO_SELF, 0f);
        inAnim.setDuration(1000);
        inAnim.setStartOffset(3000);
        return inAnim;
    }

    public static float getRandParm01() {
        NumberFormat n = NumberFormat.getInstance();
        n.setMaximumFractionDigits(1);
        float f = Float.parseFloat(n.format(Math.random()));
        float fre = (float) ((f - 0.5) * 0.5);
        return fre;
    }

    public static float getRandParam() {
        NumberFormat n = NumberFormat.getInstance();
        n.setMaximumFractionDigits(1);
        float f = Float.parseFloat(n.format(Math.random()));
        float fre = (float) (f - 0.5);
        return fre;
    }
}
