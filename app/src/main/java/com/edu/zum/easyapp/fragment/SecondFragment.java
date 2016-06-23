package com.edu.zum.easyapp.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.adapter.GanHuoPagerAdapter;

import butterknife.Bind;

public class SecondFragment extends BaseFragment {
    String[] mTitles = new String[]{"all", "休息视频", "福利", "Android", "iOS", "拓展资源", "前端", "瞎推荐"};
    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.viewPager)
    ViewPager viewPager;

    private GanHuoPagerAdapter adapter;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_second;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        for (int i = 0; i < mTitles.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(mTitles[i]));
        }
        adapter=new GanHuoPagerAdapter(getChildFragmentManager(),mTitles);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
