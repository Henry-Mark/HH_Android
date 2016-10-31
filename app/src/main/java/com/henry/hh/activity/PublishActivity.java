package com.henry.hh.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.GlobalData;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;

import io.github.rockerhieu.emojicon.EmojiconEditText;

public class PublishActivity extends TitleActivity implements TextWatcher, View.OnClickListener {

    private int TEXT_MAX_LENGTH = 50;
    private EmojiconEditText mMoodInput;
    private View view_white, view_orange, view_green, view_red, view_purple, view_blue;
    private ImageView mPic;
    private TextView mTextLeft;
    private int count_left;
    private GlobalData app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        //全局变量
        app = (GlobalData) getApplication();
        count_left = TEXT_MAX_LENGTH;
        bindView();

    }


    /**
     * 绑定控件
     */
    private void bindView() {
        mMoodInput = getViewById(R.id.emojiconEditText);
        mTextLeft = getViewById(R.id.tv_count_left);
        view_white = getViewById(R.id.view_color_white);
        view_orange = getViewById(R.id.view_color_orange);
        view_green = getViewById(R.id.view_color_green);
        view_red = getViewById(R.id.view_color_red);
        view_purple = getViewById(R.id.view_color_purple);
        view_blue = getViewById(R.id.view_color_blue_light);
        mPic = getViewById(R.id.iv_photo);
        int width = (int) (ScreenUtils.getScreenWidth(mContext) / 4 * 0.6);
        ControlsUtils.setSize(view_white, width, width);
        ControlsUtils.setSize(view_orange, width, width);
        ControlsUtils.setSize(view_green, width, width);
        ControlsUtils.setSize(view_red, width, width);
        ControlsUtils.setSize(view_purple, width, width);
        ControlsUtils.setSize(view_blue, width, width);
        ControlsUtils.setSize(mPic, width, width);

        ControlsUtils.setHeight(getViewById(R.id.rl_inputmood),
                (int) (ScreenUtils.getScreenWidth(mContext) * 0.6));
        mTextLeft.setText("还可以输入" + count_left + "个字");
        mMoodInput.addTextChangedListener(this);
        view_white.setOnClickListener(this);
        view_blue.setOnClickListener(this);
        view_purple.setOnClickListener(this);
        view_red.setOnClickListener(this);
        view_green.setOnClickListener(this);
        view_orange.setOnClickListener(this);
        mPic.setOnClickListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mTextLeft.setText("还可以输入" + count_left + "个字");
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        int length = TextUtils.isEmpty(s) ? 0 : s.length();
        if (length <= TEXT_MAX_LENGTH)
            mTextLeft.setText("还可以输入" + (count_left - length) + "个字");
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v == view_blue) {
            setInputBackground(app.COLOR_BLUE);
        } else if (v == view_green) {
            setInputBackground(app.COLOR_GREEN);
        } else if (v == view_orange) {
            setInputBackground(app.COLOR_ORANGE);
        } else if (v == view_purple) {
            setInputBackground(app.COLOR_PURPLE);
        } else if (v == view_red) {
            setInputBackground(app.COLOR_RED);
        } else if (v == view_white) {
            setInputBackground(app.COLOR_WHITE);
        } else if (v == mPic) {

        }
    }

    /**
     * 设置输入框背景颜色
     * @param color
     */
    private void setInputBackground(int color){
        mMoodInput.setBackgroundColor(color);
    }
}
