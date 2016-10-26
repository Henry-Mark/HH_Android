package com.henry.hh.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.library.activity.BaseActivity;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;
import com.henry.library.utils.ToastUtils;

public class SearchActivity extends BaseActivity implements View.OnClickListener{

    private ImageView mBack;
    private EditText mSearch;
    private ImageView mSearchImg;
    private LinearLayout mLayoutSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        bindView();
    }

    private void bindView() {
        mLayoutSearch = getViewById(R.id.layout_search);
        mBack = getViewById(R.id.iv_back);
        mSearch = getViewById(R.id.et_search);
        mSearchImg = getViewById(R.id.iv_search);
        mSearch.setOnClickListener(this);
        mBack.setOnClickListener(this);
        ControlsUtils.setWidth(mLayoutSearch, ScreenUtils.getScreenWidth(mContext) * 4 / 5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_search:
                ToastUtils.showShort(mContext,"search");
                break;
            default:
                break;
        }
    }
}
