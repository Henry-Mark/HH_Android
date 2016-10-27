package com.henry.hh.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.henry.hh.R;
import com.henry.hh.adapter.LivingCircleAdapter;
import com.henry.hh.entity.LivingCircleDynamic;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.utils.TimeUtils;
import com.henry.library.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 * 生活圈，主要用于展示生活动态
 */
public class LivingCircleLogFragment extends BaseFragment
        implements BGARefreshLayout.BGARefreshLayoutDelegate{

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private LivingCircleAdapter circleAdapter;
    private BGARefreshLayout mRefreshLayout;

    public LivingCircleLogFragment() {
        // Required empty public constructor
    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_living_circle);
        initList();
        initRefresh();
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

    /**
     * 初始化列表
     */
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

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        mRefreshLayout = getViewById(R.id.refreshLayout);
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setIsShowLoadingMoreView(true);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity().getApplicationContext(), true));
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
                circleAdapter.refresh(getDatas(10));
                ToastUtils.showShort(getActivity(), "没有最新数据了");
                mRefreshLayout.endLoadingMore();
            }
        }, 2000);
        return true;
    }
}
