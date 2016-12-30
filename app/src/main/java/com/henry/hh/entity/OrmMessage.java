package com.henry.hh.entity;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Date: 2016/12/30. 15:41
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:用于将消息保存到数据库
 */

public class OrmMessage extends Message {

    private long messageId;

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public long getMessageId() {
        return messageId;
    }

}
