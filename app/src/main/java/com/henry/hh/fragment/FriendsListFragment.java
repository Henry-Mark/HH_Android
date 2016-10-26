package com.henry.hh.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.henry.hh.R;
import com.henry.hh.adapter.FriendAdapter;
import com.henry.hh.entity.Friend;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.DividerItemDecoration;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 * 好友列表
 */
public class FriendsListFragment extends BaseFragment
        implements BGARefreshLayout.BGARefreshLayoutDelegate, OnRecyclerItemClickListener {

    private RecyclerView mRecyclerView;
    private FriendAdapter friendAdapter;
    private LinearLayoutManager mLayoutManager;
    private BGARefreshLayout mRefreshLayout;

    public FriendsListFragment() {
        // Required empty public constructor
    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_list_friend);
        mRecyclerView = getViewById(R.id.recycler_friend);
        mRefreshLayout = getViewById(R.id.refreshLayout);

        initList();
        initRefresh();
        friendAdapter.refresh(getDatas(5));

    }

    /**
     * 初始化列表
     *
     * @return
     */
    private void initList() {
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        //创建并设置Adapter
        friendAdapter = new FriendAdapter(getActivity());
        mRecyclerView.setAdapter(friendAdapter);
        friendAdapter.addOnItemClickListener(this);
    }

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setIsShowLoadingMoreView(true);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity().getApplicationContext(), true));
    }

    private List<Friend> getDatas(int num) {
        List<Friend> mList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Friend friend = new Friend();
            friend.setRemarkName("name" + i);
            friend.setLabel("label" + i);
            friend.setSignature("sigature" + i);
            mList.add(friend);
        }

        return mList;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
            }
        }, 3000);

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                friendAdapter.refresh(getDatas(10));
                ToastUtils.showShort(getActivity(), "没有最新数据了");
                mRefreshLayout.endLoadingMore();
            }
        }, 2000);
        return true;

    }

    @Override
    public void onItemClick(View view, List data, int position) {
        ToastUtils.showShort(getActivity(), position + "....");
    }
}
