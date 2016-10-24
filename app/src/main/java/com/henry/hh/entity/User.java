package com.henry.hh.entity;

import android.app.Application;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Date: 2016/10/20. 14:27
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 关于用户信息的实体
 */
@Table("user")
public class User {

    public final static int USER_COMMON = 0;
    public final static int USER_VIP = 1;

    public final static int SEX_MALE = 0;
    public final static int SEX_FEMALE = 1;
    public final static int SEX_UNKNOWN = 2;

    /**
     * 用户ID
     */
    @PrimaryKey(AssignType.BY_MYSELF)
    private int userId;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 用户名
     */
    private String username;
    /**
     * 用户密码：6-16字节，字母、数字组合
     */
    private String password;
    /**
     * 用户类型：
     * 0.普通用户
     * 1.VIP用户
     */
    private int type;
    /**
     * 年龄
     */
    private int age;
    /**
     * 手机号码
     */
    private String phone;
    /**
     * 性别：
     * 0：男
     * 1：女
     * 2.未知
     */
    private int sex;
    /**
     * 头像url
     */
    private String avatarUrl;
    /**
     * 签名
     */
    private String signature;
    /**
     * 地址
     */
    private String address;
    /**
     * 注册时间 ms
     */
    private long registrationTimeMillis;
    /**
     * 上次访问时间
     */
    private long lastAccessTimeMillis;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getSex() {
        return sex;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getSignature() {
        return signature;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setRegistrationTimeMillis(long registrationTimeMillis) {
        this.registrationTimeMillis = registrationTimeMillis;
    }

    public long getRegistrationTimeMillis() {
        return registrationTimeMillis;
    }

    public void setLastAccessTimeMillis(long lastAccessTimeMillis) {
        this.lastAccessTimeMillis = lastAccessTimeMillis;
    }

    public long getLastAccessTimeMillis() {
        return lastAccessTimeMillis;
    }

    @Override
    public String toString() {
        String string = "userId=" + userId
                + ";nickname=" + nickname
                + ";username=" + username
                + ";password=" + password
                + ";type=" + type
                + ";age=" + age
                + "phone;=" + phone
                + ";sex=" + sex
                + ";avatarUrl=" + avatarUrl
                + ";signature=" + signature
                + ";address=" + address
                + ";registrationTimeMillis=" + registrationTimeMillis
                + ";lastAccessTimeMillis=" + lastAccessTimeMillis;
        return string;
    }
}
