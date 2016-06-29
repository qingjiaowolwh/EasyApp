package com.edu.zum.easyapp.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;

import com.edu.zum.easyapp.R;

/**
 * Created by lwh on 2016/6/29.
 */
public abstract class BaseToolbarFragment extends BaseFragment implements View.OnClickListener {
    private Toolbar mToolbar;

    @Override
    protected void setUpView() {
        super.setUpView();
        mToolbar = (Toolbar) getContentView().findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.fanhui);
            mToolbar.setNavigationOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        getActivity().finish();
    }
}
