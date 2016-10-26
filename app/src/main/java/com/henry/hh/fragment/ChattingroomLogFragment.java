package com.henry.hh.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.adapter.ChatOrFriendsPaperAdapter;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * 聊天室，用于展示聊天列表和好友列表
 */
public class ChattingroomLogFragment extends BaseFragment implements
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
        adapter = new ChatOrFriendsPaperAdapter(getFragmentManager(), list);
        viewPager.setAdapter(adapter);
    }

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
                ToastUtils.showShort(getActivity(),"收索");
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
