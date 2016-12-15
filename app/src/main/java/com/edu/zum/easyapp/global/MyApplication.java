package com.edu.zum.easyapp.global;

import android.app.Application;
import android.content.Context;

import easemob.helpdeskdemo.DemoHelper;
/**
 * Created by lwh on 2016/3/30.
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;
    public static Context applicationContext;

    // login user name
    public final String PREF_USERNAME = "username";

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        applicationContext = this;
        // init demo helper
        DemoHelper.getInstance().init(applicationContext);
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
