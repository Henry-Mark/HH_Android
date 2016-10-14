package com.henry.hh.entity;

/**
 * Date: 2016/10/14. 11:21
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:聊天消息内容
 */
public class Message {
    public final static int MSG_TYPE_TEXT = 0;
    public final static int MSG_TYPE_PHOTO = 1;

    public final static int MSG_STATE_SENDING = 0;
    public final static int MSG_STATE_SUCCESS = 1;
    public final static int MSG_STATE_FAIL = 2;

    public final static int FROM_USER_AVATAR_NOT_CHANGED = 0;
    public final static int FROM_USER_AVATAR_CHANGED = 1;

    /**
     * 消息类型
     * 0.文字消息；
     * 1.图片消息；
     * more 其他
     */
    private int type;
    /**
     * 消息发送状态：
     * 0.正在发送
     * 1.发送成功
     * 2.发送失败
     */
    private int state;
    /**
     * 发送者Id
     */
    private String fromUserId;
    /**
     * 接收者Id
     */
    private String toUserId;
    /**
     * 发送者名字
     */
    private String fromUserName;
    /**
     * 接收者名字
     */
    private String toUserName;
    /**
     * 发送者头像url
     */
    private String fromUserAvatarUrl;
    /**
     * 接收者头像Url
     */
    private String toUserAvatarUrl;
    /**
     * 接收者头像是否改变
     * 0.未改变
     * 1.已改变
     */
    private int isToUserAvatarChanged;
    /**
     * 时间
     */
    private long timeMillis;
    /**
     * 消息内容
     */
    private String content;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setFromUserId(String fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getFromUserId() {
        return fromUserId;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserAvatarUrl(String fromUserAvatarUrl) {
        this.fromUserAvatarUrl = fromUserAvatarUrl;
    }

    public String getFromUserAvatarUrl() {
        return fromUserAvatarUrl;
    }

    public void setToUserId(String toUserId) {
        this.toUserId = toUserId;
    }

    public String getToUserId() {
        return toUserId;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserAvatarUrl(String toUserAvatarUrl) {
        this.toUserAvatarUrl = toUserAvatarUrl;
    }

    public String getToUserAvatarUrl() {
        return toUserAvatarUrl;
    }

    public void setIsToUserAvatarChanged(int isToUserAvatarChanged) {
        this.isToUserAvatarChanged = isToUserAvatarChanged;
    }

    public int getIsToUserAvatarChanged() {
        return isToUserAvatarChanged;
    }

    public void setTimeMillis(long timeMillis) {
        this.timeMillis = timeMillis;
    }

    public long getTimeMillis() {
        return timeMillis;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        String string = "type:"+type
                +";state:"+state
                +";fromUserId:"+fromUserId
                +";fromUserName:"+fromUserName
                +";fromUserAvatarUrl:"+fromUserAvatarUrl
                +";toUserId:"+toUserId
                +";toUserName:"+toUserName
                +";toUserAvatarUrl:"+toUserAvatarUrl
                +";isToUserAvatarChanged:"+isToUserAvatarChanged
                +":timeMillis;"+timeMillis
                +";content:"+content;
        return super.toString();
    }
}
