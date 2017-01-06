package com.henry.hh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.adapter.SearchAdapter;
import com.henry.hh.entity.Friend;
import com.henry.hh.fragment.FriendsListFragment;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.DividerItemDecoration;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.LogUtils;
import com.henry.library.utils.ScreenUtils;
import com.henry.library.utils.ToastUtils;
import com.litesuits.orm.db.assit.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends MyBaseActivity
        implements View.OnClickListener, TextWatcher, View.OnKeyListener,
        OnRecyclerItemClickListener, SearchAdapter.OnClearClickListener {

    private ImageView mBack;
    private EditText mSearch;
    private ImageView mSearchImg;
    private LinearLayout mLayoutSearch;
    private ImageView mClear;
    private TextView mListTitle;
    private RecyclerView mRecyclerView;
    private SearchAdapter searchAdapter;
    private LinearLayoutManager mLayoutManager;
    //是否为添加好友
    private boolean isAddFriend = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        bindView();
        initList();
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        String value = getIntent().getStringExtra(MainActivity.KEY);
        LogUtils.d(TAG, "value=" + value);
        //添加好友
        if (MainActivity.ADDFRIEND.equals(value)) {
            isAddFriend = true;
            mSearch.setHint(R.string.search_hint_add_freind);
        } else {
            isAddFriend = false;
            mSearch.setHint(R.string.search_hint_find_freind);
        }
    }

    /**
     * 绑定控件
     */
    private void bindView() {
        //隐藏标题
        hideTitle();
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
        searchAdapter.addOnClearListener(this);
        searchHistory();
    }

    /**
     * 搜索历史
     */
    private void searchHistory() {
        mListTitle.setText(getString(R.string.history_of_search));
        List<Friend> friends = liteOrm.<Friend>query(new QueryBuilder<Friend>(Friend.class)
                .appendOrderDescBy("searchTimeMillis")
                .where("isSearched=?", 1));
        friends = getFriendInfo(friends);
        LogUtils.d(TAG, "searchHistory friend>>>" + friends);
        searchAdapter.setSearched(false);
        searchAdapter.refresh(friends);
    }

    /**
     * 搜索
     */
    private void search(CharSequence charSequence) {
        mListTitle.setText(getString(R.string.string_contacts));
        List<Friend> friends = getFriendFromOrm();
        List<Friend> friendlist = new ArrayList<>();
        for (Friend friend : friends) {
            if (String.valueOf(friend.getFriendUid()).contains(charSequence)
                    || friend.getRemarkName().contains(charSequence)
                    || friend.getFriendInfo().getAccount().contains(charSequence)) {
                friendlist.add(friend);
            }
        }
        searchAdapter.setSearched(true);
        searchAdapter.refresh(friendlist);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                hideSoftKeyboard();
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
            search(s);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            hideSoftKeyboard();
        }
        return false;
    }


    @Override
    public void onItemClick(View view, List data, int position) {
        ToastUtils.showShort(mContext, "postion=" + position);
        Friend friend = (Friend) data.get(position);
        Intent intent = new Intent(this, ChatActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable(FriendsListFragment.UID, friend);
        intent.putExtras(bundle);
        friend.setIsSearched(1);
        friend.setSearchTimeMillis(System.currentTimeMillis());
        liteOrm.save(friend);
        startActivity(intent);
    }


    @Override
    public void onClearClick(int position, List data) {
        Friend friend = (Friend) data.get(position);
        //清除搜索记录
        friend.setIsSearched(0);
        liteOrm.save(friend);
        searchHistory();
    }
}
