package com.henry.hh.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.henry.hh.R;
import com.henry.hh.adapter.ChatAdapter;
import com.henry.hh.entity.Message;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends TitleActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        showBackwardView("返回", true);
        setTitle("IMU");

        recyclerView = getViewById(R.id.chat_recyclerview);
        initList();
        chatAdapter.refresh(getDatas(10));
    }

    /**
     * 初始化列表
     *
     * @return
     */
    private void initList() {

        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);

        //创建并设置Adapter
        chatAdapter = new ChatAdapter(mContext);

        recyclerView.setAdapter(chatAdapter);
    }

    private List<Message> getDatas(int num) {
        List<Message> mList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Message message = new Message();
            message.setTimeMillis(TimeUtils.getSysCurrentMillis() + i * 1000000);
            message.setType(Message.MSG_TYPE_TEXT);
            message.setContent("content:" + i);
            if (i % 3 == 0)
                message.setState(Message.MSG_STATE_FAIL);
            else if (i % 3 == 1)
                message.setState(Message.MSG_STATE_SUCCESS);
            else
                message.setState(Message.MSG_STATE_SENDING);
            mList.add(message);
        }

        return mList;
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }
}
