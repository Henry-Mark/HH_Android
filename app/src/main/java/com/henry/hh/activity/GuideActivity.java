package com.henry.hh.activity;

import android.os.Bundle;
import android.view.Window;

import com.henry.hh.R;
import com.henry.hh.utils.Typefaces;
import com.henry.library.activity.BaseActivity;
import com.henry.titanic.Titanic;
import com.henry.titanic.TitanicTextView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * app引导页面
 */
public class GuideActivity extends BaseActivity {

    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_guide);
        TitanicTextView tv = (TitanicTextView) findViewById(R.id.titanic);

        // set fancy typeface
        tv.setTypeface(Typefaces.get(this, "Satisfy-Regular.ttf"));

        // start animation
        final Titanic titanic = new Titanic();
        titanic.start(tv);

        timer = new Timer(true);
        //５面跳转到Activity
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(mContext, MainActivity.class);
            }
        }, 5000, 1000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.cancel();
    }
}
