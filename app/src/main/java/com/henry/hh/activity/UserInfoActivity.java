package com.henry.hh.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.Friend;
import com.henry.hh.entity.Message;
import com.henry.hh.entity.User;
import com.henry.hh.entity.base.BaseSendMsg;
import com.henry.library.View.CircleImageView;
import com.henry.library.utils.LogUtils;

import java.util.List;

public class UserInfoActivity extends MyBaseActivity {

    private User user = null;

    //头像
    private CircleImageView mAvatar;
    //账号
    private TextView mAccount;
    //地区
    private TextView mRegion;
    //签名
    private TextView mSiagnature;
    //添加好友按钮
    private Button mAddFriends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        user = (User) getIntent().getSerializableExtra(SearchActivity.INFO);
        LogUtils.d(TAG, "user>>" + user.toString());
        initView();
        initData();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        setTitle("详细资料");
        showBackwardView(R.string.back, true);
        mAvatar = getViewById(R.id.civ_avatar);
        mAccount = getViewById(R.id.tv_account);
        mRegion = getViewById(R.id.tv_region);
        mSiagnature = getViewById(R.id.tv_signature);
        mAddFriends = getViewById(R.id.btn_add);
        mAddFriends.setOnClickListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        if (user != null) {
            mAccount.setText(user.getAccount());
            mRegion.setText(user.getAddress());
            mSiagnature.setText(user.getSignature());
        }
    }

    /**
     * 判断是否已经是好友
     *
     * @param userId
     * @return
     */
    private boolean isFriendAlready(int userId) {
        boolean isFriend = false;
        List<Friend> friends = getFriendFromOrm();
        for (Friend friend : friends) {
            if (friend.getFriendUid() == userId) {
                isFriend = true;
                break;
            }
        }
        return isFriend;
    }

    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_add:
                if (isFriendAlready(user.getUserId())) {
                    showToast(R.string.string_has_already_been_friend);
                }else {
                BaseSendMsg baseSendMsg =
                        new BaseSendMsg(BaseSendMsg.ADDFRIEND, String.valueOf(user.getUserId()),
                                System.currentTimeMillis(), System.currentTimeMillis());
                sendChatMsg(gson.toJson(baseSendMsg));
                showProgressDialog(R.string.adding_friend);}
                break;
            default:
                break;
        }
        super.onClick(v);
    }

    @Override
    protected void onReceive(Message message) {
        super.onReceive(message);
        if (BaseSendMsg.ADDFRIEND_BACK.equals(message.getType())) {
            cancelProgressDialog();
            showToast(R.string.apply_adding_success);
        }
    }
}
