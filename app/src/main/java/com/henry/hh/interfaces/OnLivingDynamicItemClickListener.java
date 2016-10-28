package com.henry.hh.interfaces;

import java.util.List;

/**
 * Date: 2016/10/28. 11:28
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 生活圈动态，Item中控件接口
 */
public interface OnLivingDynamicItemClickListener {
    //点击头像
    void onAvatarClick(List data, int position);

    //点击用户名
    void onUserClick(List data, int position);

    //点击内容
    void onContentClick(List data, int position);

    //点击聊天
    void onContactClick(List data, int position);

    //点击评论
    void onCommentClick(List data, int position);

    //点击赞
    void onPraiseClick(List data, int position);
}
