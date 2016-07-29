package com.edu.zum.easyapp.ui;

import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.fragment.AnimatonFragment;
import com.edu.zum.easyapp.fragment.FirstFragment;
import com.edu.zum.easyapp.fragment.ScreenshotFragment;
import com.edu.zum.easyapp.fragment.SecondFragment;
import com.edu.zum.easyapp.fragment.StickyFragment;
import com.edu.zum.easyapp.fragment.WordFragment;
import com.edu.zum.easyapp.utils.ViewUtils;
import com.orhanobut.logger.Logger;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;

import butterknife.Bind;

public class MainActivity extends BaseActivity {
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;

    private MenuItem mPreMenuItem;
    @Bind(R.id.drawerlayout)
    DrawerLayout drawerLayout;


    @Bind(R.id.navigation_view)
    NavigationView mNavigationView;

    @Override
    protected void setToolBar() {
        super.setToolBar();
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }

    @Override
    protected void setUpView() {
        String hello = "hello";
        Logger.d(hello);
        Logger.e(hello);
        Logger.w("fd");
        Logger.v(hello);
        Logger.wtf(hello);
        setDrawerLayout();
        mCurrentFragment = ViewUtils.createFragment(FirstFragment.class);
        mFragmentManager.beginTransaction().add(R.id.frame_content, mCurrentFragment).commit();
        setTitle("首页");
        mPreMenuItem = mNavigationView.getMenu().getItem(0);
        mPreMenuItem.setChecked(true);
        setNavigationViewItemClickListener();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
            }
        });
    }

    @Override
    protected void init() {
        super.init();
        //开启友盟推送
        PushAgent mPushAgent = PushAgent.getInstance(mContext);
        mPushAgent.enable();

        String device_token = UmengRegistrar.getRegistrationId(mContext);
        System.out.println("device_token:" + device_token);
        // 可以通过接口 mPushAgent.disable(); 来关闭客户端的通知服务。
        //通过mPushAgent.isEnabled() 来查询状态。 状态表示有没有启用/关闭推送功能， 不表示推送后台服务的运行状态。
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_main;
    }

    private void setDrawerLayout() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        toggle.syncState();
        drawerLayout.setDrawerListener(toggle);
        //通过 NavigationDrawer 打开关闭 抽屉
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    protected void setNavigationAction() {
        getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START))
                    drawerLayout.closeDrawer(GravityCompat.START);
                else
                    drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }

    //切换Fragment
    private void switchFragment(Class<?> clazz) {
        Fragment to = ViewUtils.createFragment(clazz);
        if (to.isAdded()) {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).show(to).commitAllowingStateLoss();
        } else {
            mFragmentManager.beginTransaction().hide(mCurrentFragment).add(R.id.frame_content, to).commitAllowingStateLoss();
        }
        mCurrentFragment = to;
    }


    private void setNavigationViewItemClickListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if (null != mPreMenuItem) {
                    mPreMenuItem.setChecked(false);
                }
                switch (item.getItemId()) {
                    case R.id.navigation_item_home:
                        setTitle("首页");
                        switchFragment(FirstFragment.class);
                        break;
                    case R.id.navigation_item_second:
                        setTitle("次页1");
                        switchFragment(SecondFragment.class);
                        break;
                    case R.id.navigation_item_third:
                        setTitle("属性动画");
                        switchFragment(AnimatonFragment.class);
                        break;
                    case R.id.navigation_item_four:
                        setTitle("Toolbar效果");
//                        switchFragment(FABRecyclerViewFragment.class);
                        startActivity(ContainerActivity.class);
                        break;
                    case R.id.navigation_item_sticky:
                        setTitle("RecyclerView悬浮效果");
//                        switchFragment(FABRecyclerViewFragment.class);
                        switchFragment(StickyFragment.class);
                        break;

                    case R.id.navigation_item_switch_theme:
//                        switchFragment(StickyFragment.class);
                        setTitle("截图");
                        switchFragment(ScreenshotFragment.class);
                        break;
                    case R.id.navigation_item_about:
                        setTitle("单词");
                        switchFragment(WordFragment.class);

                        break;
                    default:
                        break;
                }
                item.setChecked(true);
                drawerLayout.closeDrawer(Gravity.LEFT);
                mPreMenuItem = item;
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

//    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.action_settings:
//                ToastUtil.show("action_settings");
//                return true;
//        }
//        return false;
//    }


    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
//        menu.getItem(0).setChecked(true);
        return super.onMenuOpened(featureId, menu);
    }
}
