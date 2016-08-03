package com.edu.zum.easyapp.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.fragment.BaseFragment;
import com.edu.zum.easyapp.fragment.FABRecyclerViewFragment;
import com.edu.zum.easyapp.utils.ViewUtils;

public class ContainerActivity extends BaseActivity {
    public static final String EXTRA_FRAGMENT_CLASS_NAME = "class_name";
    public static final String EXTRA_FRAGMENT_TITLE = "title";
    private FragmentManager mFragmentManager;
    private Fragment mCurrentFragment;
    private String className;
    private Class<?> fragmentC;

    @Override
    protected void init() {
        super.init();
        mFragmentManager = getSupportFragmentManager();
    }

    @Override
    protected void getBundleExtras(@NonNull Bundle extras) {
        super.getBundleExtras(extras);
        className = extras.getString(EXTRA_FRAGMENT_CLASS_NAME);
        try {
            if (className != null) {
                fragmentC = Class.forName(className);
                mCurrentFragment = (BaseFragment) fragmentC.newInstance();
                mCurrentFragment.setArguments(extras);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_change_fragment;
    }

    @Override
    protected void setUpView() {
        mCurrentFragment = ViewUtils.createFragment(FABRecyclerViewFragment.class);
        mFragmentManager.beginTransaction().add(R.id.frame_content, mCurrentFragment).commit();
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
}
