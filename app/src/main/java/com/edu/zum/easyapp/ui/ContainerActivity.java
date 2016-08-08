package com.edu.zum.easyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.fragment.BaseFragment;

public class ContainerActivity extends BaseActivity {
    public static final String EXTRA_FRAGMENT_CLASS_NAME = "class_name";
    public static final String EXTRA_FRAGMENT_TITLE = "title";
    private BaseFragment mCurrentFragment;
    private String className;
    private Class<?> fragmentC;

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
        FragmentManager supportFragmentManager = getSupportFragmentManager();

        FragmentTransaction transaction = supportFragmentManager.beginTransaction();
        transaction.addToBackStack(fragmentC.getSimpleName());
        transaction.replace(R.id.frame_content, mCurrentFragment);
        transaction.setTransition(FragmentTransaction.TRANSIT_NONE);
        transaction.commit();
    }

    public static void startActivity(Context context, Class<? extends BaseFragment> frgClass, Bundle extras) {
        Intent i = new Intent(context, ContainerActivity.class);
        i.putExtra(EXTRA_FRAGMENT_CLASS_NAME, frgClass.getName());
        if (extras != null)
            i.putExtras(extras);
        context.startActivity(i);
    }
}
