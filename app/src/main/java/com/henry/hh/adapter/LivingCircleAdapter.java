package com.henry.hh.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.activity.MyApplication;
import com.henry.hh.entity.LivingCircleDynamic;
import com.henry.hh.interfaces.OnLivingDynamicItemClickListener;
import com.henry.library.View.CircleImageView;
import com.henry.library.adapter.BaseRecyclerAdapter;
import com.henry.library.adapter.RecyclerHolder;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;
import com.henry.library.utils.TimeUtils;

/**
 * Date: 2016/10/27. 16:01
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 生活圈动态列表适配器
 */
public class LivingCircleAdapter extends
        BaseRecyclerAdapter<LivingCircleAdapter.ViewHolder, LivingCircleDynamic> {

    private OnLivingDynamicItemClickListener listener = null;
    private MyApplication app;

    public LivingCircleAdapter(Context context) {
        super(context);
        app = (MyApplication) context.getApplicationContext();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_living_circle, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        //设置子空间监听
        OnDynamicClickListener myListrner = new OnDynamicClickListener(viewHolder);
        viewHolder.mAvatar.setOnClickListener(myListrner);
        viewHolder.mName.setOnClickListener(myListrner);
        viewHolder.mContent.setOnClickListener(myListrner);
        viewHolder.mContacts.setOnClickListener(myListrner);
        viewHolder.mComment.setOnClickListener(myListrner);
        viewHolder.mPraise.setOnClickListener(myListrner);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.itemView.setTag(position);
        LivingCircleDynamic data = datalist.get(position);
        /**
         * 设置名称：
         * 首选备注名
         * 若为null，则显示昵称
         * 若还是null，则显示ID
         */
        String name;
        if (TextUtils.isEmpty(data.getRemarkName())) {
            name = TextUtils.isEmpty(data.getNickname())
                    ? String.valueOf(data.getUserId()) : data.getNickname();
        } else {
            name = data.getRemarkName();
        }
        holder.mName.setText(name);
        //位置、时间、内容、赞
        holder.mLocation.setText(TextUtils.isEmpty(data.getLocaion()) ? "保密" : data.getLocaion());
        holder.mTime.setText(TimeUtils.getRelativeTime(context, data.getDeliveryTimeMillis()));
        holder.mContent.setText(TextUtils.isEmpty(data.getContent()) ? "" : data.getContent());
        holder.mPraiseTv.setText(data.getPraise_count() + "");
        //内容背景
        if (data.getBackType() == LivingCircleDynamic.BACK_TYPE_PIC) {
            holder.mContent.setBackgroundResource(R.mipmap.ic_launcher);
        } else if (data.getBackType() == LivingCircleDynamic.BACK_TYPE_COLOR) {
            holder.mContent.setBackgroundColor(app.useColor(data.getBack_color()));
        }

        //设置字体颜色
        if (app.useColor(data.getTextcolor())!=-1){
            holder.mContent.setTextColor(app.useColor(data.getTextcolor()));
        }
    }

    public void addDynamicClickListener(OnLivingDynamicItemClickListener listener) {
        this.listener = listener;
    }

    /**
     * 子控件点击事件
     */
    class OnDynamicClickListener implements View.OnClickListener {
        private ViewHolder holder;

        OnDynamicClickListener(ViewHolder holder) {
            this.holder = holder;
        }

        @Override
        public void onClick(View v) {
            int position = holder.getAdapterPosition();
            if (listener != null) {
                if (v == holder.mAvatar) {
                    listener.onAvatarClick(datalist, position);
                } else if (v == holder.mName) {
                    listener.onUserClick(datalist, position);
                } else if (v == holder.mContent) {
                    listener.onContentClick(datalist, position);
                } else if (v == holder.mContacts) {
                    listener.onContactClick(datalist, position);
                } else if (v == holder.mComment) {
                    listener.onCommentClick(datalist, position);
                } else if (v == holder.mPraise) {
                    listener.onPraiseClick(datalist, position);
                }
            }
        }
    }

    class ViewHolder extends RecyclerHolder {
        //头像
        public CircleImageView mAvatar;
        //名称
        public TextView mName;
        //位置
        public TextView mLocation;
        //时间
        public TextView mTime;
        //内容
        public TextView mContent;
        //发消息
        public LinearLayout mContacts;
        //评论
        public LinearLayout mComment;
        //赞
        public LinearLayout mPraise;
        //赞
        public TextView mPraiseTv;
        //整体布局
        public LinearLayout mLayout;
        //顶部、底部留空间
        public View view1, view2;

        public ViewHolder(View itemView) {
            super(itemView);
            mAvatar = getViewById(R.id.civ_avatar);
            mName = getViewById(R.id.tv_name);
            mLocation = getViewById(R.id.tv_location);
            mTime = getViewById(R.id.tv_time);
            mContent = getViewById(R.id.tv_content);
            mPraiseTv = getViewById(R.id.tv_praise);
            mContacts = getViewById(R.id.ll_contacts);
            mComment = getViewById(R.id.ll_comment);
            mPraise = getViewById(R.id.ll_praise);
            mLayout = getViewById(R.id.ll_layout);
            view1 = getViewById(R.id.view1);
            view2 = getViewById(R.id.view2);
            //布局之间留空间
            ControlsUtils.setHeight(view1, ScreenUtils.getScreenWidth(context) / 50);
            ControlsUtils.setHeight(view2, ScreenUtils.getScreenWidth(context) / 50);
            //内容为正方形
            ControlsUtils.setSize(mContent, ScreenUtils.getScreenWidth(context) * 95 / 100,
                    ScreenUtils.getScreenWidth(context) * 95 / 100);
        }
    }
}
