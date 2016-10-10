package com.henry.hh.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henry.hh.R;
import com.henry.library.View.CircleTextImageView;

/**
 * A simple {@link Fragment} subclass.
 * 生活圈，主要用于展示生活动态
 */
public class LivingCircleFragment extends Fragment {


    public LivingCircleFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_living_circle, container, false);
        CircleTextImageView text = (CircleTextImageView)view.findViewById(R.id.text);
        text.setTextColor(Color.WHITE);
        return view;
    }

}
