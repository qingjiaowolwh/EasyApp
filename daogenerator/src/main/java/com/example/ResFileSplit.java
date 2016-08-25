package com.example;

import java.io.File;

/**
 * 重命名资源图片名并分别导入对应的资源文件夹
 * 
 * @author wty 2016年8月8日 11:34:33
 * 
 */
public class ResFileSplit {

	// @1x
	private static final String MDPI_PATH = "C:\\Users\\123\\AndroidStudioProjects\\MonkeyWord\\app\\src\\main\\res\\drawable-mdpi\\";
	// @2x
	private static final String XHDPI_PATH = "C:\\Users\\123\\AndroidStudioProjects\\MonkeyWord\\app\\src\\main\\res\\drawable-xhdpi\\";
	// @3x
	private static final String XXHDPI_PATH = "C:\\Users\\123\\AndroidStudioProjects\\MonkeyWord\\app\\src\\main\\res\\drawable-xxhdpi\\";

	private static final String END_PNG = ".png";

	public static void main(String[] args) {
		// 原始文件路径
		String path = "C:/Users/123/Desktop/猴哥背词/猴哥背词application/修改包名后/3.0/UI图/012 进入PK场 安卓/assets/";
		File files = new File(path);
		for (File file : files.listFiles()) {
			if (file.getName().contains("@2x")) {
				if (file.getName().endsWith(END_PNG)) {
					file.renameTo(new File(XHDPI_PATH + file.getName().replace("@2x.png", "") + END_PNG));
				}
			} else if (file.getName().contains("@3x")) {
				if (file.getName().endsWith(END_PNG)) {
					file.renameTo(new File(XXHDPI_PATH + file.getName().replace("@3x.png", "") + END_PNG));
				}
			} else {
				if (file.getName().endsWith(END_PNG)) {
					file.renameTo(new File(MDPI_PATH + file.getName()));
				}
			}
		}
	}

}
