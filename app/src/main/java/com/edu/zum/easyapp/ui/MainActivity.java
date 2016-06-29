package com.edu.zum.easyapp.ui;

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
import com.edu.zum.easyapp.fragment.SecondFragment;
import com.edu.zum.easyapp.utils.ViewUtils;

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
        getToolbar().setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener(){

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
    }

    @Override
    protected void setUpView() {
        setDrawerLayout();

        mCurrentFragment = ViewUtils.createFragment(FirstFragment.class);
        mFragmentManager.beginTransaction().add(R.id.frame_content, mCurrentFragment).commit();
        setTitle("首页");
        mPreMenuItem = mNavigationView.getMenu().getItem(0);
        mPreMenuItem.setChecked(true);
        setNavigationViewItemClickListener();
    }

    @Override
    protected void init() {
        super.init();
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
                        setTitle("次页3");
//                        switchFragment(FABRecyclerViewFragment.class);
                        startActivity(ContainerActivity.class);
                        break;
                    case R.id.navigation_item_switch_theme:
                        break;
                    case R.id.navigation_item_about:
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

}
