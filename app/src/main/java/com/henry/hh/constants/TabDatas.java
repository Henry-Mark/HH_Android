package com.henry.hh.constants;

import com.henry.hh.R;
import com.henry.hh.fragment.ChattingroomFragment;
import com.henry.hh.fragment.ExplorationParkFragment;
import com.henry.hh.fragment.LivingCircleFragment;

/**
 * Date: 16-10-09 下午4:39
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 保存Tab相关数据
 */
public class TabDatas {
    /**
     * 获取tab导航栏文字数组
     *
     * @return
     */
    public static String[] getTabsTxt() {
        String[] tabs = {"聊天室", "生活圈", "探索园"};
        return tabs;
    }

    /**
     * 获取tab导航栏按下时图片数组
     *
     * @return
     */
    public static int[] getTabsImg() {
        int[] ids = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        return ids;
    }

    /**
     * 获取tab导航栏未按下时图片数组
     *
     * @return
     */
    public static int[] getTabsImgLight() {
        int[] ids = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        return ids;
    }

    /**
     * 获取对应的fragment数组
     *
     * @return
     */
    public static Class[] getFragments() {
        Class[] clz = {ChattingroomFragment.class, LivingCircleFragment.class, ExplorationParkFragment.class};
        return clz;
    }
}
