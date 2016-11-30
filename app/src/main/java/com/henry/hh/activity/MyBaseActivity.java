package com.henry.hh.activity;

import com.henry.library.activity.TitleActivity;

/**
 * Date: 2016/11/30. 16:28
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: activity基础类
 */
public class MyBaseActivity extends TitleActivity {


    /**
     * 全局变量
     *
     * @return
     */
    public MyApplication getMyApplication() {
        return (MyApplication) getApplication();
    }
}
