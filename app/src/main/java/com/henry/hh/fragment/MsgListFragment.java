package com.henry.hh.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.henry.hh.R;
import com.henry.hh.activity.ChatActivity;
import com.henry.hh.adapter.MsgAdapter;
import com.henry.hh.entity.Friend;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.DividerItemDecoration;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.utils.TimeUtils;
import com.henry.library.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 * 聊天消息列表
 */
public class MsgListFragment extends BaseFragment
        implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private RecyclerView recyclerView;
    private MsgAdapter roomAdapter;
    private LinearLayoutManager mLayoutManager;

    private BGARefreshLayout mRefreshLayout;

    public MsgListFragment() {
        // Required empty public constructor
    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_list_msg);
        recyclerView = getViewById(R.id.recycler_chatting);
        mRefreshLayout = getViewById(R.id.refreshLayout);
        initRefresh();
        initList();
        roomAdapter.refresh(getDatas(6));

        roomAdapter.addOnItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, List data, int position) {
                Toast.makeText(getActivity(), "...." + position, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getActivity(), ChatActivity.class));
            }
        });
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
        roomAdapter = new MsgAdapter(getActivity());

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


    private List<Friend> getDatas(int num) {
        List<Friend> mList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Friend room = new Friend();
            room.setAmountUnread(i + 7);
            room.setFriendUid(i);
            room.setLastContent("content" + i);
            room.setLastChatTimeMillis(TimeUtils.getSysCurrentMillis() - i * 1000000);
            mList.add(room);
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
        },3000);

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


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                roomAdapter.refresh(getDatas(10));
                ToastUtils.showShort(getActivity(), "没有最新数据了");
                mRefreshLayout.endLoadingMore();
            }
        },3000);
        return true;
    }

}
