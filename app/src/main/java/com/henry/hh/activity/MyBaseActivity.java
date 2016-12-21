package com.henry.hh.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.henry.hh.broadcastreceiver.ChattingMsgBroadcastReceiver;
import com.henry.hh.entity.Message;
import com.henry.hh.entity.User;
import com.henry.hh.service.ChatService;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.LogUtils;

/**
 * Date: 2016/11/30. 16:28
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: activity基础类
 */
public class MyBaseActivity extends TitleActivity {

    private ChatService chatService;
    private ChattingMsgBroadcastReceiver rhelper;
    //用户信息
    protected User user = new User();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = getMyApplication().getUser();

        Intent intent = new Intent(this, ChatService.class);
        startService(intent);// 启动服务

        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);//绑定目标Service
        rhelper = new ChattingMsgBroadcastReceiver(this);
        rhelper.registerAction(ChattingMsgBroadcastReceiver.RECEIVE_MSG);
        rhelper.receive(new ChattingMsgBroadcastReceiver.ReceiverMsg() {
            @Override
            public void onReceiveMsg(Intent intent) {
                if (intent.getAction().equals(ChattingMsgBroadcastReceiver.RECEIVE_MSG)) {
                    String string = intent.getStringExtra(ChattingMsgBroadcastReceiver.MSG);
                    Message message = gson.fromJson(string, Message.class);
                    onReceive(message);
                }
            }
        });
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);// 解除绑定，断开连接
        //取消广播接收器
        unregisterReceiver(rhelper);
    }

    /**
     * 发送聊天消息
     *
     * @param msg
     */
    protected void sendChatMsg(String msg) {
        chatService.sendMessage(msg);
    }

    /**
     * 全局变量
     *
     * @return
     */
    public MyApplication getMyApplication() {
        return (MyApplication) getApplication();
    }

    /**
     * 接收消息
     *
     * @param message
     */
    protected void onReceive(Message message) {
        LogUtils.d(TAG, "onReceive....");
    }
}
