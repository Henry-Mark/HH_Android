package com.henry.hh.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;

import io.github.rockerhieu.emojicon.EmojiconEditText;

public class PublishActivity extends TitleActivity implements TextWatcher {

    private int TEXT_MAX_LENGTH = 50;
    private EmojiconEditText mMoodInput;
    private View view_white, view_orange, view_green, view_red, view_purple, view_blue;
    private ImageView mPic;
    private TextView mTextLeft;
    private int count_left;

//    public int COLOR_WHITE = ContextCompat.getColor(this, R.color.white);
//    public int COLOR_GREEN = ContextCompat.getColor(mContext, R.color.green);
//    public int COLOR_RED = ContextCompat.getColor(mContext, R.color.red);
//    public int COLOR_ORANGE = ContextCompat.getColor(mContext, R.color.orange);
//    public int COLOR_PURPLE = ContextCompat.getColor(mContext, R.color.purple);
//    public int COLOR_BLUE = ContextCompat.getColor(mContext, R.color.blue);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        count_left = TEXT_MAX_LENGTH;
        bindView();
        int COLOR_WHITE = ContextCompat.getColor(this, R.color.white);
        int COLOR_GREEN = ContextCompat.getColor(mContext, R.color.green);
        int COLOR_RED = ContextCompat.getColor(mContext, R.color.red);
        int COLOR_ORANGE = ContextCompat.getColor(mContext, R.color.orange);
        int COLOR_PURPLE = ContextCompat.getColor(mContext, R.color.purple);
        int COLOR_BLUE = ContextCompat.getColor(mContext, R.color.blue);

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
}
