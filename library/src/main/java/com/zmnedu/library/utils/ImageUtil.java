package com.zmnedu.library.utils;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zmnedu.library.transform.GlideCircleTransform;

/**
 * Created by lwh on 2016/7/5.
 */
public class ImageUtil {
    public static void displayImage(Context mContext, String url, ImageView mImageView) {
        Glide.with(mContext).load(url).centerCrop().into(mImageView);
    }

    public static void displayImage(Activity mActivity, String url, ImageView mImageView) {
        Glide.with(mActivity).load(url).centerCrop().into(mImageView);
    }

    public static void displayImage(Fragment mFragment, String url, ImageView mImageView) {
        Glide.with(mFragment).load(url).centerCrop().into(mImageView);
    }


    public static void displayImageCircle(Activity mActivity, String url, ImageView imageView) {
        Glide.with(mActivity).load(url).centerCrop().bitmapTransform(new GlideCircleTransform(mActivity)).into(imageView);
    }

    public static void displayImageCircle(Fragment mFragment, String url, ImageView imageView) {
        Glide.with(mFragment).load(url).centerCrop().bitmapTransform(new GlideCircleTransform(mFragment.getContext())).into(imageView);
    }

    public static void displayImageCircle(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext).load(url).centerCrop().bitmapTransform(new GlideCircleTransform(mContext)).into(imageView);
    }


}
