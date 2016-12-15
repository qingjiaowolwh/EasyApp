package com.edu.zum.easyapp.base;

import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.edu.zum.easyapp.R;

@SuppressWarnings("ALL")
public abstract class BaseToolbarActivity extends BaseActivity {
    private Toolbar toolbar;
    private TextView title_middle;

    @Override
    protected void setUpView() {
        initToolBar();
    }

    public void setTitle(String title) {
        this.title_middle.setText(title);
    }

    public void setTitle(int title) {
        this.title_middle.setText(title);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

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
}
