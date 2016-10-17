package com.henry.hh.entity;

/**
 * Date: 2016/10/17. 10:47
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: emoji表情
 */
public class Emojicon {
    //在网络传递中的别名
    private String name;
    //在系统中所代表的值
    private byte[] code;
    //code转换为String的值
    private String value;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCode(byte[] code) {
        this.code = code;
    }

    public byte[] getCode() {
        return code;
    }

    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return code转换为String的值
     */
    public String getValue() {
        if (code == null) {
            return null;
        } else {
            return new String(code);
        }
    }

    @Override
    public String toString() {
        String string = "name:" + getName()
                + ";codes:" + getCode()
                + ";value" + getValue();
        return string;
    }
}
