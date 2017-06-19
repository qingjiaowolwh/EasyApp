package com.edu.zum.easyapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by lwh on 2016/3/31.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
    protected List<T> mDatas = new ArrayList<>();
    private OnItemClickListener<T> mOnItemClickListener;
    private OnItemLongClickListener<T> mOnItemLongClickListener;
    protected Context mContext;

    public BaseRecyclerAdapter() {
    }

    /**
     * 前面两种方法都可以实例化一个LayoutInflater  不需要用LayoutInflater的话用这个就够了
     *
     * @param mDatas
     */
    public BaseRecyclerAdapter(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public void setData(List<T> mDatas) {
        this.mDatas = mDatas;
    }

    public void append(T item) {
        if (item != null) {
            mDatas.add(item);
            notifyDataSetChanged();
        }
    }

    public void append(List<T> datas) {
        if (datas != null && datas.size() > 0) {
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }


    public void replace(List<T> datas) {
        mDatas.clear();
        if (datas != null && datas.size() > 0) {
            mDatas.addAll(datas);
            notifyDataSetChanged();
        }
    }

    public void remove(int position) {
        mDatas.remove(position);
        notifyDataSetChanged();
    }

    public void removeAll() {
        mDatas.clear();
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.mOnItemClickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        this.mOnItemLongClickListener = listener;
    }

    public interface OnItemClickListener<T> {

        void onItemClick(View view, int position, T model);
    }

    public interface OnItemLongClickListener<T> {

        boolean onItemLongClick(View view, int position, T model);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(setItemLayoutResourceID(), parent, false);
        return setViewHolder(view, viewType, parent);

    }


    @Override
    public void onBindViewHolder(BaseViewHolder holder, final int position) {
        onBindMyViewHolder(holder, position);
        initItemClickListener(holder, position);
    }

    private void initItemClickListener(BaseViewHolder holder, final int position) {
        holder.itemView.setOnClickListener(v-> {
                if (mOnItemClickListener != null)
                    mOnItemClickListener.onItemClick(v, position, mDatas.get(position));
        });
        if (mOnItemLongClickListener != null) {
            holder.itemView.setOnLongClickListener(v->
                     mOnItemLongClickListener.onItemLongClick(v, position, mDatas.get(position))
            );
        }
    }

    public OnItemChildClickListener mChildClickListener;

    /**
     * 给Item的childView设置监听
     *
     * @param childClickListener
     */
    public void setOnItemChildClickListener(OnItemChildClickListener childClickListener) {
        this.mChildClickListener = childClickListener;
    }

    public interface OnItemChildClickListener<T> {
        void onItemChildClick(View view, int position, T model);
    }

    public class OnRecyclerItemChildClickListener implements View.OnClickListener {
        public RecyclerView.ViewHolder mViewHolder;
        public int position;

        @Override
        public void onClick(View v) {
            if (mChildClickListener != null)
                mChildClickListener.onItemChildClick(v, position, mDatas.get(position));
        }
    }

    protected abstract int setItemLayoutResourceID();

    protected  BaseViewHolder setViewHolder(View v, int viewType, ViewGroup parent){
        return new BaseViewHolder(v);
    }

    protected abstract void onBindMyViewHolder(BaseViewHolder holder, int position);


}
