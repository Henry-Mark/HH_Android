package com.henry.hh.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.library.View.CircleTextImageView;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.fragment.BaseLogFragment;
import com.henry.library.utils.LogUtils;
import com.henry.library.utils.TimeUtils;

/**
 * A simple {@link Fragment} subclass.
 * 生活圈，主要用于展示生活动态
 */
public class LivingCircleLogFragment extends BaseFragment {


    public LivingCircleLogFragment() {
        // Required empty public constructor
    }


//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view =inflater.inflate(R.layout.fragment_living_circle, container, false);
//        CircleTextImageView text = (CircleTextImageView)view.findViewById(R.id.text);
//        TextView time = (TextView)view.findViewById(R.id.tv_time);
//        text.setTextColor(Color.WHITE);
//
//
//        LogUtils.d("TAG","时间："+System.currentTimeMillis());
//        // 返回相对于当前时间的一个时间字符串：在同一天显示时分；在不同一天，显示月日；在不同一年，显示年月日
//        CharSequence date2 = TimeUtils.getRelativeTime(
//                 TimeUtils.getSysCurrentMillis()-10001 * 10000);
//        time.setText(date2);
//        return view;
//    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_living_circle);
        CircleTextImageView text = getViewById(R.id.text);
        TextView time = getViewById(R.id.tv_time);
        text.setTextColor(Color.WHITE);


        LogUtils.d("TAG","时间："+System.currentTimeMillis());
        // 返回相对于当前时间的一个时间字符串：在同一天显示时分；在不同一天，显示月日；在不同一年，显示年月日
        CharSequence date2 = TimeUtils.getRelativeTime(
                TimeUtils.getSysCurrentMillis()-10001 * 10000);
        time.setText(date2);
    }

}
