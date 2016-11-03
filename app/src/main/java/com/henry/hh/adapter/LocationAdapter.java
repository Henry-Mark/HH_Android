package com.henry.hh.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.services.core.PoiItem;
import com.henry.hh.R;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.adapter.BaseRecyclerAdapter;
import com.henry.library.adapter.RecyclerHolder;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;

/**
 * Date: 2016/11/2. 17:35
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 收索地点的适配器
 */
public class LocationAdapter extends BaseRecyclerAdapter<LocationAdapter.ViewHolder, PoiItem>
        implements View.OnClickListener {
    //点击监听事件
    private OnRecyclerItemClickListener mOnItemClickListener = null;

    public LocationAdapter(Context context) {
        super(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_location, parent, false);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);

        PoiItem poiItem = datalist.get(position);
        if (poiItem!=null){
            holder.mContent.setText(poiItem.toString());
            holder.mDistance.setText(poiItem.getDistance()+"米");
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
     * item点击事件
     *
     * @param listener
     */
    public void addOnItemClickListener(OnRecyclerItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    class ViewHolder extends RecyclerHolder {

        /**
         * 地点
         */
        public TextView mContent;
        /**
         * 距离
         */
        public TextView mDistance;
        /**
         * 整体布局
         */
        public LinearLayout mLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mContent = getViewById(R.id.tv_content);
            mDistance = getViewById(R.id.tv_distance);
            mLayout = getViewById(R.id.layout_location);
            ControlsUtils.setHeight(mLayout, ScreenUtils.getScreenHeight(context) / 12);
        }
    }
}
