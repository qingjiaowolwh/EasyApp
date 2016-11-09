package com.edu.zum.easyapp.widgets;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.Layout;
import android.text.Selection;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ViewDebug;
import android.widget.EditText;

import com.edu.zum.easyapp.utils.ToastUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lwh on 2016/11/9.
 */

public class ShowWordView extends EditText{
    // ---------三个构造----------------------------------------------$构造
    // 当设置,指定样式时调用
    public ShowWordView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    // 布局文件初始化的时候,调用-------该构造方法,重用------------★
    // 布局文件里面定义的属性都放在 AttributeSet attrs
    public ShowWordView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    // 该方法,一般,在代码中 new 该类的时候_使用
    public ShowWordView(Context context) {
        super(context);
    }

    // 单词提取
    /**
     * @param layout
     * @param x      相对自己ev.getX()
     * @param y
     * @return
     */
    public int extractWordCurOff(Layout layout, int x, int y) {
        int line;
        line = layout
                .getLineForVertical(getScrollY() + y - 10);
        int curOff = layout.getOffsetForHorizontal(line, x);
        return curOff;
    }

    public String getSelectWord(Editable content, int curOff) {
        String word = "";
        int start = getWordLeftIndex(content, curOff);
        int end = getWordRightIndex(content, curOff);
        if (start >= 0 && end >= 0&&start!=end) {
            word = content.subSequence(start, end).toString();
            if (!"".equals(word)) {
                // setFocusable(false);
                setFocusableInTouchMode(true);
                requestFocus();
                Selection.setSelection(content, start, end);// 设置当前具有焦点的文本字段的选择范围,当前文本必须具有焦点，否则此方法无效
            }
        }
        return word;
    }

    public int getWordLeftIndex(Editable content, int cur) {
        // --left
        String editableText = content.toString();// getText().toString();
        if (cur >= editableText.length())
            return cur;

        int temp = 0;
        if (cur >= 20)
            temp = cur - 20;
        Pattern pattern = Pattern.compile("[^'A-Za-z]");
        Matcher m = pattern.matcher(editableText.charAt(cur) + "");
        if (m.find())
            return cur;

        String text = editableText.subSequence(temp, cur).toString();
        int i = text.length() - 1;
        for (; i >= 0; i--) {
            Matcher mm = pattern.matcher(text.charAt(i) + "");
            if (mm.find())
                break;
        }
        int start = i + 1;
        start = cur - (text.length() - start);
        return start;
    }

    public int getWordRightIndex(Editable content, int cur) {
        // --right
        String editableText = content.toString();
        if (cur >= editableText.length())
            return cur;

        int templ = editableText.length();
        if (cur <= templ - 20)
            templ = cur + 20;
        Pattern pattern = Pattern.compile("[^'A-Za-z]");
        Matcher m = pattern.matcher(editableText.charAt(cur) + "");
        if (m.find())
            return cur;

        String text1 = editableText.subSequence(cur, templ).toString();
        int i = 0;
        for (; i < text1.length(); i++) {
            Matcher mm = pattern.matcher(text1.charAt(i) + "");
            if (mm.find())
                break;
        }
        int end = i;
        end = cur + end;
        return end;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN:
                System.out.println("---action down-----");
                String word = getSelectWord(getEditableText(), extractWordCurOff(getLayout(), (int)event.getX(), (int)event.getY()));
                ToastUtil.show(word);
                break;
            case MotionEvent.ACTION_MOVE:
                System.out.println("---action move-----");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("---action up-----");
        }
        return super.onTouchEvent(event);
    }

    // --------------------------------------------------------------$初始
    private void initialize(Context context) {
        setGravity(Gravity.CENTER);
        setBackgroundColor(Color.TRANSPARENT);// 背景透明-去掉底部输入框
    }

    @Override
    protected void onCreateContextMenu(ContextMenu menu) {
        // 不做任何处理，为了阻止长按的时候弹出上下文菜单
    }

    @Override
    public boolean getDefaultEditable() {
        return false;
    }

    // 其实当前控件并没有获得焦点，我只是欺骗Android系统，让Android系统以我获得焦点的方式去处理
    // 用于将该控件Add到其他View下,导致失去焦点.
    @Override
    @ViewDebug.ExportedProperty(category = "focus")
    public boolean isFocused() {
        return super.isFocused();// return true一定有焦点
    }
}
