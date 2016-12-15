package com.edu.zum.easyapp.fragment;

import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.edu.zum.easyapp.R;
import com.ganhuo.entity.Ganhuo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lwh on 2016/9/19.
 */
public class JsonFragment extends BaseFragment {
    private static final String TAG = JsonFragment.class.getSimpleName();

    @Override
    protected int setLayoutResourceID() {
        return 0;
    }

    private Map<String, String> mMap = new HashMap<>();
    private List<String> mList = new ArrayList<>();
    private List<Ganhuo> ganhous = new ArrayList<>();
    private String[] strings=new String[5];
    private Spinner spinner;

    @Override
    protected void setUpView() {
        super.setUpView();
//        spinner= (Spinner) getContentView().findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(mContext,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        for (int i = 0; i < 5; i++) {
            strings[i]=i+"";

            Ganhuo ganhuo = new Ganhuo();
            ganhuo.setCreatedAt("123233");
            ganhuo.setDesc("你猜啊");
            ganhuo.setId(100l);
            ganhuo.setSource("ffesf");
            ganhous.add(ganhuo);

            mMap.put("" + i, "你知道我在想你吗");

            mList.add(i + "我的未来不是梦");
        }
        JSONObject json = new JSONObject();
        Gson gson = new GsonBuilder().disableHtmlEscaping().create();
        String array=gson.toJson(ganhous,new TypeToken<List<Ganhuo>>(){}.getType());
        JSONArray array1=null;
        try {
             array1=new JSONArray(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            json.put("strings[]", java.util.Arrays.toString(strings));
            json.put("stringstostring", strings.toString());
            json.put("mMap", mMap);
            json.put("mMaptoString", gson.toJson(mMap.toString()));
            json.put("mList", mList);
            json.put("mListtoString", gson.toJson(mList.toString()));
            json.put("ganhuos", array1);
            JSONArray jsonArray=new JSONArray();
            jsonArray.put(1);
            jsonArray.put(2);jsonArray.put(3);
            json.put("jsonarray",jsonArray);
            System.out.println("ganhuos" + array1);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println(TAG + "mjson:" + json.toString());
    }
}
