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
 * 聊天室，用于展示聊天列表
 */
public class ChatroomFragment extends Fragment {


    public ChatroomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_chatroom, container, false);

        return view;
    }

}
