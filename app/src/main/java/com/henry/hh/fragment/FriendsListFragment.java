package com.henry.hh.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.henry.hh.R;
import com.henry.hh.activity.ChatActivity;
import com.henry.hh.adapter.FriendAdapter;
import com.henry.hh.constants.Condtsnts_URL;
import com.henry.hh.entity.Friend;
import com.henry.hh.entity.RequestMsg;
import com.henry.hh.entity.User;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.DividerItemDecoration;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.utils.LogUtils;
import com.henry.library.utils.ToastUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
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
public class FriendsListFragment extends MyBaseFragment
        implements BGARefreshLayout.BGARefreshLayoutDelegate, OnRecyclerItemClickListener {

    public static final String UID = "friendUid";

    private RecyclerView mRecyclerView;
    private FriendAdapter friendAdapter;
    private LinearLayoutManager mLayoutManager;
    private BGARefreshLayout mRefreshLayout;
    private List<Friend> friends;

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

    }

    @Override
    public void onResume() {
        super.onResume();
        friends = getMyApplication().getFriends();
        LogUtils.d(TAG, "friends=>" + friends.toString());
        friendAdapter.refresh(friends);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            friends = getMyApplication().getFriends();
            LogUtils.d(TAG, "friends=>" + friends.toString());
            friendAdapter.refresh(friends);
        }
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


    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout refreshLayout) {
        queryFriendList();
        mRefreshLayout.endRefreshing();

    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout refreshLayout) {
        mRefreshLayout.endLoadingMore();
        return false;

    }

    @Override
    public void onItemClick(View view, List data, int position) {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(UID, friends.get(position));
        intent.putExtras(bundle);
        startActivity(intent);
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
                getlistFromJson(result);
                mRefreshLayout.endRefreshing();
                friendAdapter.refresh(friends);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                LogUtils.d(TAG, "queryFriendList fail...");
            }
        });
    }

    /**
     * 解析出好友列表
     *
     * @param result
     */
    private void getlistFromJson(String result) {
        if (result != null) {
            friends = new ArrayList<Friend>();
            try {
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("datas");
                int length = jsonArray.length();
                if (length != 0) {
                    for (int j = 0; j < length; j++) {
                        JSONObject friendObject = jsonArray.getJSONObject(j);
                        LogUtils.d(TAG, "friendObject=" + friendObject.toString());
                        Friend friend = gson.fromJson(friendObject.toString(), new TypeToken<Friend>() {
                        }.getType());
                        friends.add(friend);
                        //设置为全局变量
                        getMyApplication().setFriends(friends);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
