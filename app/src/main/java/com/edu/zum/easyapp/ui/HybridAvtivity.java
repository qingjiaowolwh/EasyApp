package com.edu.zum.easyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.edu.zum.easyapp.R;

/**
 * Created by lwh on 2016/9/23.
 */
public class HybridAvtivity extends BaseActivity{

    public static void actionStart(Context mContext) {
        Intent i = new Intent(mContext, HybridAvtivity.class);
        mContext.startActivity(i);
    }
    private WebView webView;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_hybird;
    }

    @Override
    protected void setUpView() {
        setStateContent();

        webView= (WebView) findViewById(R.id.webView);
        //支持JS
        WebSettings webSettings=webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

}
