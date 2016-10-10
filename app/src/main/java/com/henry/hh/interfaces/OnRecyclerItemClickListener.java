package com.henry.hh.interfaces;

import android.view.View;

import java.util.List;

/**
 * Date: 2016/10/10. 17:11
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:实现RecyclerView点击接口
 */
public interface OnRecyclerItemClickListener {
    void onItemClick(View view, List data, int position);
}
