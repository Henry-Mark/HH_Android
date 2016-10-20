package com.henry.hh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.User;
import com.henry.library.activity.BaseActivity;
import com.litesuits.orm.LiteOrm;

public class Test2Activity extends BaseActivity {
    static LiteOrm liteOrm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);
        TextView tv = getViewById(R.id.textView2);

        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "hh.db");
        }
        liteOrm.setDebugged(true); // open the log
        tv.setText(liteOrm.query(User.class).get(0).toString());

    }
}
