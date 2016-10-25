package com.henry.hh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Date: 2016/10/25. 10:43
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 聊天页及好友页面的ViewPaper适配器
 */
public class ChatOrFriendsPaperAdapter extends FragmentPagerAdapter {
    private List<Fragment> list;

    public ChatOrFriendsPaperAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
}
