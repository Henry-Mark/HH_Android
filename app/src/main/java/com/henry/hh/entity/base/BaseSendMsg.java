package com.henry.hh.entity.base;

/**
 * Date: 2016/12/14. 16:44
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 发送的基本消息
 */
public class BaseSendMsg {

    public static final String CHAT = "chat";                           //聊天
    public static final String ADDFRIEND = "addfriend";                 // 添加好友
    public static final String ONLINE_REMINDER = "onlineReminder";    //好友上线提醒
    public static final String OFFLINE_REMINDER = "offlineReminder";    //好友下线提醒

    /**
     * 消息类型：
     * chat：聊天消息
     * addfriend:添加好友
     */
    private String type;
    /*消息内容*/
    private String content;

    /**
     * 发送时间（发送成功的时间）（ms）
     */
    private long SendTimeMillis;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setSendTimeMillis(long sendTimeMillis) {
        SendTimeMillis = sendTimeMillis;
    }

    public long getSendTimeMillis() {
        return SendTimeMillis;
    }

    @Override
    public String toString() {
        String string = "type=" + type + ";content=" + content + ";getSendTimeMillis=" + getSendTimeMillis();
        return string;
    }
}
