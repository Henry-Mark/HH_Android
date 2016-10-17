package com.henry.hh.interfaces;

import android.graphics.Bitmap;

/**
 * Date: 2016/10/17. 16:38
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 聊天列表中对内容的点击事件监听
 */
public interface OnChatItemClickListener {

    //图片点击
    void onImgClick(int position, Bitmap bitmap);

    //文本点击
    void onTextClick(int position);

    //点击头像
    void onAvatarClick(int position);
}
