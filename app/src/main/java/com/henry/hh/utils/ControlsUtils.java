package com.henry.hh.utils;

import android.view.View;
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
     * @param view 控件
     * @param width 宽度，单位：像素
     */
    public static void setWidth(View view, int width){
        // 取控件mGrid当前的布局参数
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        linearParams.weight = width;
        view.setLayoutParams(linearParams);

    }

    /**
     * 设置控件高度
     * @param view 控件
     * @param height 高度，单位：像素
     */
    public static void setHeight(View view,int height){
        // 取控件mGrid当前的布局参数
        LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) view.getLayoutParams();
        linearParams.height = height;
        view.setLayoutParams(linearParams);
    }


}
