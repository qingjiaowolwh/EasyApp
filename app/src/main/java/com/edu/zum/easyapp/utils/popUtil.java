package com.edu.zum.easyapp.utils;

import android.app.Activity;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.IdRes;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

/**
 * Created by lwh on 2016/7/7.
 */
public class PopUtil {
    private PopupWindow mPopupWindow;
    private Activity mContext;
    private View mContent, mView;
    private int resID;
    private int width, height;

    /**
     * @param mContext
     * @param mContent
     * @param mView
     */
    public PopUtil(Activity mContext, View mContent, View mView) {
        this.mContext = mContext;
        this.mContent = mContent;
        this.mView = mView;
        width = mContext.getResources().getDisplayMetrics().widthPixels;
        height = mContext.getResources().getDisplayMetrics().heightPixels;
        if (mContent == null)
            mContent = LayoutInflater.from(mContext).inflate(resID, null);
        mPopupWindow = new PopupWindow(mContent, width, height);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    /**
     * @param mContext
     * @param resID
     * @param mView
     */
    public PopUtil(Activity mContext, @IdRes int resID, View mView) {
        this.mContext = mContext;
        this.resID = resID;
        this.mView = mView;
        width = mContext.getResources().getDisplayMetrics().widthPixels;
        height = mContext.getResources().getDisplayMetrics().heightPixels;
        if (mContent == null)
            mContent = LayoutInflater.from(mContext).inflate(resID, null);
        mPopupWindow = new PopupWindow(mContent, width, height);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
    }

    public PopUtil setHeitht(int height) {
        this.height = height;
        return this;
    }

    public PopUtil setWidtht(int width) {
        this.width = width;
        return this;
    }

    public PopUtil setOnDismissListener(PopupWindow.OnDismissListener dismissListener) {
        mPopupWindow.setOnDismissListener(dismissListener);
        return this;
    }

    public void dismiss() {
        if (mPopupWindow.isShowing())
            mPopupWindow.dismiss();
    }

    public PopUtil setBackground(float alpha) {
        setPopBackground(alpha);
//      半透明  setPopBackground(0.7f);
        return this;
    }


    public void showDown() {
        mPopupWindow.showAsDropDown(mView);
    }

    public void showCenter() {
        mPopupWindow.showAtLocation(mView, Gravity.CENTER, 0, 0);
    }

    public void showBottom() {

        mPopupWindow.showAtLocation(mView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    /**
     * 设置添加屏幕的背景透明度
     * setPopBackground(1.0f);
     *
     * @param bgAlpha
     */
    public void setPopBackground(float bgAlpha) {
        mContext.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        WindowManager.LayoutParams lp = mContext.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        mContext.getWindow().setAttributes(lp);
    }
}
