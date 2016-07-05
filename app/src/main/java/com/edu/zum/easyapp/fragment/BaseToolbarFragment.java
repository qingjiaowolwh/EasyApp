package com.edu.zum.easyapp.fragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;

import com.edu.zum.easyapp.R;

/**
 * Created by lwh on 2016/6/29.
 */
public abstract class BaseToolbarFragment extends BaseFragment {
    private Toolbar mToolbar;

    @Override
    protected void setUpView() {
        super.setUpView();
        mToolbar = (Toolbar) getContentView().findViewById(R.id.toolbar);
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.drawable.fanhui);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    getActivity().finish();
                }
            });
        }
    }

    /**
     * flaf初始为true
     *
     * @param dy
     * @param flag
     * @return
     */
    public boolean hidOrShowToolbar(int dy, boolean flag) {
        // Is scrolling up
        if (dy > 10) {
            if (!flag) {
                showToolbar();
                flag = true;
            }
            // Is scrolling down
        } else if (dy < -10) {
            if (flag) {
                hideToolbar();
                flag = false;
            }
        }
        return flag;
    }

    private void showToolbar() {
        mToolbar.startAnimation(AnimationUtils.loadAnimation(mContext,
                R.anim.translate_up_off));
    }

    private void hideToolbar() {
        mToolbar.startAnimation(AnimationUtils.loadAnimation(mContext,
                R.anim.translate_up_on));
    }

}
