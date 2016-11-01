package com.henry.hh.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.entity.GlobalData;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;
import com.henry.library.utils.ToastUtils;

import io.github.rockerhieu.emojicon.EmojiconEditText;

public class PublishActivity extends TitleActivity implements TextWatcher, View.OnClickListener {

    private int TEXT_MAX_LENGTH = 50;
    private EmojiconEditText mMoodInput;
    private View view_white, view_orange, view_green, view_red, view_purple, view_blue;
    private View view_text_white, view_text_black, view_text_main;
    private ImageView mPic;
    private TextView mTextLeft;
    private LinearLayout mlayoutLocation;
    private int count_left;
    private GlobalData app;
    private TextView mLocation;

    public int CODE = 100;

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
     * 绑定控件,进行初始化设置
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
        view_text_white = getViewById(R.id.view_textcolor_white);
        view_text_black = getViewById(R.id.view_textcolor_black);
        view_text_main = getViewById(R.id.view_textcolor_main);
        mlayoutLocation = getViewById(R.id.layout_location);
        mLocation = getViewById(R.id.tv_location);
        //设置控件大小
        int width = (int) (ScreenUtils.getScreenWidth(mContext) / 4 * 0.5);
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
        //设置监听事件
        mMoodInput.addTextChangedListener(this);
        view_white.setOnClickListener(this);
        view_blue.setOnClickListener(this);
        view_purple.setOnClickListener(this);
        view_red.setOnClickListener(this);
        view_green.setOnClickListener(this);
        view_orange.setOnClickListener(this);
        mPic.setOnClickListener(this);
        view_text_main.setOnClickListener(this);
        view_text_black.setOnClickListener(this);
        view_text_white.setOnClickListener(this);
        mlayoutLocation.setOnClickListener(this);
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

        } else if (v == view_text_black) {
            setInputTextColor(app.COLOR_BLACK);
        } else if (v == view_text_main) {
            setInputTextColor(app.COLOR_MAIN);
        } else if (v == view_text_white) {
            setInputTextColor(app.COLOR_WHITE);
        } else if (v == mlayoutLocation) {
            Intent intent_location = new Intent(this, LocationActivity.class);
            startActivityForResult(intent_location, CODE);
        }
    }

    /**
     * 设置输入框背景颜色
     *
     * @param color
     */
    private void setInputBackground(int color) {
        mMoodInput.setBackgroundColor(color);
    }

    /**
     * 设置输入文字颜色
     *
     * @param color
     */
    private void setInputTextColor(int color) {
        mMoodInput.setTextColor(color);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getExtras().getString(LocationActivity.PLACE);//得到新Activity 关闭后返回的数据
        mLocation.setText(TextUtils.isEmpty(result) ? "保密" : result);
    }
}
