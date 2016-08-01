package com.edu.zum.easyapp.interactor;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;

/**
 * Created by lwh on 2016/8/1.
 */
public class WordClickableSpan extends ClickableSpan {
    private final static String TAG = WordClickableSpan.class.getSimpleName();
    private String word;
    private int start;
    private int color;

    public WordClickableSpan(String word, int start, int color) {
        this.word = word;
        this.start = start;
        this.color = color;
    }

    @Override
    public void onClick(View widget) {
        System.out.println(this.word);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        super.updateDrawState(ds);
        ds.setColor(color);
        ds.setUnderlineText(false);
    }
}
