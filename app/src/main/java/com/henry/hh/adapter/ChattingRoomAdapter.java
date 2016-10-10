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
import com.henry.library.View.CircleImageView;
import com.henry.library.View.CircleTextImageView;
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
public class ChattingRoomAdapter extends RecyclerView.Adapter<ChattingRoomAdapter.ViewHolder> {

    private List<ChattingRoom> mRoomList;
    private Context mContext;

    public ChattingRoomAdapter(Context context,List<ChattingRoom> roomList) {
        if (roomList == null)
            throw new IllegalArgumentException("model Data must not be null");
        this.mRoomList = roomList;
        this.mContext = context;
    }

    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat, parent, false);
        return new ViewHolder(view);
    }

    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ChattingRoom room = mRoomList.get(position);
        holder.mUser.setText(room.getUserId());
        holder.mContent.setText(room.getContent());
        holder.mAmount.setText("" + room.getAmountUnread());
        holder.mTime.setText(TimeUtils.getRelativeTime(mContext,room.getMessageTime()));

        ControlsUtils.setHeight(holder.mLayout, ScreenUtils.getScreenHeight(mContext)/10);
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    //获取数据的数量
    @Override
    public int getItemCount() {
        return mRoomList.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
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
            mLayout = (LinearLayout)view.findViewById(R.id.layout_chat);
            mAvatar = (CircleImageView) view.findViewById(R.id.civ_avatar);
            mUser = (TextView) view.findViewById(R.id.tv_user);
            mContent = (TextView) view.findViewById(R.id.tv_content);
            mTime = (TextView) view.findViewById(R.id.tv_time);
            mAmount = (CircleTextImageView) view.findViewById(R.id.ctiv_amount);
            mAmount.setTextColor(Color.WHITE);
        }
    }
}
