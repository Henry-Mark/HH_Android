/**
 * 
 */
package com.henry.library;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * @author wangbl
 * 锁屏监听事件
 */
public class LockScreenBroadcastReceiver extends BroadcastReceiver {

    LockScreenListener lockScreenListener = null;

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
     * 锁屏监听接口
     * 
     * @author wangbl
     *
     */
    public interface LockScreenListener {

        public void onScreenOn();

        public void onScreenOff();

        public void onUserPresent();
    }
}
