package com.henry.hh.entity.base;

import com.litesuits.orm.db.annotation.Collate;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Date: 2016/12/14. 16:44
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 发送的基本消息
 */
public class BaseSendMsg implements Serializable {

    public static final String CHAT = "chat";                           //聊天
    public static final String CHAT_BACK = "chat_back";                   //聊天返回确认，表示发送成功
    public static final String ADDFRIEND = "addfriend";                 // 好友申请
    public static final String ADDFRIEND_CONFORM = "addfriend_conform";                 // 好友申请确认
    public static final String ADDFRIEND_BACK = "addfriend_back";                 // 好友申请消息返回
    public static final String ONLINE_REMINDER = "onlineReminder";    //好友上线提醒
    public static final String OFFLINE_REMINDER = "offlineReminder";    //好友下线提醒
    public static final String DELETEFRIEND = "deletefriend";       //删除好友
    public static final String DELETEFRIEND_BACK = "deletefriend_back";       //删除好友返回消息


    public static final String APPLY_AGREE = "agree";   //同意添加好友
    public static final String APPLY_DISAGREE = "disagree";   //同意添加好友


    /**
     * 消息类型：
     * chat：聊天消息
     * addfriend:添加好友
     */
    public String type = "";
    /*消息内容*/
    public String content = "";
    /*用于识别消息*/
    public long uid;
    /*是否已读，0为未读，1为已读*/
    public int isRead;

    /**
     * 发送时间（发送成功的时间）（ms）
     */
    private long SendTimeMillis;

    public BaseSendMsg() {

    }

    /**
     * @param type
     * @param content
     * @param uid
     * @param sendTimeMillis
     */
    public BaseSendMsg(String type, String content, long uid, long sendTimeMillis) {
        this.type = type;
        this.content = content;
        this.uid = uid;
        this.SendTimeMillis = sendTimeMillis;
    }

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

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUid() {
        return uid;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public int getIsRead() {
        return isRead;
    }

    @Override
    public String toString() {
        String string = "type=" + type + ";uid=" + uid + ";content=" + content + ";getSendTimeMillis=" + getSendTimeMillis() + ";isRead=" + isRead;
        return string;
    }
}
