package com.henry.hh.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.Friend;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.CircleImageView;
import com.henry.library.adapter.BaseRecyclerAdapter;
import com.henry.library.adapter.RecyclerHolder;

import java.util.List;

/**
 * Date: 2016/10/26. 15:03
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:好友列表适配器
 */
public class FriendAdapter extends BaseRecyclerAdapter<FriendAdapter.ViewHolder, Friend>
        implements View.OnClickListener {

    //点击监听事件
    private OnRecyclerItemClickListener mOnItemClickListener = null;

    public FriendAdapter(Context context) {
        super(context);
    }

    public FriendAdapter(Context context, List datalist) {
        super(context, datalist);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_friends, parent, false);
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
         */
        String name;
        if (TextUtils.isEmpty(friend.getRemarkName())) {
            name = TextUtils.isEmpty(friend.getFriendInfo().getNickname())
                    ? String.valueOf(friend.getFriendInfo().getAccount()) : friend.getFriendInfo().getNickname();
        } else {
            name = friend.getRemarkName();
        }
        holder.mName.setText(name);
        //设置签名
        String signature = friend.getFriendInfo().getSignature();
        holder.mSignature.setText(signature == null ? "" : signature);
        //设置标签
        holder.mLabel.setText(friend.getLabel() == null ? "" : friend.getLabel());

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

    class ViewHolder extends RecyclerHolder {

        public CircleImageView mAvatar;
        public TextView mName;
        public TextView mSignature;
        public TextView mLabel;

        public ViewHolder(View itemView) {
            super(itemView);
            mAvatar = getViewById(R.id.civ_avatar);
            mName = getViewById(R.id.tv_name);
            mSignature = getViewById(R.id.tv_signature);
            mLabel = getViewById(R.id.tv_label);
        }
    }
}
