package com.henry.hh.widget;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.henry.hh.R;
import com.henry.hh.SoftKeyboardStateHelper;
import com.henry.hh.adapter.FaceCategroyAdapter;
import com.henry.hh.interfaces.OnFaceListener;
import com.henry.hh.interfaces.OnOperationListener;
import com.henry.hh.interfaces.OnSoftKeyboardStateListener;

import java.util.List;

/**
 * Date: 16-10-15 下午8:50
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:聊天输入控件主界面
 */
public class ChatKeyboard extends RelativeLayout
        implements View.OnClickListener, OnSoftKeyboardStateListener, TextWatcher {


    public static final int LAYOUT_TYPE_HIDE = 0;
    public static final int LAYOUT_TYPE_FACE = 1;
    public static final int LAYOUT_TYPE_MORE = 2;

    /**
     * 最上层输入框
     */
    private EditText mEtMsg;
    private CheckBox mBtnFace;
    private CheckBox mBtnMore;
    private Button mBtnSend;

    /**
     * 表情
     */
    private ViewPager mPagerFaceCagetory;
    private RelativeLayout mRlFace;

    private Context context;
    //表情所在布局类型，默认隐藏
    private int layoutType = LAYOUT_TYPE_HIDE;
    //软键盘相关辅助
    private SoftKeyboardStateHelper mKeyboardHelper;
    //表情栏顶部按钮的监听
    private OnOperationListener listener;
    //表情监听
    private OnFaceListener faceListener;
    //点击表情按钮时的适配器
    private FaceCategroyAdapter adapter;


    public ChatKeyboard(Context context) {
        super(context);
        init(context);
    }

    public ChatKeyboard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ChatKeyboard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        initSoftKeyboardHelper();
        this.bindView();
    }

    /**
     * 初始化布局
     *
     * @param context
     */
    private void init(Context context) {
        this.context = context;
        View root = View.inflate(context, R.layout.chat_tool_box, null);
        this.addView(root);
    }

    /**
     * 初始化软键盘辅助工具
     */
    private void initSoftKeyboardHelper() {
        mKeyboardHelper = new SoftKeyboardStateHelper(((Activity) getContext())
                .getWindow().getDecorView());
        mKeyboardHelper.addSoftKeyboardStateListener(this);
    }

    /**
     * 绑定控件
     */
    private void bindView() {
        mEtMsg = (EditText) findViewById(R.id.toolbox_et_message);
        mBtnSend = (Button) findViewById(R.id.toolbox_btn_send);
        mBtnFace = (CheckBox) findViewById(R.id.toolbox_btn_face);
        mBtnMore = (CheckBox) findViewById(R.id.toolbox_btn_more);
        mRlFace = (RelativeLayout) findViewById(R.id.toolbox_layout_face);
        mPagerFaceCagetory = (ViewPager) findViewById(R.id.toolbox_pagers_face);

        adapter = new FaceCategroyAdapter(((FragmentActivity) getContext())
                .getSupportFragmentManager(), LAYOUT_TYPE_FACE);

        mBtnSend.setOnClickListener(this);
        mEtMsg.setOnClickListener(this);
        mBtnFace.setOnClickListener(this);
        mBtnMore.setOnClickListener(this);
        mEtMsg.addTextChangedListener(this);

        mBtnSend.setEnabled(false);
        mEtMsg.requestFocus();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //发送
            case R.id.toolbox_btn_send:
                if (listener != null) {
                    String content = mEtMsg.getText().toString();
                    listener.send(content);
                    mEtMsg.setText(null);
                }
                break;
            //输入框
            case R.id.toolbox_et_message:
                hideLayout();
                break;
            //表情
            case R.id.toolbox_btn_face:
                setLayout(LAYOUT_TYPE_FACE);
                break;
            //更多
            case R.id.toolbox_btn_more:
                setLayout(LAYOUT_TYPE_MORE);
                break;
            default:
                break;
        }
    }

    /**
     * 设置布局
     *
     * @param which
     */
    private void setLayout(int which) {
        if (isShow() && which == layoutType) {
            hideLayout();
            showKeyboard(context);
        } else {
            changeLayout(which);
            showLayout();
            mBtnFace.setChecked(layoutType == LAYOUT_TYPE_FACE);
            mBtnMore.setChecked(layoutType == LAYOUT_TYPE_MORE);
        }
    }

    /**
     * 改变布局
     *
     * @param mode
     */
    private void changeLayout(int mode) {
        adapter = new FaceCategroyAdapter(((FragmentActivity) getContext())
                .getSupportFragmentManager(), mode);
        adapter.setOnOperationListener(listener);
        layoutType = mode;
//        setFaceData(mFaceData);
    }


    @Override
    public void onSoftKeyboardOpened(int keyboardHeightInPx) {
        hideLayout();
    }

    @Override
    public void onSoftKeyboardClosed() {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (TextUtils.isEmpty(s)) {
            mBtnSend.setEnabled(false);
        } else {
            mBtnSend.setEnabled(true);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }


    /***********************
     * 可选调用的方法 start
     ******************************/


    public void setFaceData(List<String> faceData) {
//        mFaceData = faceData;
        adapter.refresh(faceData);
        mPagerFaceCagetory.setAdapter(adapter);
//        mFaceTabs.setViewPager(mPagerFaceCagetory);
//        if (layoutType == LAYOUT_TYPE_MORE) {
//            mFaceTabs.setVisibility(GONE);
//        } else {
//            //加1是表示第一个分类为默认的emoji表情分类，这个分类是固定不可更改的
//            if (faceData.size() + 1 < 2) {
//                mFaceTabs.setVisibility(GONE);
//            } else {
//                mFaceTabs.setVisibility(VISIBLE);
//            }
//        }
    }

    /**
     * 隐藏软键盘
     */
    public void hideKeyboard(Context context) {
        Activity activity = (Activity) context;
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm.isActive() && activity.getCurrentFocus() != null) {
                imm.hideSoftInputFromWindow(activity.getCurrentFocus()
                        .getWindowToken(), 0);
            }
        }
    }

    /**
     * 显示软键盘
     */
    public static void showKeyboard(Context context) {
        Activity activity = (Activity) context;
        if (activity != null) {
            InputMethodManager imm = (InputMethodManager) activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInputFromInputMethod(activity.getCurrentFocus()
                    .getWindowToken(), 0);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


    /**
     * 是否正在显示表情所在的自定义布局
     *
     * @return
     */
    public boolean isShow() {
        return mRlFace.getVisibility() == VISIBLE;
    }

    /**
     * 隐藏表情所在的自定义布局
     */
    public void hideLayout() {

        mRlFace.setVisibility(View.GONE);
        if (mBtnFace.isChecked()) {
            mBtnFace.setChecked(false);
        }
        if (mBtnMore.isChecked()) {
            mBtnMore.setChecked(false);
        }
    }

    /**
     * 显示表情所在的自定义布局
     */
    public void showLayout() {
        hideKeyboard(this.context);
        // 延迟一会，让键盘先隐藏再显示表情键盘，否则会有一瞬间表情键盘和软键盘同时显示
        postDelayed(new Runnable() {
            @Override
            public void run() {
                mRlFace.setVisibility(View.VISIBLE);
                if (faceListener != null) {
                    faceListener.onShowFace();
                }
            }
        }, 50);
    }

    /**
     * 获取输入控件
     *
     * @return
     */
    public EditText getEditTextBox() {
        return mEtMsg;
    }

    /**
     * 获取表情栏顶部按钮的监听器
     *
     * @return
     */
    public OnOperationListener getOnOperationListener() {
        return listener;
    }

    /**
     * 设置表情栏顶部按钮的监听器
     *
     * @param onOperationListener
     */
    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
//        adapter.setOnOperationListener(onOperationListener);
    }

    /**
     * 设置表情监听器
     *
     * @param faceListener
     */
    public void setOnFaceListener(OnFaceListener faceListener) {
        this.faceListener = faceListener;
    }


    /*********************** 可选调用的方法 end ******************************/
}
