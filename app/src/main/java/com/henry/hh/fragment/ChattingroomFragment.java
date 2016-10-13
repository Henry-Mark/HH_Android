package com.henry.hh.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.henry.hh.R;
import com.henry.hh.activity.ChatActivity;
import com.henry.hh.adapter.ChattingRoomAdapter;
import com.henry.hh.entity.ChattingRoom;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.DividerItemDecoration;
import com.henry.library.utils.TimeUtils;
import com.henry.library.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 * 聊天室，用于展示聊天列表
 */
public class ChattingroomFragment extends Fragment implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private RecyclerView recyclerView;
    private ChattingRoomAdapter roomAdapter;
    private LinearLayoutManager mLayoutManager;

    private BGARefreshLayout mRefreshLayout;

    public ChattingroomFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chattingroom, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_chatting);
        mRefreshLayout = (BGARefreshLayout) view.findViewById(R.id.refreshLayout);
        initRefresh();
        initList();
        roomAdapter.refresh(getDatas(5));

        roomAdapter.setOnItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, List data, int position) {
                Toast.makeText(getActivity(), "...." + position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), ChatActivity.class));
            }
        });

        return view;

    }

    /**
     * 初始化列表
     *
     * @return
     */
    private void initList() {

        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        //创建并设置Adapter
        roomAdapter = new ChattingRoomAdapter(getActivity());

        recyclerView.setAdapter(roomAdapter);
    }

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setIsShowLoadingMoreView(true);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity().getApplicationContext(), true));
    }


    private List<ChattingRoom> getDatas(int num) {
        List<ChattingRoom> mList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            ChattingRoom room = new ChattingRoom();
            room.setAmountUnread(i + 7);
            room.setUserId("id" + i);
            room.setContent("content" + i);
            room.setMessageTime(TimeUtils.getSysCurrentMillis() - i * 1000000);
            mList.add(room);
        }

        return mList;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
//                ToastUtils.showShort(getActivity(),"没有最新数据了");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mRefreshLayout.endRefreshing();
                    }
                });

                cancel();
            }
        }, 3000, 1000);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
//        mMorePageNumber++;
//        if (mMorePageNumber > 4) {
//            mRefreshLayout.endLoadingMore();
//            ToastUtils.showShort(getActivity(),"没有更多数据了");
//            return false;
//        }
//        mEngine.loadMoreData(mMorePageNumber).enqueue(new Callback<List<RefreshModel>>() {
//            @Override
//            public void onResponse(Call<List<RefreshModel>> call, Response<List<RefreshModel>> response) {
//                mRefreshLayout.endLoadingMore();
//                mAdapter.addMoreData(response.body());
//            }
//
//            @Override
//            public void onFailure(Call<List<RefreshModel>> call, Throwable t) {
//                mRefreshLayout.endLoadingMore();
//            }
//        });

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
//                ToastUtils.showShort(getActivity(),"没有最新数据了");
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        roomAdapter.refresh(getDatas(10));
                        ToastUtils.showShort(getActivity(), "没有最新数据了");
                        mRefreshLayout.endLoadingMore();
                        cancel();
                    }
                });

            }
        }, 2000, 1000);
        return true;
    }
}
