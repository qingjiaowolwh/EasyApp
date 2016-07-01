//package com.edu.zum.easyapp.adapter;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.edu.zum.easyapp.R;
//import com.edu.zum.easyapp.fragment.StickyFragment;
//
///**
// * Created by lwh on 2016/6/29.
// */
//public abstract class StickyRecyclerAdapter<T> extends BaseRecyclerAdapter<T> {
//    public StickyRecyclerAdapter(Context mContext) {
//        super(mContext);
//    }
//
//    @Override
//    protected int setItemLayoutResourceID() {
//        return R.layout.item;
//    }
//
//    @Override
//    protected BaseViewHolder setViewHolder(View v, int viewType, ViewGroup parent) {
//        if (viewType == StickyFragment.STICK_POSITION) {
//            View view = LayoutInflater.from(parent.getContext()).inflate(getStickLayoutResourceID(), parent, false);
//            return setStickViewHolder(view, viewType, parent);
//        }
//        return setMyViewHolder(v, viewType, parent);
//    }
//
//    /**
//     * 悬浮布局Item
//     *
//     * @return
//     */
//    protected abstract int getStickLayoutResourceID();
//
//    /**
//     * item的布局
//     *
//     * @param v
//     * @param viewType
//     * @param parent
//     * @return
//     */
//    protected abstract BaseViewHolder setMyViewHolder(View v, int viewType, ViewGroup parent);
//
//    /**
//     * Stickitem的布局
//     *
//     * @param view
//     * @param viewType
//     * @param parent
//     * @return
//     */
//    protected abstract BaseViewHolder setStickViewHolder(View view, int viewType, ViewGroup parent);
//
//    @Override
//    public int getItemViewType(int position) {
//        if (position == StickyFragment.STICK_POSITION)
//            return StickyFragment.STICK_POSITION;
//        return super.getItemViewType(position);
//    }
//}
