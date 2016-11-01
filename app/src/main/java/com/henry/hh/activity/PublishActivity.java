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
import com.henry.hh.dialog.ButtonMenuFragment;
import com.henry.hh.entity.LivingCircleDynamic;
import com.henry.library.activity.TitleActivity;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;
import com.henry.library.utils.TimeUtils;

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
    private MyApplication app;
    private TextView mLocation;

    private int back_code = LivingCircleDynamic.BACK_TYPE_COLOR;
    private int textColor = LivingCircleDynamic.TEXTCOLOR_BLACK;
    private int backColor = LivingCircleDynamic.BACK_COLOR_WHITE;

    public int CODE = 100;
    public static final String BEAN = "bean";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
        //全局变量
        app = (MyApplication) getApplication();
        count_left = TEXT_MAX_LENGTH;
        initTitle();
        bindView();

    }

    /**
     * 初始化标题栏
     */
    private void initTitle() {
        setTitle("我的心情");
        showBackwardView("返回", true);
        showForwardView("发布", true);
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
            setInputBackground(LivingCircleDynamic.BACK_COLOR_BLUE);
        } else if (v == view_green) {
            setInputBackground(LivingCircleDynamic.BACK_COLOR_GREEN);
        } else if (v == view_orange) {
            setInputBackground(LivingCircleDynamic.BACK_COLOR_ORANGE);
        } else if (v == view_purple) {
            setInputBackground(LivingCircleDynamic.BACK_COLOR_PURPLE);
        } else if (v == view_red) {
            setInputBackground(LivingCircleDynamic.BACK_COLOR_RED);
        } else if (v == view_white) {
            setInputBackground(LivingCircleDynamic.BACK_COLOR_WHITE);
        } else if (v == mPic) {
            ButtonMenuFragment buttonMenuFragment = new ButtonMenuFragment();
            buttonMenuFragment.show(getFragmentManager(), TAG);
        } else if (v == view_text_black) {
            setInputTextColor(LivingCircleDynamic.TEXTCOLOR_BLACK);
        } else if (v == view_text_main) {
            setInputTextColor(LivingCircleDynamic.TEXTCOLOR_MAIN);
        } else if (v == view_text_white) {
            setInputTextColor(LivingCircleDynamic.TEXTCOLOR_WHITE);
        } else if (v == mlayoutLocation) {
            Intent intent_location = new Intent(this, LocationActivity.class);
            startActivityForResult(intent_location, CODE);
        }
    }

    /**
     * 返回键
     *
     * @param backwardView
     */
    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    /**
     * 提交
     *
     * @param forwardView
     */
    @Override
    protected void onForward(View forwardView) {
        super.onForward(forwardView);
        //数据是使用Intent返回
        Intent intent = new Intent();
        //把返回数据存入Intent
        intent.putExtra(BEAN, getData());

        //设置返回数据
        setResult(RESULT_OK, intent);
        finish();
    }

    /**
     * 获取需要发布的数据
     *
     * @return
     */
    private LivingCircleDynamic getData() {
        LivingCircleDynamic dynamic = new LivingCircleDynamic();
        dynamic.setBackType(back_code);
        dynamic.setLocaion(mLocation.getText().toString());
        dynamic.setContent(mMoodInput.getText().toString());
        dynamic.setBack_color(backColor);
        dynamic.setTextcolor(textColor);
        dynamic.setDeliveryTimeMillis(TimeUtils.getSysCurrentMillis());
        return dynamic;
    }

    /**
     * 设置输入框背景颜色
     *
     * @param color
     */
    private void setInputBackground(int color) {
        mMoodInput.setBackgroundColor(app.useColor(color));
        backColor = color;
    }

    /**
     * 设置输入文字颜色
     *
     * @param colorCode
     */
    private void setInputTextColor(int colorCode) {
        mMoodInput.setTextColor(app.useColor(colorCode));
        textColor = colorCode;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getExtras().getString(LocationActivity.PLACE);//得到新Activity 关闭后返回的数据
        mLocation.setText(TextUtils.isEmpty(result) ? "" : result);
    }
}
