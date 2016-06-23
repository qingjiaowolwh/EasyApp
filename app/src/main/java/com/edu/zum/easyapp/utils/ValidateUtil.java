package com.edu.zum.easyapp.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.text.TextUtils;

public class ValidateUtil {
	
    public static boolean isEmpty(String str) {
        if (str == null || str.length() == 0 || str.equalsIgnoreCase("null") || str.isEmpty() || str.equals("")) {
            return true;
        } else {
            return false;
        }
    }
	
    public static boolean validatePassword(String str) {
        if(str == null || str.length() < 6 || str.length() > 20){
        	return false;
        }else{
        	return true;
        }
    }
	
	public static boolean validateMobileNO(String paramString) {
		if (TextUtils.isEmpty(paramString)) {
			return false;
		}
//		return paramString.matches("[1][3578]\\d{9}");
		return paramString.matches("^((17[0-9])|(14[0-9])|(13[0-9])|(15[^4,\\D])|(18[0,0-9]))\\d{8}$");
	}

	public static boolean validateEmail(String email) {
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);

		return m.matches();
	}
	
	public static boolean validatesEqual(String str1,String str2){
		boolean isEqual=false;
		
		if(str1==null||str2==null){
			isEqual=false;
		}else if(str1.equals(str2)){
			isEqual=true;
		}else{
			isEqual=false;
		}
		
		return isEqual;
	}
}
