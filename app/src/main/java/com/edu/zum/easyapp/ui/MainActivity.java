package com.edu.zum.easyapp.ui;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.base.BaseToolbarActivity;
import com.edu.zum.easyapp.fragment.AnimatonFragment;
import com.edu.zum.easyapp.fragment.FABRecyclerViewFragment;
import com.edu.zum.easyapp.fragment.FirstFragment;
import com.edu.zum.easyapp.fragment.PrintAndroidCircleProgressbarFragment;
import com.edu.zum.easyapp.fragment.SecondFragment;
import com.edu.zum.easyapp.fragment.StickyFragment;
import com.edu.zum.easyapp.global.Constants;
import com.edu.zum.easyapp.utils.AssetsToSDCard;
import com.edu.zum.easyapp.utils.ToastUtil;
import com.edu.zum.easyapp.utils.ViewUtils;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengRegistrar;

public class MainActivity extends BaseToolbarActivity {
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    private DrawerLayout drawerLayout;
    private NavigationView mNavigationView;
    private MenuItem mPreMenuItem;

    //    @Override
//    protected void setToolBar() {
//        super.setToolBar();
//        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
//
//            @Override
//            public boolean onMenuItemClick(MenuItem item) {
//                return false;
//            }
//        });
//    }
    private void initView() {
        drawerLayout = findViewById_(R.id.drawerlayout);
        mNavigationView = findViewById_(R.id.navigation_view);
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        initView();
        mCurrentFragment = ViewUtils.createFragment(FirstFragment.class);
        mFragmentManager.beginTransaction().add(R.id.frame_content, mCurrentFragment).commit();
        setTitle("首页");
        mPreMenuItem = mNavigationView.getMenu().getItem(0);
        mPreMenuItem.setChecked(true);
        setNavigationViewItemClickListener();
        setDrawerLayout();
    }

    @Override
    protected void init() {
        super.init();

        getJumpData();

        //开启友盟推送
        PushAgent mPushAgent = PushAgent.getInstance(mContext);
        mPushAgent.enable();

        String device_token = UmengRegistrar.getRegistrationId(mContext);
        System.out.println("device_token:" + device_token);
        // 可以通过接口 mPushAgent.disable(); 来关闭客户端的通知服务。
        //通过mPushAgent.isEnabled() 来查询状态。 状态表示有没有启用/关闭推送功能， 不表示推送后台服务的运行状态。
        mFragmentManager = getSupportFragmentManager();
    }

    private void getJumpData() {
        Intent intent = getIntent();//在这个Activity里，我们可以通过getIntent()，来获取外部跳转传过来的信息。
        String data = intent.getDataString();//接收到网页传过来的数据：sharetest://data/http://www.huxiu.com/
        if (!TextUtils.isEmpty(data)) {
            String[] split = data.split("data/");//以data/切割data字符串
            String url = split[1]; //就得到：http://www.huxiu.com/(这就是我们需要网页传给我们的数据)
            Log.e("MainActivity:" , url);
        }
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
        getToolbar().setNavigationOnClickListener(v -> {
            if (drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START);
            else
                drawerLayout.openDrawer(GravityCompat.START);
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
        mNavigationView.setNavigationItemSelectedListener(item -> {
            if (null != mPreMenuItem) {
                mPreMenuItem.setChecked(false);
            }
            switch (item.getItemId()) {
                case R.id.navigation_item_home:
                    setTitle("首页");
                    switchFragment(FirstFragment.class);
                    break;
                case R.id.navigation_item_second:
                    setTitle("TabLayout");
                    switchFragment(SecondFragment.class);
                    break;
                case R.id.navigation_item_third:
                    setTitle("动画");
                    switchFragment(AnimatonFragment.class);
                    break;
                case R.id.navigation_item_four:
                    setTitle("Toolbar效果");
                    ContainerActivity.startActivity(mContext, FABRecyclerViewFragment.class, null);
                    break;
                case R.id.navigation_item_sticky:
                    setTitle("RecyclerView悬浮效果");
                    switchFragment(StickyFragment.class);
                    break;

                case R.id.navigation_item_switch_theme:
                    setTitle("自定义View");
                    switchFragment(PrintAndroidCircleProgressbarFragment.class);
                    break;
                case R.id.navigation_item_list:
//                        ContainerActivity.startActivity(mContext, DecorViewFragment.class,null);
                    AssetsToSDCard.fileToSDCard(this, "ttt.ppt", Constants.FileCachePath, true);
                    break;
                default:
                    break;
            }
            item.setChecked(true);
            drawerLayout.closeDrawer(Gravity.LEFT);
            mPreMenuItem = item;
            return false;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            ToastUtil.show("设置");
            return false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (mCurrentFragment instanceof FirstFragment) {
//            return false;
//        }
        return super.onKeyDown(keyCode, event);
    }
}
