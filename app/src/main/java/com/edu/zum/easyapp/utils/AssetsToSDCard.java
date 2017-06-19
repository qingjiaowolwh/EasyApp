package com.edu.zum.easyapp.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;

/**
 * Created by lwh on 2016/7/11.
 */
public class AssetsToSDCard {
    /**
     * @param mContext
     * @param fileName  压缩文件名字
     * @param outputDirectory
     * @param isReWrite 是否覆盖
     */
    public static void unZip(Context mContext, String fileName, String outputDirectory, boolean isReWrite) {
        // 创建解压目标目录
        try {
            File file = new File(outputDirectory);
            // 如果目标目录不存在，则创建
            if (!file.exists()) {
                file.mkdirs();
            }
            // 打开压缩文件
            InputStream inputStream = mContext.getAssets().open(fileName);
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            // 读取一个进入点
            java.util.zip.ZipEntry zipEntry = zipInputStream.getNextEntry();
            // 使用1Mbuffer
            byte[] buffer = new byte[1024 * 1024];
            // 解压时字节计数
            int count = 0;
            // 如果进入点为空说明已经遍历完所有压缩包中文件和目录
            while (zipEntry != null) {
                // 如果是一个目录
                if (zipEntry.isDirectory()) {
                    file = new File(outputDirectory + File.separator + zipEntry.getName());
                    // 文件需要覆盖或者是文件不存在
                    if (isReWrite || !file.exists()) {
                        file.mkdir();
                    }
                } else {
                    // 如果是文件
                    file = new File(outputDirectory + File.separator + zipEntry.getName());
                    // 文件需要覆盖或者文件不存在，则解压文件
                    if (isReWrite || !file.exists()) {
                        file.createNewFile();
                        FileOutputStream fileOutputStream = new FileOutputStream(file);
                        while ((count = zipInputStream.read(buffer)) > 0) {
                            fileOutputStream.write(buffer, 0, count);
                        }
                        fileOutputStream.close();
                    }
                }
                // 定位到下一个文件入口
                zipEntry = zipInputStream.getNextEntry();
            }
            zipInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void fileToSDCard(Context mContext, String fileName, String outputDirectory, boolean isReWrite){
        try {
            File file = new File(outputDirectory);
            if (!file.exists()) {
                file.mkdirs();
            }
            file = new File(outputDirectory + File.separator + fileName);
            if (isReWrite || !file.exists()) {
                InputStream inputStream=mContext.getAssets().open(fileName);
                FileOutputStream fileOutputStream=new FileOutputStream(file);
                byte[] buffer=new byte[1024*1024];
                int count=0;
                    while ((count=inputStream.read(buffer))>0){
                        fileOutputStream.write(buffer,0,count);
                    }
                inputStream.close();
                fileOutputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
