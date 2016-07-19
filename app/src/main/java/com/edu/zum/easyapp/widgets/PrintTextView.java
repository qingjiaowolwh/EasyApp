package com.edu.zum.easyapp.widgets;

import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lwh on 2016/7/19.
 */
public class PrintTextView extends TextView {
    /**
     * 字体显示出来的时间
     */
    private int DURATION = 8000;

    public PrintTextView(Context context) {
        this(context, null);
    }

    public PrintTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PrintTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


    }

    public void printString(String str) {
        if (str != null && str != "") {            // 字符串的长度
            final int lenght = str.length();
            final char[] c = new char[str.length()];       //将字符串转换成字符数组
            for (int i = 0; i < str.length(); i++) {
                c[i] = str.charAt(i);
            }
            ValueAnimator animator = ValueAnimator.ofFloat(0, 1);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {          // 字体显示的速度 v = 总的字体长度 / 总的显示时间
                    float v = (float) lenght / (float) DURATION;                  // 动画执行速度
                    float fraction = (float) animation.getAnimatedValue();        //动画不同阶段字体应该显示的个数
                    int s = (int) (v * fraction * DURATION);
                    setText(c, 0, s);
                }
            });
            animator.setDuration(DURATION);
            animator.start();
        }
    }
}
