package com.henry.hh.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.utils.ControlsUtils;
import com.henry.library.activity.BaseActivity;
import com.henry.library.utils.ScreenUtils;

/**
 * Date: 16-9-24 下午10:55
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:
 */
public class MainActivity extends BaseActivity {

    //抽屉控件
    private DrawerLayout drawer_layout;
    //抽屉布局（左侧）
    private LinearLayout mDrawer;

    private TextView mText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindControls();



    }

    /**
     * 绑定控件
     */
    protected void bindControls() {
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawer = (LinearLayout) findViewById(R.id.ll_drawer);
        //设置抽屉宽度为频幕宽度的3/5
        ControlsUtils.setWidth(mDrawer, ScreenUtils.getScreenWidth(this) * 3 / 5);
        mText = (TextView) findViewById(R.id.text1);
    }


}
