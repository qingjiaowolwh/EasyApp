package com.edu.zum.easyapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.model.GanHuoBean;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * Created by lwh on 2016/3/31.
 */
public class FirstAdapter extends BaseRecyclerAdapter<GanHuoBean> {

    public FirstAdapter(List<GanHuoBean> mDatas) {
        super(mDatas);
    }

    public FirstAdapter() {
        super();
    }

    @Override
    protected int setItemLayoutResourceID() {
        return R.layout.layout_item_first;
    }

    @Override
    protected BaseViewHolder setViewHolder(View v, int viewType, ViewGroup parent) {
        return new BaseViewHolder(v);
    }


    @Override
    protected void onBindMyViewHolder(BaseViewHolder holder, int position) {
//        MyViewHolder myHolder = (MyViewHolder) holder;
//        myHolder.text.setText(position + mDatas.get(position).getDesc());
//        myHolder.itemView.setTag(mDatas.get(position).getUrl());
////            ImageLoader.getInstance().displayImage(
////                    mDatas.get(position).getUrl(), myHolder.image,
////                    ImageLoaderHelper.getInstance(mContext).getDisplayOptions(5));
////        Picasso.with(mContext).load(mDatas.get(position).getUrl()).into(myHolder.image);
//        Glide.with(mContext).load(mDatas.get(position).getUrl()).centerCrop().into(myHolder.image);
        holder.setText(R.id.text, mDatas.get(position).getDesc());
        holder.setOnClickListener(R.id.image,new OnRecyclerItemChildClickListener());
        Glide.with(mContext).load(mDatas.get(position).getUrl()).centerCrop().into((ImageView) holder.getView(R.id.image));


    }
}
