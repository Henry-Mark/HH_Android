package com.henry.hh;

import android.os.Bundle;
import android.widget.Toast;

import com.henry.hh.activity.BaseActivity;
import com.henry.hh.activity.GuideActivity;
import com.henry.hh.activity.MainActivity;
import com.henry.hh.constants.Constants_SP;
import com.henry.hh.utils.SPUtils;

/**
 * Date: 16-9-24 下午9:51
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:  使用ViewPager实现初次进入应用时的引导页
 * (1)判断是否是首次加载应用--采取读取SharedPreferences的方法
 * (2)是，则进入引导GuideActivity；否，则进入MainActivity
 */
public class SplashActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstEnter(Constants_SP.GUIDE_ISFIRST, true)) {
            startActivity(GuideActivity.class);
        } else
            startActivity(MainActivity.class);

        //写入文件，表示不是第一次进入
        SPUtils.put(this, Constants_SP.GUIDE_ISFIRST, false);
        finish();

    }


    /**
     * 判断是否是第一次进去该应用
     *
     * @param key
     * @param defaultObject
     * @return
     */
    private boolean isFirstEnter(String key, boolean defaultObject) {
        if (SPUtils.contains(this, key)) {
            return (boolean) SPUtils.get(this, key, defaultObject);
        }
        return true;
    }


}
