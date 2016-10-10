package com.henry.hh.entity;

/**
 * Date: 2016/10/10. 11:04
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 聊天室相关实体类
 */
public class ChattingRoom {

    /**
     * 头像url
     */
    private String avatarUrl;
    /**
     * 用户ID
     */
    private String userId;
    /**
     * 消息内容
     */
    private String content;
    /**
     * 最新消息时间
     */
    private long messageTime;
    /**
     * 未读消息条数
     */
    private int amountUnread;

    /**
     * 设置未读消息条数
     *
     * @param amountUnread
     */
    public void setAmountUnread(int amountUnread) {
        this.amountUnread = amountUnread;
    }

    /**
     * 获取未读消息条数
     *
     * @return
     */
    public int getAmountUnread() {
        return amountUnread;
    }

    /**
     * 设置头像url
     *
     * @param avatarUrl
     */
    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    /**
     * 获取头像url
     *
     * @return
     */
    public String getAvatarUrl() {
        return avatarUrl;
    }

    /**
     * 设置消息内容
     *
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取消息内容
     *
     * @return
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置最新消息时间
     *
     * @param messageTime
     */
    public void setMessageTime(long messageTime) {
        this.messageTime = messageTime;
    }

    /**
     * 获取最新消息时间
     *
     * @return
     */
    public long getMessageTime() {
        return messageTime;
    }

    /**
     * 设置聊天对象的id
     *
     * @param userId
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * 获取聊天对象的id
     *
     * @return
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 重写toString方法，便于查看
     *
     * @return
     */
    @Override
    public String toString() {
        String string = "avatarUrl:" + avatarUrl + ";userId:" + userId + ";content:"
                + content + ";messageTime:" + messageTime + ":amountUnread:" + amountUnread;
        return string;
    }
}
