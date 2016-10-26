package com.henry.hh.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.henry.hh.R;
import com.henry.library.activity.BaseActivity;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;
import com.henry.library.utils.ToastUtils;

public class SearchActivity extends BaseActivity
        implements View.OnClickListener, TextWatcher, View.OnKeyListener {

    private ImageView mBack;
    private EditText mSearch;
    private ImageView mSearchImg;
    private LinearLayout mLayoutSearch;
    private ImageView mClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bindView();
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
        mSearchImg.setOnClickListener(this);
        mBack.setOnClickListener(this);
        mSearch.addTextChangedListener(this);
        mSearch.setOnKeyListener(this);
        mClear.setOnClickListener(this);
        ControlsUtils.setWidth(mLayoutSearch, ScreenUtils.getScreenWidth(mContext) * 4 / 5);
    }

    /**
     * 搜索
     */
    private void search() {
        ToastUtils.showShort(mContext, "search");
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
        } else
            mClear.setVisibility(View.VISIBLE);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // 先隐藏键盘
            ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                    .hideSoftInputFromWindow(this.getCurrentFocus()
                            .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            //进行搜索操作的方法，在该方法中可以加入mEditSearchUser的非空判断
            search();
        }
        return false;
    }
}
