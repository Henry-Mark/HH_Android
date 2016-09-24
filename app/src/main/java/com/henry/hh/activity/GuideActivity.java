package com.henry.hh.activity;

import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.henry.hh.R;

public class GuideActivity extends AppCompatActivity {
//
//    private ViewPager mViewPager;
//    private PagerAdapter mAdapter;
//    private List<view> mViews = new ArrayList<view>();
//    // TAB
//
//    private LinearLayout mTabWeixin;
//    private LinearLayout mTabFrd;
//    private LinearLayout mTabAddress;
//    private LinearLayout mTabSetting;
//
//    private Button mEnterButton;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_guide);
//    }
//
//
//    private void initEvents()
//    {
//
//
//        mViewPager.setOnPageChangeListener(new OnPageChangeListener()
//        {
//
//            @Override
//            public void onPageSelected(int arg0)
//            {
//
//
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2)
//            {
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0)
//            {
//
//            }
//        });
//    }
//
//    private void initView()
//    {
//        mViewPager = (ViewPager) findViewById(R.id.id_viewpager);
//
//
//        LayoutInflater mInflater = LayoutInflater.from(this);
//        View tab01 = mInflater.inflate(R.layout.tab01, null);
//        View tab02 = mInflater.inflate(R.layout.tab02, null);
//        View tab03 = mInflater.inflate(R.layout.tab03, null);
//        View tab04 = mInflater.inflate(R.layout.tab04, null);
//        mViews.add(tab01);
//        mViews.add(tab02);
//        mViews.add(tab03);
//        mViews.add(tab04);
//        mEnterButton=(Button)tab04.findViewById(R.id.imgbtn_enter);
//        mEnterButton.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(GuideActivity.this,MainActivity.class);
//                startActivity(intent);
//
//            }
//        });
//
//        mAdapter = new PagerAdapter()
//        {
//
//            @Override
//            public void destroyItem(ViewGroup container, int position,
//                                    Object object)
//            {
//                container.removeView(mViews.get(position));
//            }
//
//            @Override
//            public Object instantiateItem(ViewGroup container, int position)
//            {
//                View view = mViews.get(position);
//                container.addView(view);
//                return view;
//            }
//
//            @Override
//            public boolean isViewFromObject(View arg0, Object arg1)
//            {
//                return arg0 == arg1;
//            }
//
//            @Override
//            public int getCount()
//            {
//                return mViews.size();
//            }
//        };
//
//        mViewPager.setAdapter(mAdapter);
//
//    }
//
//    @Override
//    public void onClick(View v) {
//
//    }

}
