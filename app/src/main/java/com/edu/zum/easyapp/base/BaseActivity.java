package com.edu.zum.easyapp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.utils.NetworkUtil;
import com.edu.zum.easyapp.utils.StatusBarUtil;
import com.edu.zum.easyapp.utils.ToastUtil;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.zmnedu.library.widgets.MultiStateView;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;
    private MultiStateView multiStateView;
    private Handler mHandker = new Handler();
    private Runnable mRun;
    private View loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarUtil.initSystemBar(this);
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceID());
        //设置状态栏透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        init();
        loading = LayoutInflater.from(mContext).inflate(R.layout.layout_loading_view, null);
        initMultiStateView();
        setUpView();
        setUpData();
    }


    protected abstract void setUpView();

    protected void setUpData() {}

    protected void init() {
        mContext = this;
        PushAgent.getInstance(mContext).onAppStart();
       //注意: 此方法与统计分析sdk中统计日活的方法无关！请务必调用此方法！
    }

    protected abstract int setLayoutResourceID();

    protected void initMultiStateView() {
        loading = LayoutInflater.from(mContext).inflate(R.layout.layout_loading_view, null);

        multiStateView = (MultiStateView) findViewById(R.id.multiStateView);
        if (multiStateView != null) {
            multiStateView.setViewForState(R.layout.layout_loading_view,MultiStateView.VIEW_STATE_LOADING);
            multiStateView.setViewForState(R.layout.layout_error_view,MultiStateView.VIEW_STATE_ERROR);
            multiStateView.setViewForState(R.layout.layout_empty_view,MultiStateView.VIEW_STATE_EMPTY);
            // 网络请求失败后的重试按
            findViewById(R.id.retry).setOnClickListener(OnClickListener);
        }
    }

    protected <T extends View> T findViewById_(int id) {
        return (T) findViewById(id);
    }

    public void addLoadingView() {
        if (loading != null) {
            multiStateView.removeView(loading);
            multiStateView.addView(loading);
        }
    }

    public void removeLoadingView() {
        multiStateView.removeView(loading);
    }

    public MultiStateView getMultiStateView() {
        return multiStateView;
    }

    public void retry(Runnable run) {
        this.mRun = run;
    }

    //网络请求失败重试
    View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (NetworkUtil.isNetwork(BaseActivity.this)) {
                multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
                if (mRun != null)
                    mHandker.post(mRun);
            } else {
                ToastUtil.show(getResources().getString(R.string.qingjianchawangluo));
            }
        }
    };

    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void startActivityWithExtras(Class<?> clazz, Bundle extras) {
        Intent intent = new Intent(this, clazz);
        intent.putExtras(extras);
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
