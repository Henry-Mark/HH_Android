package com.henry.hh.entity;

import java.io.Serializable;

/**
 * Date: 2016/11/4. 14:59
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:附近的人的信息
 */
public class PeopleNearby extends User implements Serializable {
    //附近人的名称
    private String nameNearby;
    //距离
    private long distance;
    //展示图片Url
    private String showImgUrl;
    //星座
    private String zodiac;

    public void setNameNearby(String nameNearby) {
        this.nameNearby = nameNearby;
    }

    public String getNameNearby() {
        return nameNearby;
    }

    public void setDistance(long distance) {
        this.distance = distance;
    }

    public long getDistance() {
        return distance;
    }

    public void setShowImgUrl(String showImgUrl) {
        this.showImgUrl = showImgUrl;
    }

    public String getShowImgUrl() {
        return showImgUrl;
    }

    public void setZodiac(String zodiac) {
        this.zodiac = zodiac;
    }

    public String getZodiac() {
        return zodiac;
    }

    @Override
    public String toString() {
        String string = ";nameNearby=" + nameNearby
                + ";distance=" + distance
                + ";showImgUrl=" + showImgUrl
                + ";zodiac=" + zodiac;
        return super.toString();
    }
}
