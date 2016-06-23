package com.edu.zum.easyapp.global;


import com.edu.zum.easyapp.utils.EnvironmentStateUtils;

public class Constants {
    public static final boolean DEBUG = true;

    public static final String TESTACCOUNT = "18310816974";
    public static final String TEXTPASSWORD = "123456";

    public static final String HOST_COMMON = "http://gank.io/";

    // 外部存储设备的根路径
    public static final String ExternalStorageRootPath = EnvironmentStateUtils.getExternalStorageDirectory().getPath();
    public static final String BasePath = ExternalStorageRootPath + "/app_name/";
    // 文件存放路径
    public static final String FileCachePath = BasePath + "FileCache/";
    // 保存图片
    public static final String ImageCachePath = BasePath + "ImageCache/";
    // 下载存储地址
    public static final String DOWNLOADPath = BasePath + "DWONLOAD/";

    public static final String IMAGE_LOADER_CACHE_PATH = "/easyApp/Images/";


}