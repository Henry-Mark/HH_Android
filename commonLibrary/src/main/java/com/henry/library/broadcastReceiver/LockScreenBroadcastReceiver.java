/**
 *
 */
package com.henry.library.broadcastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

/**
 * Date: 16-11-17 下午7:06
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 锁屏监听广播
 */
public class LockScreenBroadcastReceiver extends BroadcastReceiver {

    LockScreenListener lockScreenListener = null;
    private boolean isRegisterReceiver = false;
    private String TAG = "LockScreenBroadcastReceiver";

    public LockScreenBroadcastReceiver(LockScreenListener lockScreenListener) {
        super();
        this.lockScreenListener = lockScreenListener;
    }

    @Override
    public void onReceive(Context content, Intent intent) {

        String action = intent.getAction();
        if (lockScreenListener != null) {
            if (action.equals(Intent.ACTION_SCREEN_ON)) {
                lockScreenListener.onScreenOn();
            } else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
                lockScreenListener.onScreenOff();
            } else if (action.equals(Intent.ACTION_USER_PRESENT)) {
                lockScreenListener.onUserPresent();
            }
        }
    }

    /**
     * 注册锁屏监听广播
     *
     * @param mContext
     */
    public void registerScreenActionReceiver(Context mContext) {
        if (!isRegisterReceiver) {
            isRegisterReceiver = true;

            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(Intent.ACTION_USER_PRESENT);
            Log.i(TAG, "注册屏幕解锁、加锁广播接收者...");
            mContext.registerReceiver(this, filter);
        }
    }

    /**
     * 取消注册锁屏监听广播
     *
     * @param mContext
     */
    public void unRegisterScreenActionReceiver(Context mContext) {
        if (isRegisterReceiver) {
            isRegisterReceiver = false;
            Log.i(TAG, "注销屏幕解锁、加锁广播接收者...");
            mContext.unregisterReceiver(this);
        }
    }

    /**
     * 锁屏监听接口
     */
    public interface LockScreenListener {

        /**
         * 屏幕锁定，暗
         */
        public void onScreenOn();

        /**
         * 屏幕变亮，未解锁
         */
        public void onScreenOff();

        /**
         * 正在使用，解锁后
         */
        public void onUserPresent();
    }
}
