package com.henry.hh.activity;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.constants.Constants;
import com.henry.hh.entity.Message;
import com.henry.hh.fragment.ExplorationParkLogFragment;
import com.henry.hh.fragment.FriendsListFragment;
import com.henry.hh.fragment.LivingCircleLogFragment;
import com.henry.hh.fragment.MsgListFragment;
import com.henry.hh.fragment.MyHomeLogFragment;
import com.litesuits.orm.db.model.ConflictAlgorithm;

public class MainActivity extends MyBaseActivity implements View.OnClickListener {

    //存放Fragment
    private FrameLayout mFragment;
    //聊天室、生活圈、探索园、我的家 TextView控件
    private TextView mTvChatting, mTvLivingCicle, mTvExploration, mTvMyhome;
    //聊天室、生活圈、探索园、我的家 ImageView控件
    private ImageView mIvChatting, mIvLivingCicle, mIvExploration, mIvMyhome;
    //聊天室、生活圈、探索园、我的家 RelativeLyout控件
    private RelativeLayout mRlChatting, mRlLivingCicle, mRlExploration, mRlMyhome;
    //标题栏布局
    private RelativeLayout mRlTitle;
    //收索图标
    private ImageView mSearch;
    //对应到消息列表、朋友列表布局
    private TextView mMsg, mFriend;
    //当前索引
    private int TabIndex = -1;
    //聊天室索引
    private int TabChating = Constants.TabIndex.MESSAGE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideTitle();
        initView();
        switchTab(TabChating);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //聊天室
            case R.id.rl_footer_chatting:
                //切换Tab，及Fragment显示
                switchTab(TabChating);
                break;
            //生活圈
            case R.id.rl_footer_livingcicle:
                //切换Tab，及Fragment显示
                switchTab(Constants.TabIndex.LIVINGCIRCLE);
                break;
            //探索园
            case R.id.rl_footer_explorationpark:
                //切换Tab，及Fragment显示
                switchTab(Constants.TabIndex.EXPLORATION);
                break;
            //我的家
            case R.id.rl_footer_myhome:
                //切换Tab，及Fragment显示
                switchTab(Constants.TabIndex.MYHOME);
                break;
            //收索
            case R.id.iv_search:
                startActivity(SearchActivity.class);
                break;
            //切换到消息列表
            case R.id.tv_msg_main:
                TabChating = Constants.TabIndex.MESSAGE;
                //切换Tab，及Fragment显示
                switchTab(TabChating);
                break;
            //切换到好友列表
            case R.id.tv_friends_main:
                TabChating = Constants.TabIndex.FRIEND;
                //切换Tab，及Fragment显示
                switchTab(TabChating);
                break;
            default:
                break;
        }
        super.onClick(v);
    }

    /**
     * 初始化控件
     */
    private void initView() {

        mIvChatting = getViewById(R.id.img_footer_chatting);
        mIvLivingCicle = getViewById(R.id.img_footer_livingcicle);
        mIvExploration = getViewById(R.id.img_footer_explorationpark);
        mIvMyhome = getViewById(R.id.img_footer_myhome);
        mTvChatting = getViewById(R.id.title_footer_chatting);
        mTvLivingCicle = getViewById(R.id.title_footer_livingcicle);
        mTvExploration = getViewById(R.id.title_footer_explorationpark);
        mTvMyhome = getViewById(R.id.title_footer_myhome);
        mRlChatting = getViewById(R.id.rl_footer_chatting);
        mRlChatting.setOnClickListener(this);
        mRlLivingCicle = getViewById(R.id.rl_footer_livingcicle);
        mRlLivingCicle.setOnClickListener(this);
        mRlExploration = getViewById(R.id.rl_footer_explorationpark);
        mRlExploration.setOnClickListener(this);
        mRlMyhome = getViewById(R.id.rl_footer_myhome);
        mRlMyhome.setOnClickListener(this);
        mRlTitle = getViewById(R.id.titlelayout);
        mSearch = getViewById(R.id.iv_search);
        mSearch.setOnClickListener(this);
        mFriend = getViewById(R.id.tv_friends_main);
        mFriend.setOnClickListener(this);
        mMsg = getViewById(R.id.tv_msg_main);
        mMsg.setOnClickListener(this);
    }

    /**
     * 获取缓存的Fragment
     *
     * @param tag 标签
     * @return
     */
    private Fragment getFragment(int tag) {
        return getSupportFragmentManager().findFragmentByTag(String.valueOf(tag));
    }

    /**
     * 显示新的界面（Fragment），隐藏旧的界面（Fragment）
     *
     * @param hideTag  隐藏界面的标签
     * @param showTag  显示界面的标签
     * @param fragment 显示的界面
     * @param resId    控件ID
     * @param <T>
     */
    private <T extends Fragment> void showFragment(int hideTag, int showTag, T fragment, int resId) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment hideFragment = getFragment(hideTag);
        if (hideFragment != null) {
            ft.hide(hideFragment);
        }
        Fragment showFragment = getFragment(showTag);
        if (showFragment == null) {
            ft.add(resId, fragment, String.valueOf(showTag));
        } else {
            ft.show(showFragment);
        }
        ft.commitAllowingStateLoss();
    }


    /**
     * 设置Fragment参数
     *
     * @param fragment
     * @param bundle
     */
    private void setFragmentArgument(Fragment fragment, Bundle bundle) {
        if (bundle != null) {
            if (fragment.getArguments() != null) {
                fragment.getArguments().putAll(bundle);
            } else {
                fragment.setArguments(bundle);
            }
        }
    }

    /**
     * 切换Tab
     *
     * @param index Tab索引
     */
    public void switchTab(int index) {
        switchTab(index, null);
    }

    /**
     * 切换Tab
     *
     * @param index  Tab索引
     * @param bundle 参数
     */
    public void switchTab(int index, Bundle bundle) {
        Fragment fragment = getFragment(index);
        if (fragment == null) {
            switch (index) {
                //消息列表
                case Constants.TabIndex.MESSAGE:
                    fragment = new MsgListFragment();
                    break;
                //消息列表
                case Constants.TabIndex.FRIEND:
                    fragment = new FriendsListFragment();
                    break;
                //消息列表
                case Constants.TabIndex.LIVINGCIRCLE:
                    fragment = new LivingCircleLogFragment();
                    break;
                //消息列表
                case Constants.TabIndex.EXPLORATION:
                    fragment = new ExplorationParkLogFragment();
                    break;
                //消息列表
                case Constants.TabIndex.MYHOME:
                    fragment = new MyHomeLogFragment();
                    break;
                default:
                    break;

            }
        }
        //设置参数
        setFragmentArgument(fragment, bundle);
        showFragment(TabIndex, index, fragment, R.id.main_fragment);

        setTitleView(index);
        setFooterView(index);
        TabIndex = index;
    }

    /**
     * 设置标题
     *
     * @param index
     */
    private void setTitleView(int index) {
        if (index == Constants.TabIndex.MESSAGE) {
            mRlTitle.setVisibility(View.VISIBLE);
            mMsg.setTextColor(ContextCompat.getColor(this, R.color.colorMainStyle));
            mMsg.setBackgroundResource(R.drawable.background_choose_title_chat_pressed);
            mFriend.setTextColor(Color.WHITE);
            mFriend.setBackgroundResource(R.drawable.background_choose_title_friends_normal);
        } else if (index == Constants.TabIndex.FRIEND) {
            mRlTitle.setVisibility(View.VISIBLE);
            mMsg.setTextColor(Color.WHITE);
            mMsg.setBackgroundResource(R.drawable.background_choose_title_chat_normal);
            mFriend.setTextColor(ContextCompat.getColor(this, R.color.colorMainStyle));
            mFriend.setBackgroundResource(R.drawable.background_choose_title_friends_pressed);
        } else {
            mRlTitle.setVisibility(View.GONE);
        }
    }

    /**
     * 设置导航栏字体颜色
     *
     * @param textView
     */
    private void setFooterColor(TextView textView) {
        mTvChatting.setTextColor(ContextCompat.getColor(this, R.color.colorTabTextNormal));
        mTvLivingCicle.setTextColor(ContextCompat.getColor(this, R.color.colorTabTextNormal));
        mTvExploration.setTextColor(ContextCompat.getColor(this, R.color.colorTabTextNormal));
        mTvMyhome.setTextColor(ContextCompat.getColor(this, R.color.colorTabTextNormal));
        textView.setTextColor(ContextCompat.getColor(this, R.color.colorMainStyle));
    }

    /**
     * 设置底部控件显示
     *
     * @param index
     */
    private void setFooterView(int index) {
        switch (index) {
            case Constants.TabIndex.MESSAGE:
            case Constants.TabIndex.FRIEND:
                setFooterColor(mTvChatting);
                break;
            case Constants.TabIndex.LIVINGCIRCLE:
                setFooterColor(mTvLivingCicle);
                break;
            case Constants.TabIndex.MYHOME:
                setFooterColor(mTvMyhome);
                break;
            case Constants.TabIndex.EXPLORATION:
                setFooterColor(mTvExploration);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onReceive(Message message) {
        super.onReceive(message);
        //保存到数据库
        liteOrm.insert(message, ConflictAlgorithm.Abort);
    }
}
