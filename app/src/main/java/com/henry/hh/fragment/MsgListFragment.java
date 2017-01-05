package com.henry.hh.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.henry.hh.R;
import com.henry.hh.activity.ChatActivity;
import com.henry.hh.adapter.MsgAdapter;
import com.henry.hh.constants.Condtsnts_URL;
import com.henry.hh.entity.Friend;
import com.henry.hh.entity.Message;
import com.henry.hh.entity.User;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.hh.utils.JsonUtils;
import com.henry.library.View.DividerItemDecoration;
import com.henry.library.utils.LogUtils;
import com.henry.library.utils.ToastUtils;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * A simple {@link Fragment} subclass.
 * 聊天消息列表
 */
public class MsgListFragment extends MyBaseFragment
        implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private RecyclerView recyclerView;
    private MsgAdapter msgAdapter;
    private LinearLayoutManager mLayoutManager;

    private BGARefreshLayout mRefreshLayout;

    private List<Friend> friends;

    private User user;

    public MsgListFragment() {
        // Required empty public constructor
    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_list_msg);
        user = getMyApplication().getUser();

        recyclerView = getViewById(R.id.recycler_chatting);
        mRefreshLayout = getViewById(R.id.refreshLayout);
        initRefresh();
        initList();
        msgAdapter.addOnItemClickListener(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, List data, int position) {
                Intent intent = new Intent(getActivity(), ChatActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(FriendsListFragment.UID, friends.get(position));
                intent.putExtras(bundle);
                startActivity(intent);
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
        msgAdapter = new MsgAdapter(getActivity());

        recyclerView.setAdapter(msgAdapter);
    }

    /**
     * 初始化刷新控件
     */
    private void initRefresh() {
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setIsShowLoadingMoreView(true);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getActivity().getApplicationContext(), true));
    }

    @Override
    public void onResume() {
        super.onResume();
        friends = getFriendFromOrm();
        if (friends != null && friends.size() != 0) {
            freshList(friends);
        } else {
            queryFriendList();
        }

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {

        }
    }

    /**
     * 获取好友列表
     */
    private void queryFriendList() {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("id", getMyApplication().getUser().getUserId());
        client.post(Condtsnts_URL.FRIENDLIST, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result = new String(bytes);
                LogUtils.d(TAG, "friends result=");
                //解析json
                friends = JsonUtils.getFriendlistFromJson(result);
                //保存到数据库中
                writeFriendToOrm(friends);
                freshList(friends);
                mRefreshLayout.endRefreshing();
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                LogUtils.d(TAG, "queryFriendList fail...");
            }
        });
    }

    /**
     * 刷新列表
     */
    private void freshList(List<Friend> friends) {
        List<Friend> friendList = new ArrayList<>();
        for (Friend friend : friends) {
            List<Message> messages = getMsg();
            //消息条数
            int count = 0;
            //未读消息条数
            int unReadcount = 0;
            for (Message message : messages) {
                if (message.getFromUserId() == friend.getFriendUid() || message.getToUserId() == friend.getFriendUid()) {
                    friend.setLastContent(message.getContent());
                    friend.setLastChatTimeMillis(message.getSendTimeMillis());
                    count++;
                    if (message.getIsRead() == 0) {
                        unReadcount++;
                    }
                }
            }
            if (count != 0) {
                friendList.add(friend);
            }
            friend.setAmountUnread(unReadcount);
        }
        msgAdapter.refresh(friendList);
    }


    /**
     * 获取数据库中的消息列表
     *
     * @return
     */
    private List<Message> getMsg() {
        //升序查找消息列表
        List<Message> messages = liteOrm.<Message>query(new QueryBuilder<Message>(Message.class)
                .appendOrderAscBy("SendTimeMillis")
                .where("type=? and fromUserId=? or toUserId=?", "chat", user.getUserId(), user.getUserId()));
//        LogUtils.d(TAG, "list>>> " + messages.toString());

        return messages;
    }


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        queryFriendList();
//        mRefreshLayout.endRefreshing();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ToastUtils.showShort(getActivity(), "没有最新数据了");
                mRefreshLayout.endLoadingMore();
            }
        }, 3000);
        return true;
    }

}
