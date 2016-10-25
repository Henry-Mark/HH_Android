package com.henry.hh.adapter;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.Emojicon;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.adapter.BaseRecyclerAdapter;
import com.henry.library.adapter.RecyclerHolder;

/**
 * Date: 2016/10/17. 10:21
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:emoji表情界面recycleView适配器
 */
public class EmojiAdapter extends
        BaseRecyclerAdapter<EmojiAdapter.ViewHolder, Emojicon>
        implements View.OnClickListener {

    //点击监听事件
    private OnRecyclerItemClickListener mOnItemClickListener = null;

    public EmojiAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_chat_emoji, parent, false);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.mTextView.setGravity(Gravity.CENTER);
        holder.mTextView.setText(datalist.get(position).getValue());
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(position);
    }

    /**
     * item点击事件
     *
     * @param listener
     */
    public void addOnItemClickListener(OnRecyclerItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        //使用getTag方法获取数据
        if (mOnItemClickListener != null)
            mOnItemClickListener.onItemClick(view, datalist, (Integer) view.getTag());
    }

    class ViewHolder extends RecyclerHolder {

        public TextView mTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = getViewById(R.id.itemEmoji);
        }
    }
}
