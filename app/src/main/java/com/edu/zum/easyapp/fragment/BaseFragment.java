package com.edu.zum.easyapp.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.utils.NetworkUtil;
import com.edu.zum.easyapp.utils.ToastUtil;
import com.edu.zum.easyapp.widgets.MultiStateView;

import butterknife.ButterKnife;


/**
 * Created lwh
 */
public abstract class BaseFragment extends Fragment {
    protected Context mContext;
    private View rootView;
    private ProgressDialog mProgressDialog;

    private MultiStateView multiStateView;
    private Button retry;

    private Handler mHandker = new Handler();
    private Runnable mRun;
    private View loading;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(setLayoutResourceID(), container, false);
        ButterKnife.bind(this, rootView);
        mContext = getActivity();
        mProgressDialog = new ProgressDialog(mContext);
        mProgressDialog.setCanceledOnTouchOutside(false);
        setProgressDialogMessage();
        init();
        initMultiStateView();
        setUpView();
        setUpData();
        return rootView;
    }


    protected void initMultiStateView() {
        loading = LayoutInflater.from(mContext).inflate(R.layout.layout_loading_view, null);

        multiStateView = (MultiStateView) rootView.findViewById(R.id.multiStateView);
        if (multiStateView != null) {
            multiStateView.setViewForState(R.layout.layout_loading_view,
                    MultiStateView.VIEW_STATE_LOADING);
            multiStateView.setViewForState(R.layout.layout_error_view,
                    MultiStateView.VIEW_STATE_ERROR);
            multiStateView.setViewForState(R.layout.layout_empty_view,
                    MultiStateView.VIEW_STATE_EMPTY);
            multiStateView.getView(MultiStateView.VIEW_STATE_ERROR)
                    .findViewById(R.id.retry).setOnClickListener(OnClickListener);
            multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
            retry = (Button) rootView.findViewById(R.id.retry);
        }
    }

    public void removeLoadingView() {
        multiStateView.removeView(loading);
    }

    public MultiStateView getMultiStateView() {
        return multiStateView;
    }

    public void addLoadingView() {
        if (loading != null) {
            multiStateView.removeView(loading);
            multiStateView.addView(loading);
        }
    }

    public void retry(Runnable run) {
        this.mRun = run;
    }

    //网络请求失败重试
    View.OnClickListener OnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (NetworkUtil.isNetwork(mContext)) {
                setStateLoading();
                if (mRun != null)
                    mHandker.post(mRun);
            } else {
                ToastUtil.show(mContext, getResources().getString(R.string.qingjianchawangluo));
            }
        }
    };

    public void setStateLoading() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_LOADING);
    }

    public void setStateError() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);
    }

    public void setStateEmpty() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);
    }

    public void setStateContent() {
        multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    protected <T extends View> T $(int id) {
        return (T) rootView.findViewById(id);
    }


    protected abstract int setLayoutResourceID();

    protected void setUpData() {

    }

    protected void init() {

    }

    protected void setUpView() {
    }


    protected View getContentView() {
        return rootView;
    }


    protected ProgressDialog getProgressDialog() {
        return mProgressDialog;
    }

    protected void showProgressDialog() {
        mProgressDialog.show();
    }

    protected void setProgressDialogMessage() {
        mProgressDialog.setMessage("加载中...");
    }
}
