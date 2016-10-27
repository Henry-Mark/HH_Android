package com.henry.hh.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.adapter.LivingCircleAdapter;
import com.henry.hh.entity.Friend;
import com.henry.hh.entity.LivingCircleDynamic;
import com.henry.library.View.CircleTextImageView;
import com.henry.library.View.DividerItemDecoration;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.fragment.BaseLogFragment;
import com.henry.library.utils.LogUtils;
import com.henry.library.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 生活圈，主要用于展示生活动态
 */
public class LivingCircleLogFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private LivingCircleAdapter circleAdapter;

    public LivingCircleLogFragment() {
        // Required empty public constructor
    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_living_circle);
        initList();
        circleAdapter.refresh(getDatas(3));
//        CircleTextImageView text = getViewById(R.id.text);
//        TextView time = getViewById(R.id.tv_time);
//        text.setTextColor(Color.WHITE);
//
//
//        LogUtils.d("TAG","时间："+System.currentTimeMillis());
//        // 返回相对于当前时间的一个时间字符串：在同一天显示时分；在不同一天，显示月日；在不同一年，显示年月日
//        CharSequence date2 = TimeUtils.getRelativeTime(
//                TimeUtils.getSysCurrentMillis()-10001 * 10000);
//        time.setText(date2);
    }

    private void initList(){
        mRecyclerView = getViewById(R.id.recycler_livingcircle);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        circleAdapter = new LivingCircleAdapter(getActivity());
        mRecyclerView.setAdapter(circleAdapter);
    }

    private List<LivingCircleDynamic> getDatas(int num) {
        List<LivingCircleDynamic> mList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            LivingCircleDynamic dynamic = new LivingCircleDynamic();
            dynamic.setRemarkName("name"+i);
            dynamic.setDeliveryTimeMillis(TimeUtils.getSysCurrentMillis()-10001 * 10000);
            dynamic.setContent("内容"+i);
            dynamic.setBackType(LivingCircleDynamic.BACK_TYPE_COLOR);
            dynamic.setPraise_count(1);
            mList.add(dynamic);
        }

        return mList;
    }
}
