package com.edu.zum.easyapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.model.GanHuoBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by lwh on 2016/3/31.
 */
public class StickyAdapter extends BaseRecyclerAdapter<String> {

    public StickyAdapter(List<String> mDatas) {
        super(mDatas);
    }

    public StickyAdapter() {
        super();
    }

    @Override
    protected int setItemLayoutResourceID() {
        return R.layout.item;
    }

    @Override
    protected BaseViewHolder setViewHolder(View v, int viewType, ViewGroup parent) {
        return new MyViewHolder(v);
    }


    @Override
    protected void onBindMyViewHolder(BaseViewHolder holder, int position) {
        MyViewHolder myHolder = (MyViewHolder) holder;
        myHolder.text.setText(position + mDatas.get(position));
    }


    static class MyViewHolder extends BaseViewHolder {
        @Bind(R.id.text)
        TextView text;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
