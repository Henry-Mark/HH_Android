package com.henry.hh.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.henry.hh.R;
import com.henry.library.activity.TitleActivity;

public class LocationActivity extends TitleActivity {

    public static final String PLACE = "place";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        ((Button)getViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //数据是使用Intent返回
                Intent intent = new Intent();
                //把返回数据存入Intent
                intent.putExtra(PLACE, "My name is linjiqin");
                //设置返回数据
                ((Activity)mContext).setResult(RESULT_OK, intent);
                //关闭Activity
                ((Activity)mContext).finish();
            }
        });
    }
}
