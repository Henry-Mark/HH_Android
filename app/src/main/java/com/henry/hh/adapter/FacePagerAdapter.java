package com.henry.hh.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.List;

/**
 * Date: 2016/10/17. 10:24
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: Emoji表情分类的显示适配器
 */
public class FacePagerAdapter extends PagerAdapter {

    private List<RecyclerView> mList;

    public FacePagerAdapter(List<RecyclerView> list) {
        mList = list;
    }

    @Override
    public int getCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager) container).removeView(mList.get(position));
    }

    @Override
    public Object instantiateItem(View arg0, int arg1) {
        ((ViewPager) arg0).addView(mList.get(arg1));
        return mList.get(arg1);
    }
}
