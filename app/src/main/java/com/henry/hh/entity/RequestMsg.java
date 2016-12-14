package com.henry.hh.entity;

import java.io.Serializable;

/**
 * Date: 2016/12/13. 15:55
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: HTTP请求，返回的数据
 */
public class  RequestMsg<T> implements Serializable {

    private int code;
    //数据
    private T  data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T  getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        String string = "code=" + code + ";data=" + data;
        return super.toString();
    }
}
