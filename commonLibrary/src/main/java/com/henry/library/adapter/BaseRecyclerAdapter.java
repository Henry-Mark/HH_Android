package com.henry.library.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.henry.library.adapter.RecyclerHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2016/10/10. 15:09
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: RecyclerView.Adapter基类
 */
public class BaseRecyclerAdapter<Holder extends RecyclerHolder, T> extends RecyclerView.Adapter<Holder> {
    /**
     * adapter 数据集
     */
    public List<T> datalist;
    /**
     * Context
     */
    protected Context context;
    /**
     * 用于解析布局
     */
    protected LayoutInflater inflater;

    /**
     * 构造方法
     *
     * @param context
     */
    public BaseRecyclerAdapter(Context context) {
        this(context, new ArrayList<T>());
    }

    /**
     * 构造方法
     *
     * @param context
     * @param datalist
     */
    public BaseRecyclerAdapter(Context context, List<T> datalist) {
        this.context = context;
        this.datalist = datalist;
        inflater = LayoutInflater.from(context);
    }


    /**
     * 创建新View，被LayoutManager所调用
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * 将数据与界面进行绑定的操作
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(Holder holder, int position) {

    }

    /**
     * 获取数据的数量
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datalist != null ? datalist.size() : 0;
    }

    /**
     * 刷新列表
     *
     * @param datalist 新的数据
     */
    public void refresh(List<T> datalist) {
        this.datalist.clear();
        this.datalist = datalist;
        notifyDataSetChanged();
    }

    /**
     * 获取列表某个位置的数据
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return datalist.get(position);
    }

    /**
     * 判断是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return getItemCount() == 0;
    }

    /**
     * 在列表末尾添加数据
     *
     * @param data
     */
    public void append(T data) {
        this.datalist.add(data);
        notifyItemInserted(this.datalist.size() - 1);
    }

    /**
     * 在列表指定位置添加数据
     *
     * @param position
     * @param data
     */
    public void append(int position, T data) {
        this.datalist.add(position, data);
        notifyItemInserted(position);
    }

    /**
     * 添加一组数据
     *
     * @param datalist
     */
    public void append(List<T> datalist) {
        int oldSize = this.datalist.size();
        this.datalist.addAll(datalist);
        notifyItemRangeInserted(oldSize, datalist.size());
    }

    /**
     * 移除一组数据
     *
     * @param item
     * @return
     */
    public T remove(T item) {
        this.datalist.remove(item);
        notifyItemRemoved(this.datalist.size() + 1);
        return item;
    }

    /**
     * 移除某个位置的数据
     *
     * @param position
     * @return
     */
    public T remove(int position) {
        T item = this.datalist.get(position);
        this.datalist.remove(position);
        notifyItemRemoved(position);
        return item;
    }

    /**
     * 清空列表
     */
    public void removeAll() {
        this.datalist.clear();
        notifyDataSetChanged();
    }
}
