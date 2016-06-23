package com.edu.zum.easyapp.utils;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

import com.edu.zum.easyapp.global.MyApplication;
import com.edu.zum.easyapp.model.UserInfo;
import com.google.gson.Gson;

public class PreferenceHelper {

    // 用户信息
    public static String USERINFO = "user_info";

    public static UserInfo getUserInfo() {
        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        String str = settings.getString(USERINFO, null);
        if (str == null)
            return null;
        else {
            UserInfo info = new Gson().fromJson(str, UserInfo.class);
            return info;
        }
    }

    public static void setUserInfo(UserInfo info) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        sp.edit().putString(USERINFO, new Gson().toJson(info)).commit();
    }

    public static void removeUserInfo() {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        sp.edit().remove(USERINFO).commit();
    }

    public static boolean getBoolean(String key, boolean defValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        return sp.getBoolean(key, defValue);
    }

    public static void putBoolean(String key, boolean value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        sp.edit().putBoolean(key, value).commit();
    }

    public static int getInt(String key, int defValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        return sp.getInt(key, defValue);
    }

    public static void putInt(String key, int value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        sp.edit().putInt(key, value).commit();
    }

    public static long getLong(String key, long defValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        return sp.getLong(key, defValue);
    }

    public static void putLong(String key, long value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        sp.edit().putLong(key, value).commit();
    }

    public static String getString(String key, String defValue) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        return sp.getString(key, defValue);
    }

    public static void putString(String key, String value) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        sp.edit().putString(key, value).commit();
    }

    public static void remove(String key) {
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance());
        sp.edit().remove(key).commit();
    }

    public static void registerOnPrefChangeListener(OnSharedPreferenceChangeListener listener) {
        try {
            PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance())
                    .registerOnSharedPreferenceChangeListener(listener);
        } catch (Exception e) {
            //
        }
    }

    public static void unregisterOnPrefChangeListener(OnSharedPreferenceChangeListener listener) {
        try {
            PreferenceManager.getDefaultSharedPreferences(MyApplication.getInstance())
                    .unregisterOnSharedPreferenceChangeListener(listener);
        } catch (Exception e) {
            //
        }
    }

}
