package com.edu.zum.easyapp.widgets;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by lwh on 2016/8/3.
 */
public class CustomPopupWindow extends PopupWindow {
    private Context mContext;
    private View mContentView, mView;
    private int width, height;
    private int resID;

    private boolean backDismiss = true;

    public CustomPopupWindow(Context context, View mContentView, View mView) {
        this.mContentView = mContentView;
        this.mContext = context;
        this.mView = mView;
    }

    public CustomPopupWindow(Context context, int resID, View mView) {
        this.resID = resID;
        this.mContext = context;
        this.mView = mView;
    }

    /**
     * 设置后响应返回dismiss
     *
     * @return
     */
    public CustomPopupWindow setBackDismiss(boolean backDismiss) {
        this.backDismiss = backDismiss;
        return this;
    }

    /**
     * 设置透明度 上下文需要Activity
     *
     * @param alpha
     * @return
     */
    public CustomPopupWindow setCustomBackground(float alpha) {
        setPopBackground(alpha);
//      半透明  setPopBackground(0.7f);
        return this;
    }

    public void setUpPop() {
        if (width == 0)
            setWidth(width = mContext.getResources().getDisplayMetrics().widthPixels);
        if (height == 0)
            setHeight(height = mContext.getResources().getDisplayMetrics().heightPixels);
        if (mContentView == null)
            mContentView = LayoutInflater.from(mContext).inflate(resID, null);
        setContentView(mContentView);
        setFocusable(true);
        setOutsideTouchable(true);
        if (backDismiss) {
            setBackgroundDrawable(new BitmapDrawable());
        }
    }


    public CustomPopupWindow showDown() {
        setUpPop();
        showAsDropDown(mView);
        return this;
    }

    public CustomPopupWindow showCenter() {
        setUpPop();
        showAtLocation(mView, Gravity.CENTER, 0, 0);
        return this;
    }

    public CustomPopupWindow showBottom() {
        setUpPop();
        showAtLocation(mView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        return this;
    }

    /**
     * 设置添加屏幕的背景透明度
     * setPopBackground(1.0f);
     *
     * @param bgAlpha
     */
    private void setPopBackground(float bgAlpha) {
        ((Activity) mContext).getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams lp = ((Activity) mContext).getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        ((Activity) mContext).getWindow().setAttributes(lp);
    }

}
