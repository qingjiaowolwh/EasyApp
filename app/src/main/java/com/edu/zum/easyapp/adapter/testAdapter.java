package com.edu.zum.easyapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.model.GanHuoBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by lwh on 2016/3/31.
 */
public class testAdapter extends BaseRecyclerAdapter<GanHuoBean> {

    public testAdapter(List<GanHuoBean> mDatas) {
        super(mDatas);
    }

    public testAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int setItemLayoutResourceID() {
        return R.layout.item;
    }

    @Override
    protected BaseViewHolder setViewHolder(View v) {
        return new MyViewHolder(v);
    }


    @Override
    protected void onBindMyViewHolder(BaseViewHolder holder, int position) {
        MyViewHolder myHolder = (MyViewHolder) holder;
        myHolder.text.setText(position+mDatas.get(position).getDesc());

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
