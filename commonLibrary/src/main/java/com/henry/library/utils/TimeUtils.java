package com.henry.library.utils;

import android.content.Context;
import android.text.format.DateUtils;

/**
 * Date: 2016/10/10. 10:14
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 时间相关的工具类
 */
public class TimeUtils {

    /**
     * 获取系统当前时间，单位ms
     * @return
     */
    public static long getSysCurrentMillis(){
        return System.currentTimeMillis();
    }

    /**
     * 返回相对于当前时间的一个时间字符串：
     * 在同一天显示时分；
     * 在不同一天，显示月日；
     * 在不同一年，显示年月日
     * @param context 上下文
     * @param millis    对应时间的毫秒数
     * @return
     */
    public static CharSequence getRelativeTime(Context context,long millis){
        return DateUtils.getRelativeTimeSpanString(context,millis);
    }

    public static CharSequence getRelativeTime(long millis){
        return DateUtils.getRelativeTimeSpanString(millis);
    }
}
