package com.edu.zum.easyapp.ui;

import android.content.Context;
import android.content.Intent;
import android.widget.ListView;

import com.edu.zum.easyapp.R;
import com.edu.zum.easyapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lwh on 2016/12/14.
 */

public class ListActivity extends BaseActivity {
    public static void startActivity(Context mContext){
        Intent i=new Intent(mContext,ListActivity.class);
        mContext.startActivity(i);
    }

    private ListView listView;
    private List<String> mData=new ArrayList<>();
    @Override
    protected int setLayoutResourceID() {
        return R.layout.activity_list;
    }
    @Override
    protected void setUpView() {
        listView= (ListView) findViewById(R.id.listview);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        mData.add("ItemDecoration");
    }
}
