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

import com.google.gson.reflect.TypeToken;
import com.henry.hh.R;
import com.henry.hh.adapter.SearchAdapter;
import com.henry.hh.constants.Condtsnts_URL;
import com.henry.hh.entity.Friend;
import com.henry.hh.entity.RequestMsg;
import com.henry.hh.entity.User;
import com.henry.hh.fragment.FriendsListFragment;
import com.henry.hh.interfaces.OnRecyclerItemClickListener;
import com.henry.library.View.DividerItemDecoration;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.LogUtils;
import com.henry.library.utils.ScreenUtils;
import com.henry.library.utils.ToastUtils;
import com.litesuits.orm.db.assit.QueryBuilder;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends MyBaseActivity
        implements View.OnClickListener, TextWatcher, View.OnKeyListener,
        OnRecyclerItemClickListener, SearchAdapter.OnClearClickListener {

    public static final String INFO = "info";

    private ImageView mBack;
    private EditText mSearch;
    private ImageView mSearchImg;
    private LinearLayout mLayoutSearch;
    private ImageView mClear;
    private TextView mListTitle;
    private RecyclerView mRecyclerView;
    private SearchAdapter searchAdapter;
    private LinearLayoutManager mLayoutManager;
    //搜索列表所在布局
    private LinearLayout mLLSearchList;
    //搜索内容所在布局
    private LinearLayout mLLSearchContent;
    //搜索内容
    private TextView mSearchContent;
    //用户不存在布局
    private LinearLayout mNotExist;
    //是否为添加好友
    private boolean isAddFriend = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        bindView();
        initData();
        initList();
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
            mLLSearchList.setVisibility(View.GONE);
        } else {
            isAddFriend = false;
            mSearch.setHint(R.string.search_hint_find_freind);
            mLLSearchList.setVisibility(View.VISIBLE);
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
        mLLSearchList = getViewById(R.id.ll_search_list);
        mLLSearchContent = getViewById(R.id.ll_search_content);
        mSearchContent = getViewById(R.id.tv_content_search);
        mNotExist = getViewById(R.id.ll_not_exist);
        mSearchImg.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mSearch.addTextChangedListener(this);
        mSearch.setOnKeyListener(this);
        mClear.setOnClickListener(this);
        mLLSearchContent.setOnClickListener(this);
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


    /**
     * 查看用户
     *
     * @param content
     */
    private void queryUser(String content) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Msg", content);
        client.get(Condtsnts_URL.QUERY_USER, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result = new String(bytes);
                LogUtils.d(TAG, "QUERY_UERY result>>" + result);
                RequestMsg msg = gson.fromJson(result, new TypeToken<RequestMsg<User>>() {
                }.getType());
                if (msg.getCode() == 1) {  //用户存在
                    User user = (User) msg.getData();
                    cancelProgressDialog();
                    //跳转到查看详情页面
                    Intent intent = new Intent(mContext, UserInfoActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(INFO, user);
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else if (msg.getCode() == 2) {    //用户不存在
                    mNotExist.setVisibility(View.VISIBLE);
                    mLLSearchContent.setVisibility(View.GONE);
                    cancelProgressDialog();
                } else {
                    LogUtils.e(TAG, "query user > " + msg.getData());
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                LogUtils.e(TAG, "fail...." + bytes);
            }
        });
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
            //搜索内容，点击效果，新的好友信息
            case R.id.ll_search_content:
                showProgressDialog(R.string.looking_for_contacts);
                queryUser(mSearch.getText().toString());
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
        if (isAddFriend) {
            mNotExist.setVisibility(View.GONE);
            if (TextUtils.isEmpty(s)) {
                mLLSearchContent.setVisibility(View.GONE);
            } else {
                mLLSearchContent.setVisibility(View.VISIBLE);
                mSearchContent.setText(s);
            }
        } else {
            if (TextUtils.isEmpty(s)) {
                mClear.setVisibility(View.GONE);
                searchHistory();
            } else {
                mClear.setVisibility(View.VISIBLE);
                search(s);
            }
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
