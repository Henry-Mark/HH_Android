package com.henry.hh.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.ChattingRoom;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.CircleImageView;
import com.henry.library.View.CircleTextImageView;
import com.henry.library.adapter.BaseRecyclerAdapter;
import com.henry.library.adapter.RecyclerHolder;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;
import com.henry.library.utils.TimeUtils;

import java.util.List;

/**
 * Date: 2016/10/10. 10:31
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 聊天室列表的适配器
 */
public class ChattingRoomAdapter extends BaseRecyclerAdapter<ChattingRoomAdapter.ViewHolder, ChattingRoom> implements View.OnClickListener {

    //点击监听事件
    private OnRecyclerItemClickListener mOnItemClickListener = null;

    public ChattingRoomAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_chat, parent, false);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        ChattingRoom room = datalist.get(position);
        holder.mUser.setText(room.getUserId());
        holder.mContent.setText(room.getContent());
        holder.mAmount.setText("" + room.getAmountUnread());
        holder.mTime.setText(TimeUtils.getRelativeTime(context, room.getMessageTime()));

        ControlsUtils.setHeight(holder.mLayout, ScreenUtils.getScreenHeight(context) / 10);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    @Override
    public void onClick(View view) {
        //使用getTag方法获取数据
        mOnItemClickListener.onItemClick(view, datalist, (Integer) view.getTag());
    }

    /**
     * item点击事件
     * @param listener
     */
    public void setOnItemClickListener(OnRecyclerItemClickListener listener) {
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