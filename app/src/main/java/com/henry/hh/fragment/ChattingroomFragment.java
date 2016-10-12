package com.henry.hh.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henry.hh.R;
import com.henry.hh.adapter.ChattingRoomAdapter;
import com.henry.hh.adapter.TestAdapter;
import com.henry.hh.dialog.PromptDialog;
import com.henry.hh.entity.ChattingRoom;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.DividerItemDecoration;
import com.henry.library.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 聊天室，用于展示聊天列表
 */
public class ChattingroomFragment extends Fragment {

    private RecyclerView recyclerView;
    private ChattingRoomAdapter roomAdapter;
    private LinearLayoutManager mLayoutManager;

    public ChattingroomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_chattingroom, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler_chatting);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        //创建并设置Adapter
        roomAdapter = new ChattingRoomAdapter(getActivity());

        recyclerView.setAdapter(roomAdapter);
        roomAdapter.refresh(getDatas());

        roomAdapter.setOnItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, List data, int position) {
//                Toast.makeText(getActivity(),"...."+position,Toast.LENGTH_SHORT).show();
//                PromptDialog promptDialog = new PromptDialog(getActivity(),"slfblabfl");
//                promptDialog.show(getActivity().getFragmentManager(),"promptDialog");
            }
        });
        return view;

    }

    private List<ChattingRoom> getDatas(){
        List<ChattingRoom> mList = new ArrayList<>();
        for (int i=0;i<5;i++){
            ChattingRoom room = new ChattingRoom();
            room.setAmountUnread(i+7);
            room.setUserId("id"+i);
            room.setContent("content"+i);
            room.setMessageTime(TimeUtils.getSysCurrentMillis()-i*1000000);
            mList.add(room);
        }

        return mList;
    }
}
