package com.henry.library.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.henry.hh.library.R;

/**
 * 自定义标题栏
 */
public class TitleActivity extends BaseActivity implements View.OnClickListener {
    private TextView mTitleTextView;
    private TextView mBackwardbButton;
    private TextView mForwardButton;
    private FrameLayout mContentLayout;
    private LinearLayout mBackwardLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupViews();
    }

    /**
     * 加载 activity_title 布局 ，并获取标题及两侧按钮
     */
    private void setupViews() {
        super.setContentView(R.layout.activity_title);
        mTitleTextView = (TextView) findViewById(R.id.text_title);
        mContentLayout = (FrameLayout) findViewById(R.id.layout_content);
        mBackwardbButton = (TextView) findViewById(R.id.text_backward);
        mForwardButton = (TextView) findViewById(R.id.text_forward);
        mBackwardLayout = (LinearLayout)findViewById(R.id.ll_backward);
    }

    /**
     * 是否显示返回按钮
     * @param string  文字
     * @param show  true则显示
     */
    protected void showBackwardView(String string, boolean show) {
        if (mBackwardbButton != null) {
            if (show) {
                mBackwardbButton.setText(string);
                mBackwardLayout.setVisibility(View.VISIBLE);
            } else {
                mBackwardLayout.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    /**
     * 提供是否显示提交按钮
     * @param forward 文字
     * @param show true则显示
     */
    protected void showForwardView(String forward, boolean show) {
        if (mForwardButton != null) {
            if (show) {
                mForwardButton.setVisibility(View.VISIBLE);
                mForwardButton.setText(forward);
            } else {
                mForwardButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    /**
     * 是否显示返回按钮
     * @param string 文字
     * @param show true则显示
     */
    protected void showBackwardView(int string, boolean show) {
        if (mBackwardbButton != null) {
            if (show) {
                mBackwardbButton.setText(string);
                mBackwardLayout.setVisibility(View.VISIBLE);
            } else {
                mBackwardLayout.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    /**
     * 提供是否显示提交按钮
     * @param forwardResId  文字
     * @param show  true则显示
     */
    protected void showForwardView(int forwardResId, boolean show) {
        if (mForwardButton != null) {
            if (show) {
                mForwardButton.setVisibility(View.VISIBLE);
                mForwardButton.setText(forwardResId);
            } else {
                mForwardButton.setVisibility(View.INVISIBLE);
            }
        } // else ignored
    }

    /**
     * 返回按钮点击后触发
     * @param backwardView
     */
    protected void onBackward(View backwardView) {
        Toast.makeText(this, "点击返回，可在此处调用finish()", Toast.LENGTH_LONG).show();
        //finish();
    }

    /**
     * 提交按钮点击后触发
     * @param forwardView
     */
    protected void onForward(View forwardView) {
        Toast.makeText(this, "点击提交", Toast.LENGTH_LONG).show();
    }


    //设置标题内容
    @Override
    public void setTitle(int titleId) {
        mTitleTextView.setText(titleId);
    }

    //设置标题内容
    @Override
    public void setTitle(CharSequence title) {
        mTitleTextView.setText(title);
    }

    //设置标题文字颜色
    @Override
    public void setTitleColor(int textColor) {
        mTitleTextView.setTextColor(textColor);
    }


    //取出FrameLayout并调用父类removeAllViews()方法
    @Override
    public void setContentView(int layoutResID) {
        mContentLayout.removeAllViews();
        View.inflate(this, layoutResID, mContentLayout);
        onContentChanged();
    }

    @Override
    public void setContentView(View view) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view);
        onContentChanged();
    }

    /* (non-Javadoc)
     * @see android.app.Activity#setContentView(android.view.View, android.view.ViewGroup.LayoutParams)
     */
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mContentLayout.removeAllViews();
        mContentLayout.addView(view, params);
        onContentChanged();
    }


    /* (non-Javadoc)
     * @see android.view.View.OnClickListener#onClick(android.view.View)
     * 按钮点击调用的方法
     */
    @Override
    public void onClick(View v) {

        int i = v.getId();
        if (i == R.id.ll_backward) {
            onBackward(v);

        } else if (i == R.id.text_forward) {
            onForward(v);

        } else {
        }
    }

}
