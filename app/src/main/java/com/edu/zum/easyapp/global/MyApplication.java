package com.edu.zum.easyapp.global;

import android.app.Application;
//import android.provider.Settings;
//
//import com.edu.zum.easyapp.utils.ImageLoaderHelper;
//import com.edu.zum.easyapp.utils.LocalDisplay;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

/**
 * Created by lwh on 2016/3/30.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        ImageLoader.getInstance().init(
//                ImageLoaderHelper.getInstance(this)
//                        .getImageLoaderConfiguration(Constants.IMAGE_LOADER_CACHE_PATH));
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
