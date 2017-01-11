package com.henry.hh.entity;

import com.henry.hh.entity.base.BaseSendMsg;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Date: 2016/10/14. 11:21
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:聊天消息内容
 */
@Table("message")
public class Message extends BaseSendMsg {
    public final static int MSG_TYPE_TEXT = 0;
    public final static int MSG_TYPE_PHOTO = 1;

    public final static int MSG_STATE_SENDING = 0;
    public final static int MSG_STATE_SUCCESS = 1;
    public final static int MSG_STATE_FAIL = 2;
    public final static int MSG_STATE_ALREADY_ARGEE = 5;
    public final static int MSG_STATE_ALREADY_REFUSE = 6;


    /**
     * 消息类型
     * 0.文字消息；
     * 1.图片消息；
     * more 其他
     */
    private int messageType;
    /**
     * 消息发送状态：
     * 0.正在发送
     * 1.发送成功
     * 2.发送失败
     * <p>
     * 5.好友申请，已同意
     * 6.好友申请，已同意
     */
    private int state;
    /*消息发送者ID*/
    private int fromUserId;
    /*消息接收者ID*/
    private int toUserId;

    /*缩略图Url*/
    private String thumbnailUrl;
    @PrimaryKey(AssignType.BY_MYSELF)
    private String messageId = null;

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }

    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    public int getFromUserId() {
        return fromUserId;
    }

    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }


    public int getToUserId() {
        return toUserId;
    }


    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessageId() {
        return messageId;
    }

    @Override
    public void setUid(long uid) {
        super.setUid(uid);
    }

    @Override
    public String toString() {
        String string = super.toString()
                + ";messageType:" + messageType
                + ";state:" + state
                + ";fromUserId:" + fromUserId
                + ";toUserId:" + toUserId
                + ";messageId:" + messageId
                + ";thumbnailUrl:" + thumbnailUrl;
        return string;
    }
}
