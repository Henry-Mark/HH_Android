package com.henry.hh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

/**
 * Date: 2016/10/21. 17:16
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 在动态注册中可将BroadcastReceiver的继承类进行封装，添加构造函数和BroadcastReceiver注册
 */
public class BroadcastReceiverHelper extends BroadcastReceiver {
   private Context context=null;
    private BroadcastReceiverHelper receiver;
    private ReceiverMsg receiverMsg;

    public BroadcastReceiverHelper(Context context){
        this.context = context;
        receiver = this;
    }

    //注册
    public void registerAction(String action){
        IntentFilter filter=new IntentFilter();
        filter.addAction(action);
        context.registerReceiver(receiver, filter);}
    @Override
    public void onReceive(Context context, Intent intent) {
        receiverMsg.onReceiveMsg(intent);

    }

    public void receive(ReceiverMsg msg){
        this.receiverMsg = msg;
    }

    public interface ReceiverMsg{
        void onReceiveMsg(Intent intent);
    }
}
