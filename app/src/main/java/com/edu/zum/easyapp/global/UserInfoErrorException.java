package com.edu.zum.easyapp.global;

import android.content.Context;
import android.widget.Toast;

import com.edu.zum.easyapp.utils.PreferenceHelper;


public class UserInfoErrorException extends Exception {

	public void excute(Context context) {
		Toast.makeText(context, "登录信息过时，请重新登录", Toast.LENGTH_SHORT).show();
		PreferenceHelper.remove("userinfo");
//		context.sendBroadcast(new Intent(BaseActivity.RECEIVER_CLOSE_ALL_ACTIVITY));
//		LoginActivity.actionStart(context, "exception");
	}
}
