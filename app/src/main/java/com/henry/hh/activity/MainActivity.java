package com.henry.hh.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;

import com.henry.hh.R;

/**
 * Date: 16-9-24 下午10:55
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:
 */
public class MainActivity extends BaseActivity {

    private DrawerLayout drawer_layout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    @Override
    protected void bindControls() {
        super.bindControls();
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);


    }
}
