package com.henry.library.adapter;

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Date: 2016/10/10. 16:12
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: ViewHolder基类
 */
public class RecyclerHolder extends RecyclerView.ViewHolder {

    public View rootView;

    public RecyclerHolder(View itemView) {
        super(itemView);
        rootView = itemView;
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) rootView.findViewById(id);
    }
}
