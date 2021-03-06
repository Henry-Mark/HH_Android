package com.henry.hh.adapter;

import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.Chatting;
import com.henry.hh.entity.Friend;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.CircleImageView;
import com.henry.library.View.CircleTextImageView;
import com.henry.library.adapter.BaseRecyclerAdapter;
import com.henry.library.adapter.RecyclerHolder;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;
import com.henry.library.utils.TimeUtils;

/**
 * Date: 2016/10/10. 10:31
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 聊天室列表的适配器
 */
public class MsgAdapter
        extends BaseRecyclerAdapter<MsgAdapter.ViewHolder, Friend>
        implements View.OnClickListener {

    //点击监听事件
    private OnRecyclerItemClickListener mOnItemClickListener = null;

    public MsgAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_message, parent, false);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Friend friend = datalist.get(position);
        /**
         * 设置名称：
         * 首选备注名
         * 若为null，则显示昵称
         * 若还是null，则显示账号
         * 添加好友消息，则显示“好友申请”
         */
        String name;
        //uid==-1为添加好友消息
        if (friend.getFriendUid() == -1) {
            name = context.getResources().getString(R.string.friend_apply);
            holder.mContent.setVisibility(View.GONE);
        } else {

            if (TextUtils.isEmpty(friend.getRemarkName())) {
                name = TextUtils.isEmpty(friend.getFriendInfo().getNickname())
                        ? String.valueOf(friend.getFriendInfo().getAccount()) : friend.getFriendInfo().getNickname();
            } else {
                name = friend.getRemarkName();
            }
            holder.mContent.setVisibility(View.VISIBLE);
            holder.mContent.setText(friend.getLastContent());
        }
        holder.mUser.setText(name);
        holder.mTime.setText(TimeUtils.getRelativeTime(context, friend.getLastChatTimeMillis()));

        /**
         * 设置未读消息条数,0条则隐藏
         * 1.0 - 9 条，显示具体条数，前后各加一个空格，已保证大小长度与两个数字相同
         * 2.10 - 99 条，显示具体条数；
         * 3.大于100条时，只显示99条；
         */
        int amount = friend.getAmountUnread();
        if (amount <= 0) {
            holder.mAmount.setVisibility(View.INVISIBLE);
        } else if (amount < 10) {
            holder.mAmount.setText(" " + amount + " ");
        } else if (amount < 100)
            holder.mAmount.setText("" + amount);
        else
            holder.mAmount.setText("99");


        ControlsUtils.setHeight(holder.mLayout, ScreenUtils.getScreenHeight(context) / 10);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public void onClick(View view) {
        //使用getTag方法获取数据
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(view, datalist, (Integer) view.getTag());
    }

    /**
     * item点击事件
     *
     * @param listener
     */
    public void addOnItemClickListener(OnRecyclerItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    class ViewHolder extends RecyclerHolder {
        //头像
        public CircleImageView mAvatar;
        //用户名
        public TextView mUser;
        //消息内容
        public TextView mContent;
        //时间
        public TextView mTime;
        //未读消息条数
        public CircleTextImageView mAmount;

        public LinearLayout mLayout;

        public ViewHolder(View view) {
            super(view);
            mLayout = (LinearLayout) view.findViewById(R.id.layout_chat);
            mAvatar = (CircleImageView) view.findViewById(R.id.civ_avatar);
            mUser = (TextView) view.findViewById(R.id.tv_user);
            mContent = (TextView) view.findViewById(R.id.tv_content);
            mTime = (TextView) view.findViewById(R.id.tv_time);
            mAmount = (CircleTextImageView) view.findViewById(R.id.ctiv_amount);
            mAmount.setTextColor(Color.WHITE);

        }
    }
}