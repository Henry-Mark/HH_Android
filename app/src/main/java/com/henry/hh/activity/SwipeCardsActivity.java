package com.henry.hh.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.henry.hh.R;
import com.henry.hh.adapter.JellyFragmentPagerAdapter;
import com.henry.hh.constants.Constants;
import com.henry.hh.entity.PeopleNearby;
import com.henry.hh.widget.JellyViewPager;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 可滑动卡片
 */
public class SwipeCardsActivity extends TitleActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {

    private List<PeopleNearby> peopleNearbies;
    private Button left, right;
    private JellyViewPager jellyViewPager;
    private JellyFragmentPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_cards);

        jellyViewPager = getViewById(R.id.jelly);
        left = getViewById(R.id.left);
        right = getViewById(R.id.right);
        left.setOnClickListener(this);
        right.setOnClickListener(this);

        peopleNearbies = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            PeopleNearby peopleNearby = new PeopleNearby();
            peopleNearby.setNameNearby("person" + i);
            peopleNearby.setZodiac(Constants.Zodiac.SAGITTARIUS);
            peopleNearby.setDistance(123 * i);
            peopleNearby.setAge(23);
            peopleNearbies.add(peopleNearby);
        }
        adapter = new JellyFragmentPagerAdapter(getSupportFragmentManager(),peopleNearbies);
        jellyViewPager.setAdapter(adapter);
        jellyViewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.left:
                jellyViewPager.showPre();
                break;
            case R.id.right:
                jellyViewPager.showNext();
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {
        switch(state){
            case 1: //正在滑动
                LogUtils.d(TAG,"on moving....");
                break;
            case 2: //滑动结束
                LogUtils.d(TAG,"move finish....");
                break;
        }
    }
}

