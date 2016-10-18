package com.edu.zum.easyapp.fragment;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.edu.zum.easyapp.R;

/**
 * Created by lwh on 2016/9/20.
 */
public class WebFragment extends BaseFragment {
    private WebView mWebView;
    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_web;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        mWebView= (WebView) getView().findViewById(R.id.webView);
        //支持JS
        WebSettings webSettings=mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mWebView.destroy();
    }
}
