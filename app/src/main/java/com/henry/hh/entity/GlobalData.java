package com.henry.hh.entity;

import android.app.Application;
import android.support.v4.content.ContextCompat;

import com.henry.hh.R;

/**
 * Date: 2016/10/24. 16:39
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:应用的全局变量
 */
public class GlobalData extends Application {

    public int COLOR_WHITE;
    public int COLOR_GREEN;
    public int COLOR_RED;
    public int COLOR_ORANGE;
    public int COLOR_PURPLE;
    public int COLOR_BLUE;
    public int COLOR_MAIN;
    public int COLOR_BLACK;

    //用户相关信息
    private User user;

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
        COLOR_MAIN = ContextCompat.getColor(this,R.color.colorMainStyle);
        COLOR_BLACK = ContextCompat.getColor(this,R.color.black);
    }
}
