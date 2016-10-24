package com.henry.hh.entity;

import android.app.Application;

/**
 * Date: 2016/10/24. 16:39
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:应用的全局变量
 */
public class GlobalData extends Application {
    //用户相关信息
    private User user;

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
