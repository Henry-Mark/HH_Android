package com.henry.hh.entity;

/**
 * Date: 2016/10/26. 13:20
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 好友的实体类
 */
public class Friend extends User {
    //标签，用于给好友分类
    private String label;
    //备注名
    private String remarkName;

    public void setLabel(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public void setRemarkName(String remarkName) {
        this.remarkName = remarkName;
    }

    public String getRemarkName() {
        return remarkName;
    }

    @Override
    public String toString() {
        String string = super.toString() + ";label=" + label
                + ";remarkName=" + remarkName;
        return string;
    }
}
