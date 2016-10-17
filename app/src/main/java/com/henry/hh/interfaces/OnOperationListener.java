package com.henry.hh.interfaces;

import com.henry.hh.entity.Emojicon;
import com.henry.hh.entity.Faceicon;


/**
 * Date: 16-10-15 下午10:01
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 表情栏顶部按钮的监听器
 */
public interface OnOperationListener {
    void send(String content);

    void selectedFace(Faceicon content);

    void selectedEmoji(Emojicon content);

    void selectedBackSpace(Emojicon back);

    void selectedFunction(int index);
}
