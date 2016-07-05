package com.edu.zum.easyapp.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.edu.zum.easyapp.adapter.BaseRecyclerAdapter;
import com.edu.zum.easyapp.adapter.FirstAdapter;
import com.edu.zum.easyapp.api.ApiManager;
import com.edu.zum.easyapp.api.RetrofitService;
import com.edu.zum.easyapp.model.GanHuoBean;
import com.edu.zum.easyapp.model.ResultModel;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FirstFragment extends XRecyclerViewFragment {

    @Override
    protected void setUpData() {
        super.setUpData();
        //获取数据
        mRecyclerView.setRefreshing(true);
        adapter.setOnItemChildClickListener(new BaseRecyclerAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(View view, int position, Object model) {

            }
        });
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    @Override
    protected void loadData() {
        super.loadData();
        RetrofitService.getInstance().getGoods("福利", mCurrentPageIndex).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Subscriber<ResultModel>() {
            @Override
            public void onCompleted() {
                loadComplete();
            }

            @Override
            public void onError(Throwable e) {
                loadError(e);
            }

            @Override
            public void onNext(ResultModel resultModel) {
                loadNext(resultModel);
            }
        });

//        ApiManager.createUser("福利", mCurrentPageIndex, new ApiManager.Callback<ResultModel>() {
//            @Override
//            public void onError(Throwable e) {
//                loadError(e);
//            }
//
//            @Override
//            public void onSuccess(ResultModel result) {
//                loadNext(result);
//                loadComplete();
//            }
//        });

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
    protected BaseRecyclerAdapter<GanHuoBean> setAdapter() {
        return new FirstAdapter();
    }
}
