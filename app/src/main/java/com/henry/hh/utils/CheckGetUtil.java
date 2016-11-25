package com.henry.hh.utils;

/**
 * Date: 2016/11/25. 9:32
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:验证码相关工具类
 */
public class CheckGetUtil {
    /**
     * 获得画干扰直线的位置
     *
     * @param height
     * @param width
     * @return
     */
    public static int[] getLine(int height, int width) {
        int[] tempCheckNum = {0, 0, 0, 0, 0};
        for (int i = 0; i < 4; i += 2) {
            tempCheckNum[i] = (int) (Math.random() * width);
            tempCheckNum[i + 1] = (int) (Math.random() * height);
        }
        return tempCheckNum;
    }

    /**
     * 获得干扰点的位置
     *
     * @param height
     * @param width
     * @return
     */
    public static int[] getPoint(int height, int width) {
        int[] tempCheckNum = {0, 0, 0, 0, 0};
        tempCheckNum[0] = (int) (Math.random() * width);
        tempCheckNum[1] = (int) (Math.random() * height);
        return tempCheckNum;
    }
}
