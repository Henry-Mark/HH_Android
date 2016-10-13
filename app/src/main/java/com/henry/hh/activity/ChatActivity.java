package com.henry.hh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.library.activity.BaseActivity;
import com.henry.library.activity.TitleActivity;

public class ChatActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        TextView textView = getViewById(R.id.text);
        textView.setText("sodhfsa;dgnfv;lse");

        showBackwardView("返回", true);
        showForwardView("put", true);
        setTitle("IMU");
        textView.setOnClickListener(this);
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
    }

    @Override
    protected void onForward(View forwardView) {
        super.onForward(forwardView);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.text:
                showToast("jdgj;sjg;");
                break;
            default:
                break;
        }
    }
}
