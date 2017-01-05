package com.henry.hh.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.Friend;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.CircleImageView;
import com.henry.library.adapter.BaseRecyclerAdapter;
import com.henry.library.adapter.RecyclerHolder;

import java.util.List;

/**
 * Date: 2016/10/27. 9:30
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 搜索历史、搜索结果的适配器
 */
public class SearchAdapter extends BaseRecyclerAdapter<SearchAdapter.ViewHolder, Friend>
        implements View.OnClickListener {
    //点击监听事件
    private OnRecyclerItemClickListener mOnItemClickListener = null;
    private OnClearClickListener onClearClickListener = null;
    private boolean isSearched = false;

    public SearchAdapter(Context context) {
        super(context);
    }

    public SearchAdapter(Context context, List<Friend> datalist) {
        super(context, datalist);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_search, parent, false);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        super.onBindViewHolder(holder, position);
        Friend friend = datalist.get(position);

        //备注名称（账号）
        holder.mRemarkName.setText(friend.getRemarkName() + "  (" + friend.getFriendUid() + ")");
        if (isSearched) {   //搜索结果
            holder.mNickName.setVisibility(View.VISIBLE);
            String nickname = friend.getFriendInfo().getNickname();
            holder.mNickName.setText("昵称："
                    + (nickname == null ? "" : nickname));
            holder.mClear.setVisibility(View.GONE);
        } else {    //历史搜索
            holder.mNickName.setVisibility(View.GONE);
            holder.mClear.setVisibility(View.VISIBLE);
            holder.mClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClearClickListener != null)
                        onClearClickListener.onClearClick(position,datalist);
                }
            });
        }

        holder.itemView.setTag(position);
    }

    @Override
    public void onClick(View v) {
        //使用getTag方法获取数据
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(v, datalist, (Integer) v.getTag());
    }

    /**
     * 设置是否搜索
     * true:显示搜索内容
     * false:显示历史收索记录
     *
     * @param searched
     */
    public void setSearched(boolean searched) {
        isSearched = searched;
    }

    /**
     * item点击事件
     *
     * @param listener
     */
    public void addOnItemClickListener(OnRecyclerItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public void addOnClearListener(OnClearClickListener listener) {
        this.onClearClickListener = listener;
    }

    class ViewHolder extends RecyclerHolder {
        //头像
        public CircleImageView mAvatar;
        //备注名称
        public TextView mRemarkName;
        //昵称
        public TextView mNickName;
        //x
        public ImageView mClear;

        public ViewHolder(View itemView) {
            super(itemView);
            mAvatar = getViewById(R.id.civ_avatar);
            mRemarkName = getViewById(R.id.tv_name);
            mNickName = getViewById(R.id.tv_nickname);
            mClear = getViewById(R.id.iv_clear);
        }
    }

    /**
     * 清除图标监听事件
     */
    public interface OnClearClickListener {
        //点击清除
        void onClearClick(int position,List data);
    }
}
