package com.henry.hh.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
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
import com.henry.hh.entity.base.BaseMsgBean;
import com.henry.hh.entity.base.BaseSendMsg;
import com.henry.hh.fragment.FriendsListFragment;
import com.henry.hh.interfaces.OnChatItemClickListener;
import com.henry.hh.interfaces.OnChatItemLongClickListener;
import com.henry.hh.interfaces.OnOperationListener;
import com.henry.hh.utils.DisplayRules;
import com.henry.hh.widget.ChatKeyboard;
import com.henry.library.utils.FileUtils;
import com.henry.library.utils.LogUtils;
import com.henry.library.utils.TimeUtils;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.litesuits.orm.db.assit.WhereBuilder;
import com.litesuits.orm.db.model.ColumnsValue;
import com.litesuits.orm.db.model.ConflictAlgorithm;

import java.io.File;
import java.util.HashMap;
import java.util.List;


public class ChatActivity extends MyBaseActivity implements OnOperationListener, OnChatItemLongClickListener, OnChatItemClickListener {
    public static final int REQUEST_CODE_GETIMAGE_BYSDCARD = 0x1;

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private ChatKeyboard mChatKeyboard;
    private ChatAdapter chatAdapter;

    private List<Message> messageList;
    private Friend friend;
    //用于保存消息发送状态
    HashMap map = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        initWidget();
        initList();
        initData();

    }

    @Override
    protected void onResume() {
        super.onResume();

        messageList = getDatas();
        chatAdapter.refresh(messageList);
        chatAdapter.addOnItemClickListener(this);
        chatAdapter.addOnItemLongClickListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
        setMsgReaded();
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

    /**
     * 获取数据库中消息列表
     *
     * @return
     */
    private List<Message> getDatas() {
        //升序查找消息列表
        List<Message> messages = liteOrm.<Message>query(new QueryBuilder<Message>(Message.class).
                appendOrderAscBy("SendTimeMillis")
                .where("type=? and fromUserId=? or toUserId=?", "chat", friend.getFriendUid(), friend.getFriendUid()));
//        LogUtils.d(TAG, "list>>> " + messages.toString());

        return messages;
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
        message.setSendTimeMillis(System.currentTimeMillis());
        message.setUid(System.currentTimeMillis());
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
        showLastItem();
        if (message.getState() == Message.MSG_STATE_SENDING) {
            map.put(message.getUid(), false);
            message.setIsRead(1);
            liteOrm.insert(message, ConflictAlgorithm.Abort);
            LogUtils.d(TAG, "list=" + liteOrm.query(Message.class));
            //5m则发送失败
            new Handler().postDelayed(new myRunnable(message.getUid()), 5000);
        }

    }

    /**
     * 显示最新一条消息
     */
    private void showLastItem() {
        //显示最后一个item
        recyclerView.scrollToPosition(chatAdapter.getItemCount() - 1);
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
        //返回发送成功的确认信息
        if (BaseSendMsg.CHAT_BACK.equals(message.getType())) {
            long uid = message.getUid();
            setItemState(uid, Message.MSG_STATE_SUCCESS);
            map.put(uid, true);

            //获取聊天消息
        } else if (BaseSendMsg.CHAT.equals(message.getType())) {
            chatAdapter.append(message);
            showLastItem();

        }
    }

    /**
     * 设置item作态
     *
     * @param uid
     * @param state
     */
    private void setItemState(long uid, int state) {
        final List<Message> messages = chatAdapter.getDatalist();
        for (int i = 0; i < messages.size(); i++) {
            if (uid == messages.get(i).getUid()) {
                final Message msg = messages.get(i);
                msg.setState(state);

                final int finalI = i;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatAdapter.notifyItemChanged(finalI, msg);
                        showLastItem();
                    }
                });
                //修改数据库中消息状态
                updateState(msg.getUid(), Message.MSG_STATE_SUCCESS);
            }
        }
    }

    /**
     * 延时操作时，操作
     */
    class myRunnable implements Runnable {

        long uid;

        myRunnable(long id) {
            uid = id;
        }

        @Override
        public void run() {
            boolean isSendSuccess = (boolean) map.get(uid);
            if (!isSendSuccess) {
                setItemState(uid, Message.MSG_STATE_FAIL);
                //更新数据库》发送状态
                updateState(uid, Message.MSG_STATE_FAIL);
            }

        }
    }


    /**
     * 修改数据库中发送状态
     *
     * @param uid
     * @param state
     */
    private void updateState(long uid, int state) {

        //更新数据库》发送状态
        liteOrm.update(new WhereBuilder(Message.class).where("uid=?", uid),
                new ColumnsValue(new String[]{"state"}, new Object[]{state}),
                ConflictAlgorithm.Fail);
//        LogUtils.d(TAG, "list=" + liteOrm.query(Message.class));

    }

    /**
     * 更新数据库》设置该联系人的消息为已读
     */
    private void setMsgReaded() {

        liteOrm.update(new WhereBuilder(Message.class).
                        where("type=? and fromUserId=? or toUserId=?", BaseSendMsg.CHAT, friend.getFriendUid(), friend.getFriendUid()),
                new ColumnsValue(new String[]{"isRead"}, new Object[]{1}),
                ConflictAlgorithm.Fail);

    }
}
