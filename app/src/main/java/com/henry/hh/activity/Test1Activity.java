package com.henry.hh.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.User;
import com.henry.library.activity.BaseActivity;
import com.litesuits.orm.LiteOrm;

public class Test1Activity extends BaseActivity {
    static LiteOrm liteOrm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        TextView tv=getViewById(R.id.textView);
        User user = (User)getApplication();
        tv.setText(user.getNickname());

        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "hh.db");
        }
        liteOrm.setDebugged(true); // open the log

        setOrm();

        startActivity(Test2Activity.class);

    }

    private void setOrm(){
        User user = new User();
        user.setUserId(0);
        user.setNickname("henry");
        user.setAge(2);
        user.setAddress("liandisljsm;");
        user.setPassword("idsjjns");
        liteOrm.save(user);
    }
}
