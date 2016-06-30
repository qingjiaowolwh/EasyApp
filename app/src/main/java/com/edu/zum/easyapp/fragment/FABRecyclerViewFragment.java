package com.edu.zum.easyapp.fragment;


import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.adapter.BaseRecyclerAdapter;
import com.edu.zum.easyapp.adapter.FirstAdapter;
import com.edu.zum.easyapp.api.ApiManager;
import com.edu.zum.easyapp.model.ResultModel;

public class FABRecyclerViewFragment extends XRecyclerViewFragment{

    @Override
    protected int setLayoutResourceID() {
        return R.layout.fragment_fabrecycler_view;
    }

    @Override
    protected void setUpView() {
        super.setUpView();
        mRecyclerView.setRefreshing(true);
        mRecyclerView.setPullRefreshEnabled(false);

    }

    @Override
    protected void setUpData() {
        super.setUpData();
        //获取数据
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected void loadData() {
        super.loadData();
        ApiManager.createUser("福利", mCurrentPageIndex, new ApiManager.Callback<ResultModel>() {
            @Override
            public void onError(Throwable e) {
                loadError(e);
            }

            @Override
            public void onSuccess(ResultModel result) {
                loadNext(result);
                loadComplete();
            }
        });

    }

    protected void loadNext(ResultModel resultModel) {
        setStateContent();
        if (!resultModel.isError()) {
            if (!resultModel.getResults().isEmpty()) {
                if (mCurrentAction == ACTION_REFRESH)
                    adapter.replace(resultModel.getResults());
                if (mCurrentAction == ACTION_LOAD_MORE)
                    adapter.append(resultModel.getResults());
            }
        }
    }

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        return new FirstAdapter();
    }
}
