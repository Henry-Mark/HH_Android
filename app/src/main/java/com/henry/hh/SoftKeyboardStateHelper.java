/*
 * Copyright (c) 2015, 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.henry.hh;

import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;

import com.henry.hh.interfaces.OnSoftKeyboardStateListener;

import java.util.LinkedList;
import java.util.List;

/**
 * Date: 16-10-15 下午8:50
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:软键盘相关辅助类
 */
public class SoftKeyboardStateHelper implements
        ViewTreeObserver.OnGlobalLayoutListener {

    private final List<OnSoftKeyboardStateListener> listeners = new LinkedList<OnSoftKeyboardStateListener>();
    private final View activityRootView;
    private int lastSoftKeyboardHeightInPx;
    private boolean isSoftKeyboardOpened;

    public SoftKeyboardStateHelper(View activityRootView) {
        this(activityRootView, false);
    }

    public SoftKeyboardStateHelper(View activityRootView,
                                   boolean isSoftKeyboardOpened) {
        this.activityRootView = activityRootView;
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        final Rect r = new Rect();
        // r will be populated with the coordinates of your view that area still
        // visible.
        activityRootView.getWindowVisibleDisplayFrame(r);

        final int heightDiff = activityRootView.getRootView().getHeight()
                - (r.bottom - r.top);
        if (!isSoftKeyboardOpened && heightDiff > 100) { // if more than 100
            // pixels, its probably
            // a keyboard...
            isSoftKeyboardOpened = true;
            notifyOnSoftKeyboardOpened(heightDiff);
        } else if (isSoftKeyboardOpened && heightDiff < 100) {
            isSoftKeyboardOpened = false;
            notifyOnSoftKeyboardClosed();
        }
    }

    /**
     * 设定软键盘是否是打开状态
     *
     * @param isSoftKeyboardOpened
     */
    public void setIsSoftKeyboardOpened(boolean isSoftKeyboardOpened) {
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
    }

    /**
     * 软件盘是否是打开状态
     *
     * @return
     */
    public boolean isSoftKeyboardOpened() {
        return isSoftKeyboardOpened;
    }


    /**
     * 获取最新一次软键盘的像素高度，默认值为０
     *
     * @return　最新一次保存的软键盘的像素高度
     */
    public int getLastSoftKeyboardHeightInPx() {
        return lastSoftKeyboardHeightInPx;
    }

    /**
     * 添加监听器
     *
     * @param listener
     */
    public void addSoftKeyboardStateListener(OnSoftKeyboardStateListener listener) {
        listeners.add(listener);
    }

    /**
     * 移除监听器
     *
     * @param listener
     */
    public void removeSoftKeyboardStateListener(
            OnSoftKeyboardStateListener listener) {
        listeners.remove(listener);
    }

    /**
     * 通知软键盘打开
     *
     * @param keyboardHeightInPx
     */
    private void notifyOnSoftKeyboardOpened(int keyboardHeightInPx) {
        this.lastSoftKeyboardHeightInPx = keyboardHeightInPx;

        for (OnSoftKeyboardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyboardOpened(keyboardHeightInPx);
            }
        }
    }

    /**
     * 通知软键盘关闭
     */
    private void notifyOnSoftKeyboardClosed() {
        for (OnSoftKeyboardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyboardClosed();
            }
        }
    }
}
