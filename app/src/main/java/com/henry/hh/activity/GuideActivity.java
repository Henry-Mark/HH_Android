package com.henry.hh.activity;

import android.os.Bundle;

import com.henry.hh.R;
import com.henry.library.activity.BaseActivity;

/**
 * app引导页面
 */
public class GuideActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        startActivity(MainActivity.class);
    }
}
