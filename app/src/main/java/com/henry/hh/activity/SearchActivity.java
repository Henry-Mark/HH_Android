package com.henry.hh.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.adapter.SearchAdapter;
import com.henry.hh.entity.Friend;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.DividerItemDecoration;
import com.henry.library.activity.BaseActivity;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;
import com.henry.library.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity
        implements View.OnClickListener, TextWatcher, View.OnKeyListener,
        OnRecyclerItemClickListener {

    private ImageView mBack;
    private EditText mSearch;
    private ImageView mSearchImg;
    private LinearLayout mLayoutSearch;
    private ImageView mClear;
    private TextView mListTitle;
    private RecyclerView mRecyclerView;
    private SearchAdapter searchAdapter;
    private LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bindView();
        initList();
    }

    /**
     * 绑定控件
     */
    private void bindView() {
        mLayoutSearch = getViewById(R.id.layout_search);
        mBack = getViewById(R.id.iv_back);
        mSearch = getViewById(R.id.et_search);
        mSearchImg = getViewById(R.id.iv_search);
        mClear = getViewById(R.id.iv_clear);
        mListTitle = getViewById(R.id.tv_list_title);
        mRecyclerView = getViewById(R.id.recycler_search);
        mSearchImg.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mSearch.addTextChangedListener(this);
        mSearch.setOnKeyListener(this);
        mClear.setOnClickListener(this);
        ControlsUtils.setWidth(mLayoutSearch, ScreenUtils.getScreenWidth(mContext) * 7 / 10);
    }

    /**
     * 初始化列表
     */
    private void initList() {
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mContext,
                DividerItemDecoration.VERTICAL_LIST));
        //创建并设置Adapter
        searchAdapter = new SearchAdapter(mContext);
        mRecyclerView.setAdapter(searchAdapter);
        searchAdapter.addOnItemClickListener(this);
        searchHistory();
    }

    /**
     * 搜索历史
     */
    private void searchHistory() {
        mListTitle.setText(getString(R.string.history_of_search));
        searchAdapter.setSearched(false);
        searchAdapter.refresh(getDatas(1));
    }

    /**
     * 搜索
     */
    private void search() {
        mListTitle.setText(getString(R.string.string_contacts));
        searchAdapter.setSearched(true);
        searchAdapter.refresh(getDatas(10));
    }

    private List<Friend> getDatas(int num) {
        List<Friend> mList = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Friend friend = new Friend();
            friend.setRemarkName("name" + i);
            friend.setLabel("label" + i);
            friend.setUserId(1000 + i);
            mList.add(friend);
        }

        return mList;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                search();
                break;
            case R.id.iv_clear:
                mSearch.setText("");
                break;
            default:
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(s)) {
            mClear.setVisibility(View.GONE);
            searchHistory();
        } else {
            mClear.setVisibility(View.VISIBLE);
            search();
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            hideSoftKeyboard();
            search();
        }
        return false;
    }

    private void hideSoftKeyboard(){
        // 先隐藏键盘
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    @Override
    public void onItemClick(View view, List data, int position) {
        ToastUtils.showShort(mContext, "postion=" + position);
    }
}
