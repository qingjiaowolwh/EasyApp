package com.edu.zum.easyapp.ui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.edu.zum.easyapp.base.BaseActivity;

/**
 * Created by lwh on 2016/8/4.
 */
public abstract class BaseFragmentActivity extends BaseActivity {
    public FragmentManager mFragmentManager;
    public Fragment mCurrentFragment;

}
