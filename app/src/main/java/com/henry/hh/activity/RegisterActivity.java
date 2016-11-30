package com.henry.hh.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.library.activity.TitleActivity;

public class RegisterActivity extends TitleActivity
        implements View.OnClickListener, TextView.OnEditorActionListener {
    private static String charOrNum = "^[A-Za-z0-9]+$";
    //账号
    private EditText mAccount;
    //昵称
    private EditText mNickName;
    //密码
    private EditText mPassword;
    //确认密码
    private EditText mPasswordComform;
    //错误提示－－账号
    private TextView mErrAccount;
    //错误提示－－昵称
    private TextView mErrNick;
    //错误提示－－密码
    private TextView mErrPassword;
    //错误提示－－确认密码
    private TextView mErrPwdComform;
    //注册按钮
    private Button mRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
    }

    /**
     * 初始化控件
     */
    private void initViews() {
        //设置标题
        setTitle(R.string.login_register_account);
        //返回
        showBackwardView(R.string.register_back, true);
        mAccount = getViewById(R.id.et_account_register);
        mNickName = getViewById(R.id.et_nickname_register);
        mPassword = getViewById(R.id.et_pwd_register);
        mPasswordComform = getViewById(R.id.et_pwd_conform_register);
        mPasswordComform.setOnEditorActionListener(this);
        mErrAccount = getViewById(R.id.tv_error_account);
        mErrNick = getViewById(R.id.tv_error_nick);
        mErrPassword = getViewById(R.id.tv_error_password);
        mErrPwdComform = getViewById(R.id.tv_error_password_comform);
        mRegister = getViewById(R.id.btn_register);
        mRegister.setOnClickListener(this);
    }

    /**
     * 执行登录操作
     */
    private void doRegister() {
        clearErrText();
        String account = mAccount.getText().toString();
        String nickName = mNickName.getText().toString();
        String passwd = mPassword.getText().toString();
        String pwdComform = mPasswordComform.getText().toString();
        if (TextUtils.isEmpty(account)) {
            mErrAccount.setVisibility(View.VISIBLE);
            mErrAccount.setText(R.string.register_account_not_null);
        } else if (!account.matches(charOrNum)) {
            mErrAccount.setVisibility(View.VISIBLE);
            mErrAccount.setText(R.string.register_account_only_num_or_char);
        } else if (TextUtils.isEmpty(nickName)) {
            mErrNick.setVisibility(View.VISIBLE);
            mErrNick.setText(R.string.register_nick_not_null);
        } else if (!isPwdSuit(passwd)) {
            mErrPassword.setVisibility(View.VISIBLE);
            mErrPassword.setText(R.string.register_password_length_not_true);
        } else if (TextUtils.isEmpty(pwdComform) || !passwd.equals(pwdComform)) {
            mErrPwdComform.setVisibility(View.VISIBLE);
            mErrPwdComform.setText(R.string.register_password_not_same);
        } else {
            showToast("register");
        }
    }

    /***
     * 判断密码是否适合仅有数字、密码的要求
     *
     * @param password
     * @return
     */
    private boolean isPwdSuit(String password) {
        if (password.length() < 6 || password.length() > 16)
            return false;
        if (!password.matches(charOrNum))
            return false;
        return true;
    }

    /**
     * 清除错误记录
     */
    private void clearErrText() {
        mErrAccount.setVisibility(View.GONE);
        mErrNick.setVisibility(View.GONE);
        mErrPassword.setVisibility(View.GONE);
        mErrPwdComform.setVisibility(View.GONE);
    }

    /**
     * 返回
     *
     * @param backwardView
     */
    @Override
    protected void onBackward(View backwardView) {
        super.onBackward(backwardView);
        finish();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //点击软键盘的ＧＯ
        if (actionId == EditorInfo.IME_ACTION_GO) {
            doRegister();
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if (v == mRegister) {
            doRegister();
        }
        super.onClick(v);
    }


}
