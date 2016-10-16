package com.henry.hh.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.Message;
import com.henry.library.View.CircleImageView;
import com.henry.library.adapter.BaseRecyclerAdapter;
import com.henry.library.adapter.RecyclerHolder;
import com.henry.library.utils.TimeUtils;

import io.github.rockerhieu.emojicon.EmojiconTextView;

import static android.R.attr.data;

/**
 * Date: 2016/10/14. 11:15
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 聊天界面适配器
 */
public class ChatAdapter extends BaseRecyclerAdapter<ChatAdapter.ViewHolder, Message> {

    //建立枚举 2个item 类型
    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

    public ChatAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        //加载Item View的时候根据不同TYPE加载不同的布局
        if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
            view = inflater.inflate(R.layout.item_chat_left, parent, false);
        } else {
            view = inflater.inflate(R.layout.item_chat_right, parent, false);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Message message = datalist.get(position);
        //设置时间
        holder.mTime.setText(TimeUtils.getRelativeTime(context, message.getTimeMillis()));
        /**
         * 根据消息类型显示消息内容：
         * MSG_TYPE_TEXT：文字（emjo）
         * MSG_TYPE_PHOTO:图片
         */
        if (message.getType() == Message.MSG_TYPE_TEXT){
            holder.mChatContent.setVisibility(View.VISIBLE);
            holder.mChatContent.setText(message.getContent()==null?"":message.getContent());
            holder.mChatImg.setVisibility(View.GONE);
        }else if (message.getType() == Message.MSG_TYPE_PHOTO){
            holder.mChatContent.setVisibility(View.GONE);
            holder.mChatImg.setVisibility(View.VISIBLE);
        }


        if (position % 2 == 0)
            holder.mLayoutContent.setBackgroundResource(R.drawable.chat_from_bg_selector);
        else
        holder.mLayoutContent.setBackgroundResource(R.drawable.chat_to_bg_selector);

        /**
         * 消息状态
         * MSG_STATE_FAIL：发送失败
         * MSG_STATE_SUCCESS：发送成功
         * MSG_STATE_SENDING：正在发送
         */
        switch (message.getState()) {
            case Message.MSG_STATE_FAIL:
                holder.mSending.setVisibility(View.GONE);
                holder.mChatFailed.setVisibility(View.VISIBLE);
                break;
            case Message.MSG_STATE_SUCCESS:
                holder.mSending.setVisibility(View.GONE);
                holder.mChatFailed.setVisibility(View.GONE);
                break;
            case Message.MSG_STATE_SENDING:
                holder.mSending.setVisibility(View.VISIBLE);
                holder.mChatFailed.setVisibility(View.GONE);
                break;
        }

        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    /**
     * 设置ITEM类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        //Enum类提供了一个ordinal()方法，返回枚举类型的序数，这里ITEM_TYPE.ITEM1.ordinal()代表0， ITEM_TYPE.ITEM2.ordinal()代表1
        int type = position % 2 == 0 ? ITEM_TYPE.ITEM1.ordinal() : ITEM_TYPE.ITEM2.ordinal();
        return type;
    }

    class ViewHolder extends RecyclerHolder {

        //聊天头像
        private CircleImageView mAvatar;
        //聊天时间
        private TextView mTime;
        //聊天内容
        private EmojiconTextView mChatContent;
        //聊天图片
        private ImageView mChatImg;
        //聊天信息发送失败图标
        private ImageView mChatFailed;
        //聊天信息正在发送图标
        private ProgressBar mSending;
        //聊天消息内容布局
        private RelativeLayout mLayoutContent;

        public ViewHolder(View itemView) {
            super(itemView);
            mTime = getViewById(R.id.chat_item_date);
            mAvatar = getViewById(R.id.chat_item_avatar);
            mChatContent = getViewById(R.id.chat_item_content_text);
            mChatImg = getViewById(R.id.chat_item_content_image);
            mLayoutContent = getViewById(R.id.chat_item_layout_content);
            mChatFailed = getViewById(R.id.chat_item_fail);
            mSending = getViewById(R.id.chat_item_progress);
        }
    }
}