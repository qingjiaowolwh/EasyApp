package com.ganhuo.string_format;

/**
 * Created by lwh on 2016/7/18.
 */
public class testFormat {
    public static void main(String[] args) {
        String str1 = "雨都%s 这片天%s呢\n"+ "我%c记得 你说我们要快乐\n";
        str1 = String.format(str1, "停了", "灰什么",'还');
        System.out.println(str1);
    }
}
