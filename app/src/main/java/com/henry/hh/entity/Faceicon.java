package com.henry.hh.entity;

import java.io.Serializable;

/**
 * Date: 16-10-15 下午10:04
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:
 */
public class Faceicon implements Serializable {

    private String name; //网络传输中的值
    private String path; //在系统中的绝对路径
    private String fileName; //图片文件名

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        String string = "name:" + name
                + ";path:" + path
                + ";fileName:" + fileName;
        return string;
    }
}
