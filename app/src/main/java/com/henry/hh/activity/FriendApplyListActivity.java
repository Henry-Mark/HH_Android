package com.henry.hh.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.henry.hh.R;
import com.henry.hh.adapter.FriendApplyAdapter;
import com.henry.hh.entity.Message;
import com.henry.hh.entity.base.BaseSendMsg;
import com.henry.library.utils.LogUtils;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.List;

public class FriendApplyListActivity extends MyBaseActivity implements FriendApplyAdapter.OnAgreeClickListener {

    private RecyclerView mRecycler;
    private LinearLayoutManager mLayoutManager;
    private FriendApplyAdapter applyAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_apply_list);
        setTitle(R.string.string_friend_new);
        showBackwardView(R.string.back, true);
        initRecycler();
    }

    /**
     * 初始化Recycle
     */
    private void initRecycler() {
        mRecycler = getViewById(R.id.friendapply_recycler);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecycler.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecycler.setHasFixedSize(true);
        //创建并设置Adapter
        applyAdapter = new FriendApplyAdapter(mContext);
        mRecycler.setAdapter(applyAdapter);
        applyAdapter.addAgreeMentClickListener(this);
    }


    private List<Message> getMsg() {
        //降序查找消息列表
        List<Message> messages = liteOrm.<Message>query(new QueryBuilder<Message>(Message.class)
                .appendOrderDescBy("SendTimeMillis")
                .where("type=? and toUserId=?", BaseSendMsg.ADDFRIEND, user.getUserId()));
        LogUtils.d(TAG, "list>>> " + messages.toString());

        return messages;
    }

    @Override
    protected void onResume() {
        super.onResume();

        applyAdapter.refresh(getMsg());
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    @Override
    public void onAgree(List data, int position) {
//        showToast(data + ">>" + position);
    }

    @Override
    public void onRefuse(List data, int position) {
//        showToast(data + ">>" + position);
    }
}
