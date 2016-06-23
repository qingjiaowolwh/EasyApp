package com.edu.zum.easyapp.api;



import com.edu.zum.easyapp.model.UserInfo;
import com.edu.zum.easyapp.utils.PreferenceHelper;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by asus on 2016/3/10.
 */
public class ApiTools {

    public static JSONObject buildTokenAndCheckID() {
        JSONObject data = new JSONObject();
        UserInfo info = PreferenceHelper.getUserInfo();
        try {
            if (info == null) {
                data.put("checkID", "");
                data.put("token", "");
            } else {
                data.put("checkID", info.getCheckID());
                data.put("token", info.getToken());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 用户登录
     */
    public static String reqLogin(String phone, String password) {
        JSONObject params = new JSONObject();
        JSONObject data = buildTokenAndCheckID();
        try {
            params.put("phone", phone);
            params.put("password", password);
            data.put("params", params);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return data.toString();
    }
}
