package com.henry.hh.activity;

import android.os.Bundle;
import android.os.PersistableBundle;

import com.henry.hh.R;

/**
 * Date: 16-9-24 下午10:55
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:
 */
public class MainActivity extends BaseActivity {
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void bindControls() {
        super.bindControls();

    }
}
