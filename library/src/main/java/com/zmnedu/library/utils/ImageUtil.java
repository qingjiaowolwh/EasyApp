package com.zmnedu.library.utils;

import android.content.Context;
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

    public static void displayImageCircle(Context mContext, String url, ImageView imageView) {
        Glide.with(mContext).load(url).centerCrop().bitmapTransform(new GlideCircleTransform(mContext)).into(imageView);
    }

}
