package com.henry.hh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.User;
import com.henry.library.activity.BaseActivity;

public class Test1Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        TextView tv=getViewById(R.id.textView);
        User user = (User)getApplication();
        tv.setText(user.getNickname());
    }
}
