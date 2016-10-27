package com.henry.hh.entity;

import java.util.List;

/**
 * Date: 2016/10/27. 15:30
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:生活圈动态
 */
public class LivingCircleDynamic extends Friend {

    public final static int BACK_TYPE_PIC = 0;
    public final static int BACK_TYPE_COLOR = 1;

    public final static int COLOR_WHITE = 10;
    public final static int COLOR_BLACK = 11;
    public final static int COLOR_RED = 12;

    //地点
    private String locaion;
    //发布时间
    private long deliveryTimeMillis;
    /**
     * 背景类型
     * BACK_TYPE_PIC:图片背景
     * BACK_TYPE_COLOR：颜色背景
     */
    private int backType;
    /**
     * 背景图片url
     */
    private String picUrl;
    /**
     * 背景颜色:
     * COLOR_WHITE:白色
     * COLOR_BLACK:黑色
     * COLOR_RED:红色
     * ......
     */
    private int color;
    //内容
    private String content;
    //评论
    private List<Comment> comments;
    //赞的数量
    private int praise_count = 0;

    public void setLocaion(String locaion) {
        this.locaion = locaion;
    }

    public String getLocaion() {
        return locaion;
    }

    public void setDeliveryTimeMillis(long deliveryTimeMillis) {
        this.deliveryTimeMillis = deliveryTimeMillis;
    }

    public long getDeliveryTimeMillis() {
        return deliveryTimeMillis;
    }

    public void setBackType(int backType) {
        this.backType = backType;
    }

    public int getBackType() {
        return backType;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getPraise_count() {
        return praise_count;
    }

    public void setPraise_count(int praise_count) {
        this.praise_count = praise_count;
    }

    @Override
    public String toString() {
        String string = ";locaion=" + locaion
                + ";deliveryTimeMillis=" + deliveryTimeMillis
                + ";backType=" + backType
                + ";picUrl=" + picUrl + color
                + ";comments=" + comments
                + ";content=" + content
                + ";praise_count:" + praise_count;
        return super.toString() + string;
    }
}
