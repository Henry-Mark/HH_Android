package com.henry.hh.interfaces;

/**
 * Date: 16-10-15 下午10:23
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 软键盘监听接口
 */
public interface OnSoftKeyboardStateListener {
    //打开软键盘
    void onSoftKeyboardOpened(int keyboardHeightInPx);
    //关闭软键盘
    void onSoftKeyboardClosed();
}
