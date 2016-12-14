package com.henry.hh.entity.base;

/**
 * Date: 2016/12/14. 10:06
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: Http返回包含的key值，message
 */
public class BaseMsgBean {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        String string = message;
        return message;
    }
}
