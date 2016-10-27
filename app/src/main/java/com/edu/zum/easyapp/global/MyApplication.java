package com.edu.zum.easyapp.global;

import android.app.Application;
import android.content.Context;

import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import easemob.helpdeskdemo.DemoHelper;

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
    public static Context applicationContext;

    // login user name
    public final String PREF_USERNAME = "username";

    public static RefWatcher getRefWatcher(Context context) {
//        MyApplication application = (MyApplication) context.getApplicationContext();
        return mInstance.refWatcher;
    }

    private RefWatcher refWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.init("hello");
        refWatcher = LeakCanary.install(this);
        mInstance = this;
        applicationContext = this;
        // init demo helper
        DemoHelper.getInstance().init(applicationContext);
//        ImageLoader.getInstance().init(
//                ImageLoaderHelper.getInstance(this)
//                        .getImageLoaderConfiguration(Constants.IMAGE_LOADER_CACHE_PATH));
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
