package com.henry.hh.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

public class ChatService extends Service {
    private final String TAG = "ChatService";
    private WebSocketConnection mConnect = new WebSocketConnection();

    private static final String wsurl = "ws://172.16.50.126:8080/websocketServer";
    public ChatService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate...");
        connect();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG,"onBind...");
        return new MyBinder();
    }
    // 已取代onStart方法--onStart方法是在Android2.0之前的平台使用的.
    // 在2.0及其之后，则需重写onStartCommand方法，同时，旧的onStart方法则不会再被直接调用
    // （外部调用onStartCommand，而onStartCommand里会再调用 onStart。在2.0之后，
    // 推荐覆盖onStartCommand方法，而为了向前兼容，在onStartCommand依然会调用onStart方法。
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand...");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG,"onUnbind...");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG,"onDestroy...");
        super.onDestroy();
    }

    /**
     * websocket连接，接收服务器消息
     */
    private void connect() {
        Log.i(TAG, "ws connect....");
        try {
            mConnect.connect(wsurl, new WebSocketHandler() {
                @Override
                public void onOpen() {
                    Log.i(TAG, "Status:Connect to " + wsurl);
                    sendUsername("Jack");
                }

                @Override
                public void onTextMessage(String payload) {
                    Log.i(TAG, payload);
//                    mText.setText(payload != null ? payload : "");
//                    mConnect.sendTextMessage("I am android client");
                    Intent intent = new Intent();
                    intent.putExtra("T",payload);
sendBroadcast(intent);
                }

                @Override
                public void onClose(int code, String reason) {
                    Log.i(TAG, "Connection lost..");
                }
            });
        } catch (WebSocketException e) {
            e.printStackTrace();
        }
    }



    /**
     * 发送用户名给服务器
     * @param user
     */
    private void sendUsername(String user) {

        if (user != null && user.length() != 0)
            mConnect.sendTextMessage(user);
        else
            Toast.makeText(getApplicationContext(), "不能为空", Toast.LENGTH_SHORT).show();
    }

    /**
     * 发送消息
     *
     * @param msg
     */
    public void sendMessage(String msg) {
        if (mConnect.isConnected()) {
            mConnect.sendTextMessage(msg);
        } else {
            Log.i(TAG, "no connection!!");
        }
    }


    // IBinder是远程对象的基本接口，是为高性能而设计的轻量级远程调用机制的核心部分。但它不仅用于远程
    // 调用，也用于进程内调用。这个接口定义了与远程对象交互的协议。
    // 不要直接实现这个接口，而应该从Binder派生。
    // Binder类已实现了IBinder接口
    public class MyBinder extends Binder {
        /**
         * 获取Service的方法
         * @return 返回ChatService
         */
        public  ChatService getService(){
            return ChatService.this;
        }
    }
}