package com.henry.hh.constants;

import android.os.Environment;

import java.io.File;

/**
 * Date: 16-9-24 下午10:39
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 保存sharedpreference的key值
 */
public class Constants {

    //引导页，第一页
    public static final String GUIDE_ISFIRST = "isFisrt_guide";
    //根目录下项目文件夹
    public static final String PROGREM_FOLDER = Environment.getExternalStorageDirectory() + File.separator + "IMU";
    //缓存文件夹
    public static final String CACHE_FOLDER = PROGREM_FOLDER + File.separator + "cache";
    //缓存图片文件夹
    public static final String CACHE_IMG_FOLDER = CACHE_FOLDER + File.separator + "img";
    //头像路径
    public static final String PATH_AVATAR = CACHE_IMG_FOLDER + File.separator + "head.png";
}
