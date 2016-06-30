/**
 *
 */
package com.edu.zum.easyapp.utils;

import android.widget.Toast;

import com.edu.zum.easyapp.global.MyApplication;

public class ToastUtil {

    private static Toast toast;

    static {
        toast = Toast.makeText(MyApplication.getInstance().getApplicationContext(), "", Toast.LENGTH_SHORT);
    }

    public static void show(String info) {
        toast.setText(info);
        showToast();
    }

    public static void show(int info) {
        toast.setText(info);
        showToast();
    }


    private static void showToast() {
        toast.show();
    }
}
