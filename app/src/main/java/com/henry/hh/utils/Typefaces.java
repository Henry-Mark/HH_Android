package com.henry.hh.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;

import java.util.Hashtable;

/**
 * Date: 16-9-28 下午9:06
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 设置字体
 */
public class Typefaces {
    private static final String TAG = "Typefaces";
    private static final Hashtable<String, Typeface> cache = new Hashtable<String, Typeface>();

    /**
     * 获取字体
     * @param c　上下文
     * @param assetPath 　assets目录下字体文件名
     * @return
     */
    public static Typeface get(Context c, String assetPath) {
        synchronized (cache) {
            if (!cache.containsKey(assetPath)) {
                try {
                    Typeface t = Typeface.createFromAsset(c.getAssets(), assetPath);
                    cache.put(assetPath, t);
                } catch (Exception e) {
                    Log.e(TAG, "Could not get typeface '" + assetPath + "' because " + e.getMessage());
                    return null;
                }
            }

            return cache.get(assetPath);
        }
    }
}
