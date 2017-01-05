package com.henry.hh.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.henry.hh.broadcastreceiver.ChattingMsgBroadcastReceiver;
import com.henry.hh.R;
import com.henry.hh.entity.User;
import com.henry.hh.service.ChatService;
import com.henry.library.activity.BaseActivity;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.LogUtils;
import com.henry.library.utils.ToastUtils;
import com.litesuits.orm.LiteOrm;

public class Test1Activity extends TitleActivity {
    static LiteOrm liteOrm;
    ChatService chatService;
    ChattingMsgBroadcastReceiver rhelper;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        tv = getViewById(R.id.textView);
//        MyApplication data = (MyApplication) getApplication();
//        User user = data.getUser();
//        tv.setText(user.getNickname());
//
//        if (liteOrm == null) {
//            liteOrm = LiteOrm.newSingleInstance(this, "hh.db");
//        }
//        liteOrm.setDebugged(true); // open the log

//        setOrm();

        Intent intent = new Intent(this, ChatService.class);
        startService(intent);// 启动服务
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);//绑定目标Service
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatService.sendMessage("all@hhhh");
            }
        });

    }

    @Override
    protected void onStart() {
        rhelper = new ChattingMsgBroadcastReceiver(this);
        rhelper.registerAction(ChattingMsgBroadcastReceiver.RECEIVE_MSG);
        rhelper.receive(new ChattingMsgBroadcastReceiver.ReceiverMsg() {
            @Override
            public void onReceiveMsg(Intent intent) {
                if (intent.getAction().equals(ChattingMsgBroadcastReceiver.RECEIVE_MSG)) {
                    String string = intent.getStringExtra(ChattingMsgBroadcastReceiver.MSG);
                    LogUtils.d(TAG, string);
                    tv.setText(string);
                }
            }
        });
        super.onStart();
    }

    // 在Activity中，我们通过ServiceConnection接口来取得建立连接与连接意外丢失的回调
    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            // 建立连接
            // 获取服务的操作对象
            ChatService.MyBinder binder = (ChatService.MyBinder) service;
            chatService = binder.getService();// 获取到的Service即PlayerService
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // 连接断开
        }
    };

    private void setOrm() {
        User user = new User();
        user.setUserId(0);
        user.setNickname("henry");
        user.setAge(2);
        user.setAddress("liandisljsm;");
        user.setPassword("idsjjns");
        liteOrm.save(user);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);// 解除绑定，断开连接
    }

    @Override
    protected void onStop() {
        //取消广播接收器
        unregisterReceiver(rhelper);
        super.onStop();
    }

}
