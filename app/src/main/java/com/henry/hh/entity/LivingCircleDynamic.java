package com.henry.hh.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Date: 2016/10/27. 15:30
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:生活圈动态
 */
public class LivingCircleDynamic extends Friend implements Serializable {

    public final static int BACK_TYPE_PIC = 0;
    public final static int BACK_TYPE_COLOR = 1;

    /**
     * 背景颜色：
     * 白色、黑色、红色、绿色、橙色、品红、主背景色
     * 或者使用图片
     */
    public final static int BACK_COLOR_WHITE = 10;
    public final static int BACK_COLOR_BLACK = 11;
    public final static int BACK_COLOR_RED = 12;
    public final static int BACK_COLOR_GREEN = 13;
    public final static int BACK_COLOR_ORANGE = 14;
    public final static int BACK_COLOR_PURPLE = 15;
    public final static int BACK_COLOR_MAIN = 16;
    public final static int BACK_COLOR_BLUE = 17;

    /**
     * 文字颜色：：黑色，白色，主背景色
     */
    public final static int TEXTCOLOR_BLACK = 100;
    public final static int TEXTCOLOR_WHITE = 101;
    public final static int TEXTCOLOR_MAIN = 102;

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
     * BACK_COLOR_WHITE:白色
     * BACK_COLOR_BLACK:黑色
     * BACK_COLOR_RED:红色
     * ......
     */
    private int back_color;
    //内容
    private String content;

    /**
     * 文字颜色：
     * TEXTCOLOR_BLACK：黑色
     * TEXTCOLOR_WHITE：白色
     * TEXTCOLOR_MAIN：主色
     */
    private int textcolor;

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

    public void setBack_color(int back_color) {
        this.back_color = back_color;
    }

    public int getBack_color() {
        return back_color;
    }

    public void setTextcolor(int textcolor) {
        this.textcolor = textcolor;
    }

    public int getTextcolor() {
        return textcolor;
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
                + ";picUrl=" + picUrl
                + ";back_color=" + back_color
                + ";textcolor=" + textcolor
                + ";comments=" + comments
                + ";content=" + content
                + ";praise_count=" + praise_count;
        return super.toString() + string;
    }
}
