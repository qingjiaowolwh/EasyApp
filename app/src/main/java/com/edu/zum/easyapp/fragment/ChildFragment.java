package com.edu.zum.easyapp.fragment;

import com.edu.zum.easyapp.adapter.BaseRecyclerAdapter;
import com.edu.zum.easyapp.adapter.testAdapter;
import com.edu.zum.easyapp.api.RetrofitService;
import com.edu.zum.easyapp.model.ResultModel;
import com.ganhuo.entity.Ganhuo;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ChildFragment extends XRecyclerViewFragment {
    private Subscription subscription;

    @Override
    protected void setUpData() {
        super.setUpData();
        //获取数据
        mRecyclerView.setRefreshing(true);
    }

    @Override
    protected void loadData() {
        super.loadData();
        subscription= RetrofitService.getInstance().getGoods(getArguments().getString("type"), mCurrentPageIndex).subscribeOn(Schedulers.io())
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
    protected BaseRecyclerAdapter<Ganhuo> setAdapter() {
        return new testAdapter();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription!=null)subscription.unsubscribe();
    }
}
