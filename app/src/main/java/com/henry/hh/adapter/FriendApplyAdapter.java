package com.henry.hh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.Message;
import com.henry.library.View.CircleImageView;
import com.henry.library.adapter.BaseRecyclerAdapter;
import com.henry.library.adapter.RecyclerHolder;

import java.util.List;

/**
 * Date: 2017/1/10. 13:51
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:展示申请列表的适配器
 */
public class FriendApplyAdapter extends BaseRecyclerAdapter<FriendApplyAdapter.ViewHolder, Message> {

    private OnAgreeClickListener listener = null;

    public FriendApplyAdapter(Context context) {
        super(context);
    }

    public FriendApplyAdapter(Context context, List<Message> datalist) {
        super(context, datalist);
    }

    @Override
    public FriendApplyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_friend_apply, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        Message message = datalist.get(position);

        holder.mAccount.setText(message.getFromUserId() + "");
        holder.mMsg.setText(message.getContent());
        /**
         * 使用state变量：
         * 5.表示已同意
         *
         */
        if (message.getState() == Message.MSG_STATE_ALREADY_ARGEE) {
            holder.mLLAgree.setVisibility(View.GONE);
            holder.mAlreadyAgree.setVisibility(View.VISIBLE);
        } else {
            holder.mLLAgree.setVisibility(View.VISIBLE);
            holder.mAlreadyAgree.setVisibility(View.GONE);
            holder.mAgree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onAgree(datalist, position);
                    }
                }
            });
            holder.mDisagree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onRefuse(datalist, position);
                    }
                }
            });
        }

    }

    /**
     * 添加监听事件
     *
     * @param listener
     */
    public void addAgreeMentClickListener(OnAgreeClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerHolder {
        //头像
        public CircleImageView mAvatar;
        //账号
        public TextView mAccount;
        //验证消息
        public TextView mMsg;
        //同意
        public TextView mAgree;
        //已同意
        public TextView mAlreadyAgree;
        //拒绝
        public TextView mDisagree;
        //同意布局
        public LinearLayout mLLAgree;


        public ViewHolder(View itemView) {
            super(itemView);
            mAvatar = getViewById(R.id.civ_avatar);
            mAccount = getViewById(R.id.tv_account_apply);
            mMsg = getViewById(R.id.tv_msg);
            mAlreadyAgree = getViewById(R.id.tv_agree_already);
            mAgree = getViewById(R.id.tv_agree);
            mDisagree = getViewById(R.id.tv_disagree);
            mLLAgree = getViewById(R.id.ll_argee);
        }
    }

    /**
     * 监听同意或者拒绝
     */
    public interface OnAgreeClickListener {
        //同意
        void onAgree(List data, int position);

        //拒绝
        void onRefuse(List data, int position);
    }
}
