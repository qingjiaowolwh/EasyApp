package com.edu.zum.easyapp.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.adapter.BaseRecyclerAdapter;
import com.edu.zum.easyapp.utils.NetworkUtil;
import com.edu.zum.easyapp.utils.ToastUtil;
import com.zmnedu.library.xrecyclerview.ProgressStyle;
import com.zmnedu.library.xrecyclerview.XRecyclerView;


public abstract class XRecyclerViewFragment<T> extends BaseToolbarFragment{
    private String TAG="XRecyclerViewFragment";
    protected static final int ACTION_REFRESH = 1;
    protected static final int ACTION_LOAD_MORE = 2;
    protected static final int ACTION_PRE_LOAD = 3;

    protected int mCurrentAction = ACTION_REFRESH;
    protected int mCurrentPageIndex = 1;

    protected XRecyclerView mRecyclerView;
    protected BaseRecyclerAdapter adapter;

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_xrecyclerview;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        mRecyclerView = (XRecyclerView) getContentView().findViewById(R.id.xrecyclerview);
        mRecyclerView.setLayoutManager(setLayoutManager());
        mRecyclerView.setRefreshProgressStyle(setRefreshProgressStyle());
        mRecyclerView.setLoadingMoreProgressStyle(setLoadingMoreProgressStyle());
        mRecyclerView.setArrowImageView(R.drawable.iconfont_downgrey);

        if (setHeaderViewResourceID() > 0) {
            View header = LayoutInflater.from(mContext).inflate(setHeaderViewResourceID(),mRecyclerView, false);
            mRecyclerView.addHeaderView(header);
        }
        adapter = setAdapter();
        mRecyclerView.setAdapter(adapter);

        mRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                switchActionAndLoadData(ACTION_REFRESH);
            }

            @Override
            public void onLoadMore() {
                switchActionAndLoadData(ACTION_LOAD_MORE);
            }
        });
    }

    protected void switchActionAndLoadData(int action) {
        mCurrentAction = action;
        switch (mCurrentAction) {
            case ACTION_REFRESH:
                mCurrentPageIndex = 1;
                loadData();
                break;
            case ACTION_LOAD_MORE:
                mCurrentPageIndex++;
                loadData();
                break;
            case ACTION_PRE_LOAD:
                preLoadData();
                break;
            default:
                break;
        }

    }

    protected void loadComplete() {
        if (mCurrentAction == ACTION_REFRESH)
            mRecyclerView.refreshComplete();
        if (mCurrentAction == ACTION_LOAD_MORE)
            mRecyclerView.loadMoreComplete();
    }

    /**
     * 预加载（如果有缓存）
     */
    protected void preLoadData() {
    }

    protected void loadData() {
        if (!NetworkUtil.isNetwork(mContext)) {
            ToastUtil.show(getResources().getString(R.string.qingjianchawangluo));
            loadComplete();
            return;
        }
    }

    protected void loadError(Throwable e){
        Log.e(TAG,e.getMessage());
        if (mCurrentAction == ACTION_REFRESH) {
            setStateError();
            retry(()-> loadData());
        }
    }



    protected abstract BaseRecyclerAdapter setAdapter();

    /**
     * new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
     *
     * @return
     */
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    protected int setRefreshProgressStyle() {
        return ProgressStyle.BallClipRotatePulse;
    }

    protected int setLoadingMoreProgressStyle() {
        return ProgressStyle.SquareSpin;
    }

    protected void stopRefresh(){
        mRecyclerView.setRefreshing(false);
    }

    protected int setHeaderViewResourceID() {
        return -1;
    }
}
