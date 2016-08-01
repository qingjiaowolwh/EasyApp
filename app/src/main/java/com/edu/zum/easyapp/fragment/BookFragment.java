package com.edu.zum.easyapp.fragment;

import android.support.v7.widget.RecyclerView;

import com.edu.zum.easyapp.adapter.BaseRecyclerAdapter;
import com.edu.zum.easyapp.adapter.BookAdapter;

public class BookFragment extends XRecyclerViewFragment {

    @Override
    protected BaseRecyclerAdapter setAdapter() {
        return new BookAdapter();
    }

    @Override
    protected RecyclerView.LayoutManager setLayoutManager() {
        return null;
    }
}
