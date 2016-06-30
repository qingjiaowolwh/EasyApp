package com.edu.zum.easyapp.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.adapter.BaseRecyclerAdapter;
import com.edu.zum.easyapp.adapter.StickyAdapter;
import com.edu.zum.easyapp.utils.ToastUtil;

public class StickyFragment extends XRecyclerViewFragment implements View.OnClickListener {
    private final static String TAG = "StickyFragment";
    LinearLayout recyclerHeader;
    LinearLayout stickyHeader;

    private LinearLayoutManager mLayoutManager;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_sticky;
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        return mLayoutManager;
    }

    @Override
    protected void setUpView() {
        super.setUpView();

        ImageView imageView = new ImageView(mContext);
        imageView.setImageResource(R.drawable.avastar);
        mRecyclerView.addHeaderView(imageView);

        recyclerHeader = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.layout_stick_header, mRecyclerView, false);

        recyclerHeader.findViewById(R.id.position1).setOnClickListener(this);
        recyclerHeader.findViewById(R.id.position2).setOnClickListener(this);
        recyclerHeader.findViewById(R.id.position3).setOnClickListener(this);

        getContentView().findViewById(R.id.position1).setOnClickListener(this);
        getContentView().findViewById(R.id.position2).setOnClickListener(this);
        getContentView().findViewById(R.id.position3).setOnClickListener(this);


        mRecyclerView.addHeaderView(recyclerHeader);


        stickyHeader = (LinearLayout) getContentView().findViewById(R.id.sticky_header);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                Log.e(TAG, "Position" + mLayoutManager.findFirstVisibleItemPosition());
                if (mLayoutManager.findFirstVisibleItemPosition() >= 2) {
                    stickyHeader.setVisibility(View.VISIBLE);
                } else {
                    stickyHeader.setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        StickyAdapter adapter = new StickyAdapter();
        adapter.append("1年代开始了解放立刻就打算立刻解放了的");
        adapter.append("2年代开始了解放立刻就打算立刻解放了的");
        adapter.append("3年代开始了解放立刻就打算立刻解放了的");
        adapter.append("4年代开始了解放立刻就打算立刻解放了的");
        adapter.append("5年代开始了解放立刻就打算立刻解放了的");
        adapter.append("6年代开始了解放立刻就打算立刻解放了的");
        adapter.append("7年代开始了解放立刻就打算立刻解放了的");
        adapter.append("8年代开始了解放立刻就打算立刻解放了的");
        adapter.append("9年代开始了解放立刻就打算立刻解放了的");
        adapter.append("10年代开始了解放立刻就打算立刻解放了的");
        adapter.append("11年代开始了解放立刻就打算立刻解放了的");
        adapter.append("12年代开始了解放立刻就打算立刻解放了的");
        adapter.append("13年代开始了解放立刻就打算立刻解放了的");
        adapter.append("14年代开始了解放立刻就打算立刻解放了的");
        return adapter;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.position1:
                ToastUtil.show( "--rel----position1------");
                break;
            case R.id.position2:
                ToastUtil.show("--rel----position2------");
                break;
            case R.id.position3:
                ToastUtil.show( "--rel----position3------");
                break;
        }
    }

}
