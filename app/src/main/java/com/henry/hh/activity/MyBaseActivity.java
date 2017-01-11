package com.henry.hh.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;

import com.henry.hh.broadcastreceiver.ChattingMsgBroadcastReceiver;
import com.henry.hh.entity.Friend;
import com.henry.hh.entity.Message;
import com.henry.hh.entity.User;
import com.henry.hh.service.ChatService;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.LogUtils;
import com.litesuits.orm.LiteOrm;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;

/**
 * Date: 2016/11/30. 16:28
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: activity基础类
 */
public class MyBaseActivity extends TitleActivity {

    private ChatService chatService;
    private ChattingMsgBroadcastReceiver rhelper;

    protected static LiteOrm liteOrm;
    //用户信息
    protected User user = new User();

    Intent intent = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        user = getMyApplication().getUser();
        //初始化数据库
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(this, "hh.db");
        }
        liteOrm.setDebugged(true); // open the log


        intent = new Intent(this, ChatService.class);
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
                    //设置状态为发送成功
                    message.setState(Message.MSG_STATE_SUCCESS);
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

    /**
     * 停止service
     */
    protected void stopService(){
        if (intent != null) {
            stopService(intent);
        }
    }

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

    /**
     * 从本地数据库中查询好友列表
     *
     * @return
     */
    public List<Friend> getFriendFromOrm() {
        List<Friend> friends = liteOrm.query(Friend.class);

        return getFriendInfo(friends);
    }

    /**
     * 从User表中读取好友具体信息
     *
     * @param friends
     * @return
     */
    protected List<Friend> getFriendInfo(List<Friend> friends) {
        for (Friend friend : friends) {
            List<User> friendinfos = liteOrm.<User>query(new QueryBuilder<User>(User.class)
                    .where("userId=?", friend.getFriendUid()));
            if (friendinfos != null && friendinfos.size() != 0)
                friend.setFriendInfo(friendinfos.get(0));
        }
        return friends;
    }

    /**
     * 将好友列表保存到数据库中
     *
     * @param friends
     */
    public void writeFriendToOrm(List<Friend> friends) {
        liteOrm.save(friends);
//        LogUtils.d(TAG, "friend orm>>" + liteOrm.query(Friend.class));
        for (Friend friend : friends) {
            liteOrm.save(friend.getFriendInfo());
        }
//        LogUtils.d(TAG, "friend info orm>>" + liteOrm.query(User.class));
    }

}
