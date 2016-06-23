package com.edu.zum.easyapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.model.GanHuoBean;
import com.edu.zum.easyapp.utils.ImageLoaderHelper;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

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

    public FirstAdapter(Context mContext) {
        super(mContext);
    }

    @Override
    protected int setItemLayoutResourceID() {
        return R.layout.layout_item_first;
    }

    @Override
    protected BaseViewHolder setViewHolder(View v) {
        return new MyViewHolder(v);
    }


    @Override
    protected void onBindMyViewHolder(BaseViewHolder holder, int position) {
        MyViewHolder myHolder = (MyViewHolder) holder;
        myHolder.text.setText(position + mDatas.get(position).getDesc());
        myHolder.itemView.setTag(mDatas.get(position).getUrl());
//            ImageLoader.getInstance().displayImage(
//                    mDatas.get(position).getUrl(), myHolder.image,
//                    ImageLoaderHelper.getInstance(mContext).getDisplayOptions(5));
//        Picasso.with(mContext).load(mDatas.get(position).getUrl()).into(myHolder.image);
        Glide.with(mContext).load(mDatas.get(position).getUrl()).centerCrop().into(myHolder.image);


    }


    static class MyViewHolder extends BaseViewHolder {
        @Bind(R.id.text)
        TextView text;
        @Bind(R.id.image)
        ImageView image;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
