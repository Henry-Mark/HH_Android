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
    //发送
    void send(String content);
    //选择表情
    void selectedEmoji(Emojicon content);
    //选择退格表情
    void selectedBackSpace(Emojicon back);
    //选择某个功能
    void selectedFunction(int index);
}
