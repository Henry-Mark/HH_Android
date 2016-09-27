package com.henry.hh.utils;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Date: 16-9-26 下午11:51
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 设置控件的宽高
 */
public class ControlsUtils {


    /**
     * 设置控件宽度
     * @param view      控件
     * @param width     宽度，单位：像素
     */
    public static void setWidth(View view, int width ){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        view.setLayoutParams(params);
    }

    /**
     * 设置控件高度
     * @param view      控件
     * @param height    高度，单位：像素
     */
    public static void setHeight(View view ,int height){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
        view.setLayoutParams(params);
    }

    /**
     * 设置控件大小
     * @param view  控件
     * @param width 宽度，单位：像素
     * @param height 高度，单位：像素
     */
    public static void setSize(View view,int width,int height){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = width;
        params.height = height;
        view.setLayoutParams(params);
    }


}
