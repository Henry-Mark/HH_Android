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
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;

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
    protected void onPause() {
        super.onPause();
        updateApplyReadState();
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    @Override
    public void onAgree(List data, int position) {
        Message message = (Message) data.get(position);
        doApplyConform(message, BaseSendMsg.APPLY_AGREE);
    }

    @Override
    public void onRefuse(List data, int position) {
        Message message = (Message) data.get(position);
        doApplyConform(message, BaseSendMsg.APPLY_DISAGREE);
    }

    @Override
    protected void onReceive(Message message) {
        super.onReceive(message);
        if (message.getType().equals(BaseSendMsg.ADDFRIEND_CONFORM_BACK)) {
            cancelProgressDialog();
            if (message.getContent().equals(BaseSendMsg.APPLY_DISAGREE)) {
                updateApplyState(Message.MSG_STATE_ALREADY_REFUSE, message.getUid());
            } else if (message.getContent().equals(BaseSendMsg.APPLY_AGREE)) {
                updateApplyState(Message.MSG_STATE_ALREADY_ARGEE, message.getUid());
            } else {
                LogUtils.d(TAG, "conform fail..");
            }
        } else if (message.getType().equals(BaseSendMsg.ADDFRIEND)) {
            applyAdapter.refresh(getMsg());
        }
    }

    /**
     * 更新数据库状态 好友申请状态
     *
     * @param state
     * @param uid
     */
    private void updateApplyState(int state, long uid) {
        liteOrm.update(new WhereBuilder(Message.class).
                        where("type=? and toUserId=? and uid=? ", BaseSendMsg.ADDFRIEND, user.getUserId(), uid),
                new ColumnsValue(new String[]{"state"}, new Object[]{state}),
                ConflictAlgorithm.Fail);
        applyAdapter.refresh(getMsg());
    }

    /**
     * 更新申请信息均为已读
     */
    private void updateApplyReadState() {
        liteOrm.update(new WhereBuilder(Message.class).
                        where("type=? and toUserId=? ", BaseSendMsg.ADDFRIEND, user.getUserId()),
                new ColumnsValue(new String[]{"isRead"}, new Object[]{1}),
                ConflictAlgorithm.Fail);
    }

    /**
     * 申请确认
     *
     * @param msg
     * @param content
     */
    private void doApplyConform(Message msg, String content) {
        BaseSendMsg baseSendMsg = new BaseSendMsg(BaseSendMsg.ADDFRIEND_CONFORM,
                content, msg.getUid(), System.currentTimeMillis());
        sendChatMsg(gson.toJson(baseSendMsg));
        showProgressDialog(R.string.commiting_result);
    }
}
