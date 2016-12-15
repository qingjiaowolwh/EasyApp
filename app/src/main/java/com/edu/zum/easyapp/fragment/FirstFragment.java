package com.edu.zum.easyapp.fragment;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;

import com.edu.zum.easyapp.adapter.BaseRecyclerAdapter;
import com.edu.zum.easyapp.adapter.FirstAdapter;
import com.edu.zum.easyapp.api.RetrofitService;
import com.edu.zum.easyapp.manager.DaoUtils;
import com.edu.zum.easyapp.model.ResultModel;
import com.edu.zum.easyapp.ui.ContainerActivity;
import com.edu.zum.easyapp.ui.ListActivity;
import com.ganhuo.entity.Ganhuo;

import easemob.helpdeskdemo.Constant;
import easemob.helpdeskdemo.ui.LoginActivity;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FirstFragment extends XRecyclerViewFragment {
    private DaoUtils daoUtils;

    @Override
    protected void setUpData() {
        super.setUpData();
        //获取数据
        daoUtils = new DaoUtils(mContext);
        if (!daoUtils.searchGanhuo().isEmpty()) {
            mCurrentAction=ACTION_REFRESH;
            adapter.append(daoUtils.searchGanhuo());
        }
        mRecyclerView.setRefreshing(true);
        adapter.setOnItemChildClickListener((view,position,o)-> {
                switch (position) {
                    case 0:
                        //环信客服
                        mContext.startActivity(new Intent(mContext, LoginActivity.class).putExtra(Constant.MESSAGE_TO_INTENT_EXTRA,
                                Constant.MESSAGE_TO_PRE_SALES));
                        break;
                    case 1:
                        ContainerActivity.startActivity(mContext,WordFragment.class,null);
                        break;
                    default:
                        ListActivity.startActivity(mContext);
                        break;
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
        subscription=RetrofitService.getInstance().getGoods("福利", mCurrentPageIndex).subscribeOn(Schedulers.io())
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

    }

    protected void loadNext(ResultModel resultModel) {
        setStateContent();
        if (!resultModel.isError()) {
            if (!resultModel.getResults().isEmpty()) {
                if (mCurrentAction == ACTION_REFRESH)
                    adapter.replace(resultModel.getResults());
                daoUtils.deleteGanhuo();
                daoUtils.insertGanhuo(resultModel.getResults());
                if (mCurrentAction == ACTION_LOAD_MORE)
                    adapter.append(resultModel.getResults());
            }
        }
    }

    @Override
    protected BaseRecyclerAdapter<Ganhuo> setAdapter() {
        return new FirstAdapter();
    }


    private Subscription subscription;
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (subscription!=null)subscription.unsubscribe();
    }
}
