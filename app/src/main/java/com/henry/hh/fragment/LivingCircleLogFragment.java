package com.henry.hh.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.henry.hh.R;
import com.henry.hh.activity.PublishActivity;
import com.henry.hh.adapter.LivingCircleAdapter;
import com.henry.hh.entity.LivingCircleDynamic;
import com.henry.hh.interfaces.OnLivingDynamicItemClickListener;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.utils.TimeUtils;
import com.henry.library.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 * 生活圈，主要用于展示生活动态
 */
public class LivingCircleLogFragment extends BaseFragment
        implements BGARefreshLayout.BGARefreshLayoutDelegate,
        OnLivingDynamicItemClickListener, View.OnClickListener {

    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private LivingCircleAdapter circleAdapter;
    private BGARefreshLayout mRefreshLayout;
    private ImageButton mAdd;
    public int CODE = 100;

    public LivingCircleLogFragment() {
        // Required empty public constructor
    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_living_circle);
        bindView();
        initList();
        initRefresh();
        circleAdapter.refresh(getDatas(3));

    }

    private void bindView() {
        mRefreshLayout = getViewById(R.id.refreshLayout);
        mAdd = getViewById(R.id.ib_add);
        mAdd.setOnClickListener(this);
    }

    /**
     * 初始化列表
     */
    private void initList() {
        mRecyclerView = getViewById(R.id.recycler_livingcircle);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        //创建并设置Adapter
        circleAdapter = new LivingCircleAdapter(getActivity());
        mRecyclerView.setAdapter(circleAdapter);
        circleAdapter.addDynamicClickListener(this);
    }

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setIsShowLoadingMoreView(true);
        mRefreshLayout.setRefreshViewHolder(
                new BGANormalRefreshViewHolder(getActivity().getApplicationContext(), true));
    }

    private List<LivingCircleDynamic> getDatas(int num) {
        List<LivingCircleDynamic> mList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            LivingCircleDynamic dynamic = new LivingCircleDynamic();
            dynamic.setRemarkName("name" + i);
            dynamic.setDeliveryTimeMillis(TimeUtils.getSysCurrentMillis() - 10001 * 10000);
            dynamic.setContent("内容" + i);
            dynamic.setBackType(LivingCircleDynamic.BACK_TYPE_COLOR);
            dynamic.setPraise_count(1);
            mList.add(dynamic);
        }

        return mList;
    }

    /**
     * 下拉刷新
     *
     * @param refreshLayout
     */
    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.endRefreshing();
            }
        }, 3000);

    }

    /**
     * 上拉加载
     *
     * @param refreshLayout
     * @return
     */
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

    /**
     * 点击头像
     *
     * @param data
     * @param position
     */
    @Override
    public void onAvatarClick(List data, int position) {
        ToastUtils.showShort(getActivity(), "avatar" + position);
    }

    /**
     * 点击用户名
     *
     * @param data
     * @param position
     */
    @Override
    public void onUserClick(List data, int position) {
        ToastUtils.showShort(getActivity(), "onUserClick" + position);
    }

    /**
     * 点击动态内容
     *
     * @param data
     * @param position
     */
    @Override
    public void onContentClick(List data, int position) {
        ToastUtils.showShort(getActivity(), "onContentClick" + position);
    }

    /**
     * 点击聊天
     *
     * @param data
     * @param position
     */
    @Override
    public void onContactClick(List data, int position) {
        ToastUtils.showShort(getActivity(), "onContactClick" + position);
    }

    /**
     * 点击评论
     *
     * @param data
     * @param position
     */
    @Override
    public void onCommentClick(List data, int position) {
        ToastUtils.showShort(getActivity(), "onCommentClick" + position);
    }

    /**
     * 点击赞
     *
     * @param data
     * @param position
     */
    @Override
    public void onPraiseClick(List data, int position) {
        ToastUtils.showShort(getActivity(), "onPraiseClick" + position);
    }

    /**
     * 点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_add:
                Intent intent = new Intent(getActivity(), PublishActivity.class);
                startActivityForResult(intent, CODE);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
                LivingCircleDynamic result = (LivingCircleDynamic) data.getSerializableExtra(PublishActivity.BEAN);
                Log.d(TAG, "bean:" + result.getContent());
                circleAdapter.append(0, result);
                mRecyclerView.scrollToPosition(0);
            }
        }


    }
}
