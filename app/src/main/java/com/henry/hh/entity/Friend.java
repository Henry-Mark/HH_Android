package com.henry.hh.entity;

import android.os.Parcelable;

import com.litesuits.orm.db.annotation.Ignore;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

import java.io.Serializable;

/**
 * Date: 2016/10/26. 13:20
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 好友的实体类
 */
@Table("friend")
public class Friend implements Serializable {
    /**
     * 用户Id
     */
    private int userUid;
    /**
     * 好友Id
     */
    @PrimaryKey(AssignType.BY_MYSELF)
    private int friendUid;
    /**
     * 成为好友时间
     */
    private long beFriendTimeMillis;
    /**
     * 分手时间
     */
    private long splitTimeMillis;
    /**
     * 标签（将好友分类）
     */
    private String label;
    /*备注名*/
    private String remarkName;
    /**
     * 亲密度（1-5，五个等级，五颗心）
     */
    private int intimacy;
    /**
     * 好友信息
     */
    @Ignore
    private User friendInfo = new User();
    /**
     * 最后聊天时间
     */
    private long lastChatTimeMillis;

    /**
     * 最后消息内容
     */
    private String lastContent;
    /**
     * 未读消息条数
     */
    private int amountUnread;
    /**
     * 0为没有搜索，１为已搜索
     */
    private int isSearched;
    /**
     * 搜索时间
     */
    private long searchTimeMillis;

    @Override
    public String toString() {
        String string = "userId=" + userUid
                + ",friendId=" + friendUid
                + ",remarkName=" + remarkName
                + ",beFriendTimeMillis=" + beFriendTimeMillis
                + ",splitTimeMillis=" + splitTimeMillis
                + ",lastChatTimeMillis=" + lastChatTimeMillis
                + ",intimacy=" + intimacy
                + ",amountUnread=" + amountUnread
                + ",lastContent=" + lastContent
                + ",isSearched=" + isSearched
                + ",searchTimeMillis=" + searchTimeMillis
                + ",label=" + label;
        return string;
    }

    public void setFriendUid(int friendUid) {
        this.friendUid = friendUid;
    }

    public int getFriendUid() {
        return friendUid;
    }

    public void setUserUid(int userUid) {
        this.userUid = userUid;
    }

    public int getUserUid() {
        return userUid;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getRemarkName() {
        return remarkName;
    }

    public void setBeFriendTimeMillis(long beFriendTimeMillis) {
        this.beFriendTimeMillis = beFriendTimeMillis;
    }

    public long getBeFriendTimeMillis() {
        return beFriendTimeMillis;
    }

    public void setLastChatTimeMillis(long lastChatTimeMillis) {
        this.lastChatTimeMillis = lastChatTimeMillis;
    }

    public long getLastChatTimeMillis() {
        return lastChatTimeMillis;
    }

    public void setLastContent(String lastContent) {
        this.lastContent = lastContent;
    }

    public String getLastContent() {
        return lastContent;
    }

    public void setSplitTimeMillis(long splitTimeMillis) {
        this.splitTimeMillis = splitTimeMillis;
    }

    public long getSplitTimeMillis() {
        return splitTimeMillis;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setIntimacy(int intimacy) {
        this.intimacy = intimacy;
    }

    public int getIntimacy() {
        return intimacy;
    }

    public void setFriendInfo(User friendInfo) {
        this.friendInfo = friendInfo;
    }

    public User getFriendInfo() {
        return friendInfo;
    }

    public void setAmountUnread(int amountUnread) {
        this.amountUnread = amountUnread;
    }

    public int getAmountUnread() {
        return amountUnread;
    }

    public void setIsSearched(int isSearched) {
        this.isSearched = isSearched;
    }

    public int getIsSearched() {
        return isSearched;
    }

    public void setSearchTimeMillis(long searchTimeMillis) {
        this.searchTimeMillis = searchTimeMillis;
    }

    public long getSearchTimeMillis() {
        return searchTimeMillis;
    }
}
