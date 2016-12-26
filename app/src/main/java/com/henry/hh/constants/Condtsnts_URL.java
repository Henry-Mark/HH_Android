package com.henry.hh.constants;

/**
 * Date: 2016/10/21. 15:19
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 保存访问的地址
 */
public class Condtsnts_URL {

    //服务器IP、端口号
    public static final String IP_PORT = "172.16.50.126:9876/";
//    public static final String IP_PORT = "192.168.0.105:9876/";

    public static final String HTTP_HEAD = "http://" + IP_PORT + "imu/";
    //聊天地址
    public static final String WEBSOCKET_CHAT = "ws://" + IP_PORT + "imu/message?userId=";
    //登录
    public static final String LOGIN = HTTP_HEAD + "Login";
    //好友列表
    public static final String FRIENDLIST = HTTP_HEAD + "friendlist";

}
