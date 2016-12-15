package com.henry.hh.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.henry.hh.R;
import com.henry.hh.adapter.ChatAdapter;
import com.henry.hh.entity.Emojicon;
import com.henry.hh.entity.Friend;
import com.henry.hh.entity.Message;
import com.henry.hh.entity.User;
import com.henry.hh.entity.base.BaseSendMsg;
import com.henry.hh.fragment.FriendsListFragment;
import com.henry.hh.interfaces.OnChatItemClickListener;
import com.henry.hh.interfaces.OnChatItemLongClickListener;
import com.henry.hh.interfaces.OnOperationListener;
import com.henry.hh.utils.DisplayRules;
import com.henry.hh.widget.ChatKeyboard;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.FileUtils;
import com.henry.library.utils.LogUtils;
import com.henry.library.utils.TimeUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class ChatActivity extends MyBaseActivity implements OnOperationListener, OnChatItemLongClickListener, OnChatItemClickListener {
    public static final int REQUEST_CODE_GETIMAGE_BYSDCARD = 0x1;
    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private ChatKeyboard mChatKeyboard;
    private ChatAdapter chatAdapter;

    private List<Message> messageList;
    private Friend friend;

    HashMap map = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initWidget();
        initList();
        initData();
        messageList = getDatas(10);
        chatAdapter.refresh(messageList);
        chatAdapter.addOnItemClickListener(this);
        chatAdapter.addOnItemLongClickListener(this);
    }

    /**
     * 初始化控件
     */
    private void initWidget() {
        recyclerView = getViewById(R.id.chat_recyclerview);
        mChatKeyboard = (getViewById(R.id.chat_msg_input_box));
        mChatKeyboard.setOnOperationListener(this);
    }

    /**
     * 初始化数据
     */
    private void initData() {
        showBackwardView("返回", true);
        friend = (Friend) getIntent().getSerializableExtra(FriendsListFragment.UID);
        String name = TextUtils.isEmpty(friend.getRemarkName()) ?
                TextUtils.isEmpty(friend.getFriendInfo().getNickname()) ? friend.getFriendInfo().getAccount()
                        : friend.getFriendInfo().getNickname() : friend.getRemarkName();
        setTitle(name);
    }

    /**
     * 初始化列表
     *
     * @return
     */
    private void initList() {
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(mContext);
        //显示底部位置
        mLayoutManager.setStackFromEnd(true);
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
            message.setSendTimeMillis(TimeUtils.getSysCurrentMillis() + i * 1000000);
            message.setToUserId(user.getUserId());
            message.setFromUserId(friend.getFriendUid());
            message.setMessageType(Message.MSG_TYPE_TEXT);
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

    /**
     * 返回键
     */
    @Override
    public void onBackPressed() {
        if (mChatKeyboard.isShow()) {
            mChatKeyboard.hideLayout();
        } else
            super.onBackPressed();
    }

    @Override
    public void send(String content) {
        Message message = new Message();
        message.setSendTimeMillis(TimeUtils.getSysCurrentMillis());
        message.setFromUserId(user.getUserId());
        message.setToUserId(friend.getFriendUid());
        message.setMessageType(Message.MSG_TYPE_TEXT);
        message.setType(BaseSendMsg.CHAT);
        message.setContent(mChatKeyboard.getEditTextBox().getText().toString());
        message.setState(Message.MSG_STATE_SENDING);
        sendMessage(message);
    }

    @Override
    public void selectedEmoji(Emojicon emojicon) {
        mChatKeyboard.getEditTextBox().append(emojicon.getValue());
    }

    @Override
    public void selectedBackSpace(Emojicon back) {
        DisplayRules.backspace(mChatKeyboard.getEditTextBox());
    }

    @Override
    public void selectedFunction(int index) {
        switch (index) {
            case 0:
                goToAlbum();
                break;
            case 1:

                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != Activity.RESULT_OK) {
            return;
        }
        if (requestCode == REQUEST_CODE_GETIMAGE_BYSDCARD) {
            Uri dataUri = data.getData();
            if (dataUri != null) {
                File file = FileUtils.uri2File(this, dataUri);
                Message message = new Message();
                message.setSendTimeMillis(TimeUtils.getSysCurrentMillis());
                message.setMessageType(Message.MSG_TYPE_PHOTO);
                message.setContent(file.getAbsolutePath());
                message.setState(Message.MSG_STATE_SUCCESS);
                message.setUid(TimeUtils.getSysCurrentMillis());
                sendMessage(message);
            }
        }
    }

    /**
     * 发送消息
     *
     * @param message
     */
    private void sendMessage(Message message) {
        sendChatMsg(gson.toJson(message));
        chatAdapter.append(message);
        //显示最后一个item
        recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
        HashMap map = new HashMap();
        if (message.getState() == Message.MSG_STATE_SENDING) {
            Timer timer = new Timer();
            timer.schedule(new MyTimerTask(message.getUid()), 5000, 5000);
            map.put(message.getUid(), timer);
        }

    }

    /**
     * 跳转到选择相册界面
     */
    private void goToAlbum() {
        Intent intent;
        if (Build.VERSION.SDK_INT < 19) {
            intent = new Intent();
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    REQUEST_CODE_GETIMAGE_BYSDCARD);
        } else {
            intent = new Intent(Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            startActivityForResult(Intent.createChooser(intent, "选择图片"),
                    REQUEST_CODE_GETIMAGE_BYSDCARD);
        }

    }

    @Override
    public void onImgClick(int position, Bitmap bitmap) {
        showToast("onImgClick.....");
    }

    @Override
    public void onTextClick(int position) {
        showToast("onTextClick.....");
    }

    @Override
    public void onAvatarClick(int position) {
        showToast("onAvatarClick.....");
        MyApplication data = (MyApplication) getApplication();
        User user = new User();
        user.setNickname("soidhoi.........sdhfjhf");
        data.setUser(user);
        startActivity(Test1Activity.class);
    }

    @Override
    public void onContentLongClick(int position) {
        showToast("onContentLongClick.....");
    }

    @Override
    protected void onReceive(Message message) {
        super.onReceive(message);
        if (BaseSendMsg.CHAT_BACK.equals(message.getType())) {
            LogUtils.d(TAG, "send success...");
        }
    }

    class MyTimerTask extends TimerTask {
        long uid;

        MyTimerTask(long id) {
            uid = id;
        }

        @Override
        public void run() {

//            List<Message> messages = chatAdapter.getDatalist();
//            for (Message msg : messages) {
//                if (uid == msg.getUid()) {
//                    msg.setState(Message.MSG_STATE_SUCCESS);
//                }
//            }
//            map.remove(uid);
//            cancel();
//            chatAdapter.refresh(messages);
        }
    }
}
