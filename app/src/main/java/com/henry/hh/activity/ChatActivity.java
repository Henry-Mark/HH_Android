package com.henry.hh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.library.activity.BaseActivity;

public class ChatActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView textView = getViewById(R.id.text);
        textView.setText("sodhfsa;dgnfv;lse");
    }
}
