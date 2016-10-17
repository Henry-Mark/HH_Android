package com.henry.hh.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * Date: 2016/10/17. 16:25
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 图片辅助类
 */
public class ImageUtils {

    /**
     * 从本地获取图片
     * @param url
     * @return
     */
    public static Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
