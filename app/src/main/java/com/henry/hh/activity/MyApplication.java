package com.henry.hh.activity;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import com.henry.hh.R;
import com.henry.hh.adapter.LivingCircleAdapter;
import com.henry.hh.entity.LivingCircleDynamic;
import com.henry.hh.entity.User;

/**
 * Date: 2016/10/24. 16:39
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:应用的全局变量
 */
public class MyApplication extends Application {

    public int COLOR_WHITE;
    public int COLOR_GREEN;
    public int COLOR_RED;
    public int COLOR_ORANGE;
    public int COLOR_PURPLE;
    public int COLOR_BLUE;
    public int COLOR_MAIN;
    public int COLOR_BLACK;

    //用户相关信息
    private User user = new User();

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        initColor();
    }

    /**
     * 初始化颜色
     */
    private void initColor() {
        COLOR_WHITE = ContextCompat.getColor(this, R.color.white);
        COLOR_GREEN = ContextCompat.getColor(this, R.color.green);
        COLOR_RED = ContextCompat.getColor(this, R.color.red);
        COLOR_ORANGE = ContextCompat.getColor(this, R.color.orange);
        COLOR_PURPLE = ContextCompat.getColor(this, R.color.purple);
        COLOR_BLUE = ContextCompat.getColor(this, R.color.blue);
        COLOR_MAIN = ContextCompat.getColor(this, R.color.colorMainStyle);
        COLOR_BLACK = ContextCompat.getColor(this, R.color.black);
    }

    /**
     * 根据code值获取颜色
     *
     * @param code
     * @return
     */
    public int useColor(int code) {
        if (code == LivingCircleDynamic.BACK_COLOR_WHITE || code == LivingCircleDynamic.TEXTCOLOR_WHITE)
            return COLOR_WHITE;
        else if (code == LivingCircleDynamic.BACK_COLOR_BLACK || LivingCircleDynamic.TEXTCOLOR_BLACK == code)
            return COLOR_BLACK;
        else if (code == LivingCircleDynamic.BACK_COLOR_MAIN || code == LivingCircleDynamic.TEXTCOLOR_MAIN)
            return COLOR_MAIN;
        else if (code == LivingCircleDynamic.BACK_COLOR_RED)
            return COLOR_RED;
        else if (code == LivingCircleDynamic.BACK_COLOR_ORANGE)
            return COLOR_ORANGE;
        else if (code == LivingCircleDynamic.BACK_COLOR_GREEN)
            return COLOR_GREEN;
        else if (code == LivingCircleDynamic.BACK_COLOR_PURPLE)
            return COLOR_PURPLE;
        else if (code == LivingCircleDynamic.BACK_COLOR_BLUE)
            return COLOR_BLUE;
        else
            return -1;
    }
}
