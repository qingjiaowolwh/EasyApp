package com.edu.zum.easyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.global.MyApplication;
import com.edu.zum.easyapp.rxbus.RxBus;
import com.edu.zum.easyapp.rxbus.RxBusEvent;
import com.edu.zum.easyapp.utils.NetworkUtil;
import com.edu.zum.easyapp.utils.StatusBarUtil;
import com.edu.zum.easyapp.utils.ToastUtil;
import com.squareup.leakcanary.RefWatcher;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.zmnedu.library.widgets.MultiStateView;

import butterknife.ButterKnife;
import rx.functions.Action1;

public abstract class BaseActivity extends AppCompatActivity {
    protected Context mContext;

    private Toolbar toolbar;
    private TextView title_middle;
    /**
     * 可以显示加载  内容  错误   空等视图的自定义View
     */
    private MultiStateView multiStateView;

    private Handler mHandker = new Handler();
    private Runnable mRun;
    /**
     * MultiStateView浮在界面上面的加载视图
     */
    private View loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StatusBarUtil.initSystemBar(this);
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResourceID());
//        getWindow().
//                getDecorView().
//                setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
//        getWindow().getDecorView().setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
//                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
//                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
//        );
        //设置透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
//        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
//        refWatcher.watch(this);
        ButterKnife.bind(this);
        init();
        initView();
        setUpView();
        setUpData();
    }

    protected void initView(){
        initToolBar();
        setToolBar();
        initMultiStateView();
    }

    /**
     * 设置toolbar 一般不用
     */
    protected void setToolBar() {
    }

    /**
     * 设置View的操作
     */
    protected abstract void setUpView();

    /**
     * 设置数据的操作
     */
    protected void setUpData() {
    }

    /**
     * 做一些与View无关的操作
     */
    protected void init() {
        mContext = this;
        PushAgent.getInstance(mContext).onAppStart();
//        注意: 此方法与统计分析sdk中统计日活的方法无关！请务必调用此方法！
        Intent intent = getIntent();
        if (intent != null) {
            getBundleExtras(intent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {

            @Override
            public void call(Object s) {
                if (RxBusEvent.FINISH_EVENT.equals(s)) {
                    finish();
                }
            }
        });
    }

    /**
     * 获取上个activity的传值
     *
     * @param extras
     */
    protected void getBundleExtras(@NonNull Intent intent) {
    }

    /**
     * 设置布局
     *
     * @return
     */
    protected abstract int setLayoutResourceID();

    protected void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            getSupportActionBar().setHomeButtonEnabled(true); //设置返回键可用
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            setNavigationAction();
            title_middle = (TextView) findViewById(R.id.title_middle);
        }
    }

    /**
     * 在首页可以重写,自定义左上角图标
     */
    protected void setNavigationAction() {
        toolbar.setNavigationIcon(R.drawable.fanhui);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    protected void initMultiStateView() {
        loading = LayoutInflater.from(mContext).inflate(R.layout.layout_loading_view, null);

        multiStateView = (MultiStateView) findViewById(R.id.multiStateView);
        if (multiStateView != null) {
            multiStateView.setViewForState(R.layout.layout_loading_view,
                    MultiStateView.VIEW_STATE_LOADING);
            multiStateView.setViewForState(R.layout.layout_error_view,
                    MultiStateView.VIEW_STATE_ERROR);
            multiStateView.setViewForState(R.layout.layout_empty_view,
                    MultiStateView.VIEW_STATE_EMPTY);

            // 网络请求失败后的重试按
            multiStateView.getView(MultiStateView.VIEW_STATE_ERROR)
                    .findViewById(R.id.retry).setOnClickListener(OnClickListener);
            multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
        }
    }

    public void setStateLoading() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    public void setStateError() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    public void setStateEmpty() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    public void setStateContent() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }


    public Toolbar getToolbar() {
        return toolbar;
    }

    /**
     * 添加在MultiStateView上面的View  使下面的控件不可点击
     */
    public void addLoadingView() {
        if (loading != null) {
            multiStateView.removeView(loading);
            multiStateView.addView(loading);
        }
    }

    /**
     * 移除在MultiStateView上面的View
     */
    public void removeLoadingView() {
        multiStateView.removeView(loading);
    }

    public MultiStateView getMultiStateView() {
        return multiStateView;
    }

    public void setTitle(String title) {
        title_middle.setText(title);
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

    /**
     * 无参跳转activity
     *
     * @param clazz
     */
    protected void startActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }


    /**
     * 有参跳转activity
     *
     * @param clazz
     * @param extras
     */
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
