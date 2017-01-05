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

    /**
     * 星座
     */
    public class Zodiac {
        public static final String ARIES = "白羊座";
        public static final String AQUARIUS = "水瓶座";
        public static final String TAURUS = "金牛座";
        public static final String GEMINI = "双子座";
        public static final String CANCER = "巨蟹座";
        public static final String VIRGO = "处女座";
        public static final String LION = "狮子座";
        public static final String LIBRAS = "天秤座";
        public static final String SCORPIO = "天蝎座";
        public static final String SAGITTARIUS = "射手座";
        public static final String CAPRICORN = "摩羯座";
        public static final String PISCES = "双鱼座";
    }

    /**
     * Tab索引
     */
    public class TabIndex {
        /* 消息列表 */
        public static final int MESSAGE = 10;
        /* 朋友列表 */
        public static final int FRIEND = 11;
        /* 生活圈 */
        public static final int LIVINGCIRCLE = 12;
        /* 探索园 */
        public static final int EXPLORATION = 13;
        /* 我的家 */
        public static final int MYHOME = 14;
    }
}
