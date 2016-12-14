package com.henry.hh.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2016/12/13. 15:55
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: HTTP请求，返回的数据
 */
public class RequestMsg<T> implements Serializable {

    private int code;
    //数据
    private T data;

    private List<T> datas = new ArrayList<T>();

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    public List<T> getDatas() {
        return datas;
    }

    @Override
    public String toString() {
        String string = "code=" + code + ";data=" + data + ";datas=" + datas;
        return super.toString();
    }
}
