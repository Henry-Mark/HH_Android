package com.henry.hh.fragment;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.henry.hh.R;
import com.henry.hh.activity.SearchActivity;
import com.henry.hh.adapter.ChatOrFriendsPaperAdapter;
import com.henry.hh.constants.Condtsnts_URL;
import com.henry.hh.entity.Friend;
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

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 聊天室，用于展示聊天列表和好友列表
 */
public class ChattingroomLogFragment extends MyBaseFragment implements
        View.OnClickListener, ViewPager.OnPageChangeListener {
    private final static int PAPER_MSG = 0;
    private final static int PAPER_FRIENDS = 1;

    private TextView mMsg;
    private TextView mFriends;
    private ViewPager viewPager;
    private List<Fragment> list;
    private ChatOrFriendsPaperAdapter adapter;
    private ImageView mSearch;

    public ChattingroomLogFragment() {
        // Required empty public constructor
    }


    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_chattingroom);
        bindView();
//        queryFriendList();
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    /**
     * 绑定控件
     */
    private void bindView() {
        mMsg = getViewById(R.id.tv_msg_main);
        mFriends = getViewById(R.id.tv_friends_main);
        viewPager = getViewById(R.id.viewPaper);
        mSearch = getViewById(R.id.iv_search);
        mMsg.setOnClickListener(this);
        mFriends.setOnClickListener(this);
        mSearch.setOnClickListener(this);
        viewPager.addOnPageChangeListener(this);
        setTabStrip(PAPER_MSG);
    }

    /**
     * 设置标题上对应的页面选项
     *
     * @param index
     */
    private void setTabStrip(int index) {
        switch (index) {
            case PAPER_MSG:
                mMsg.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorMainStyle));
                mMsg.setBackgroundResource(R.drawable.background_choose_title_chat_pressed);
                mFriends.setTextColor(Color.WHITE);
                mFriends.setBackgroundResource(R.drawable.background_choose_title_friends_normal);
                break;
            case PAPER_FRIENDS:
                mMsg.setTextColor(Color.WHITE);
                mMsg.setBackgroundResource(R.drawable.background_choose_title_chat_normal);
                mFriends.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorMainStyle));
                mFriends.setBackgroundResource(R.drawable.background_choose_title_friends_pressed);
                break;
            default:
                break;
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        list = new ArrayList<>();
        list.add(new MsgListFragment());
        list.add(new FriendsListFragment());
        adapter = new ChatOrFriendsPaperAdapter(getChildFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }

//    /**
//     * 获取好友列表
//     */
//    private void queryFriendList() {
//        AsyncHttpClient client = new AsyncHttpClient();
//        RequestParams params = new RequestParams();
//        params.put("id", getMyApplication().getUser().getUserId());
//        client.post(Condtsnts_URL.FRIENDLIST, params, new AsyncHttpResponseHandler() {
//            @Override
//            public void onSuccess(int i, Header[] headers, byte[] bytes) {
//                String result = new String(bytes);
//                LogUtils.d(TAG, "friends result=");
//                //解析json
//                getlistFromJson(result);
//            }
//
//            @Override
//            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
//                LogUtils.d(TAG, "queryFriendList fail...");
//            }
//        });
//    }
//
//    /**
//     * 解析出好友列表
//     *
//     * @param result
//     */
//    private void getlistFromJson(String result) {
//        List<Friend> friends;
//        if (result != null) {
//            friends = new ArrayList<Friend>();
//            try {
//                JSONObject jsonObject = new JSONObject(result);
//                JSONArray jsonArray = jsonObject.getJSONArray("datas");
//                int length = jsonArray.length();
//                if (length != 0) {
//                    for (int j = 0; j < length; j++) {
//                        JSONObject friendObject = jsonArray.getJSONObject(j);
//                        LogUtils.d(TAG, "friendObject=" + friendObject.toString());
//                        Friend friend = gson.fromJson(friendObject.toString(), new TypeToken<Friend>() {
//                        }.getType());
//                        friends.add(friend);
//                        //设置为全局变量
//                        getMyApplication().setFriends(friends);
//                    }
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_msg_main:
                viewPager.setCurrentItem(PAPER_MSG);
                setTabStrip(PAPER_MSG);
                break;
            case R.id.tv_friends_main:
                viewPager.setCurrentItem(PAPER_FRIENDS);
                setTabStrip(PAPER_FRIENDS);
                break;
            case R.id.iv_search:
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        setTabStrip(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
