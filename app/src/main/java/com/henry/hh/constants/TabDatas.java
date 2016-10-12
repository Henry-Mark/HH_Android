package com.henry.hh.constants;

import com.henry.hh.R;
import com.henry.hh.fragment.ChattingroomFragment;
import com.henry.hh.fragment.ExplorationParkFragment;
import com.henry.hh.fragment.LivingCircleFragment;
import com.henry.hh.fragment.MyHomeFragment;

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
    public static int[] getTabsRes() {
        int[] tabs = {R.string.tab_chattingroom, R.string.tab_explorations,
                R.string.tab_livingcircle, R.string.tab_myhome};
        return tabs;
    }

    /**
     * 获取tab导航栏按下时图片数组
     *
     * @return
     */
    public static int[] getTabsImg() {
        int[] ids = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        return ids;
    }

    /**
     * 获取tab导航栏未按下时图片数组
     *
     * @return
     */
    public static int[] getTabsImgLight() {
        int[] ids = {R.mipmap.ic_launcher, R.mipmap.ic_launcher,
                R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        return ids;
    }

    /**
     * 获取对应的fragment数组
     *
     * @return
     */
    public static Class[] getFragments() {
        Class[] clz = {ChattingroomFragment.class, LivingCircleFragment.class,
                ExplorationParkFragment.class, MyHomeFragment.class};
        return clz;
    }
}
