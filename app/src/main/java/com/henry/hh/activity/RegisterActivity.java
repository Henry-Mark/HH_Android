package com.henry.hh.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.library.activity.TitleActivity;

public class RegisterActivity extends TitleActivity {

    private EditText mAccount;
    private EditText mNickName;
    private EditText mPassword;
    private EditText mPasswordComform;
    private TextView mErrAccount;
    private TextView mErrNick;
    private TextView mErrPassword;
    private TextView mErrPwdComform;
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
        setTitle(R.string.register_account);
        //返回
        showBackwardView(R.string.register_back, true);
        mAccount = getViewById(R.id.et_account_register);
        mNickName = getViewById(R.id.et_nickname_register);
        mPassword = getViewById(R.id.et_pwd_register);
        mPasswordComform = getViewById(R.id.et_pwd_conform_register);
        mErrAccount = getViewById(R.id.tv_error_account);
        mErrNick = getViewById(R.id.tv_error_nick);
        mErrPassword = getViewById(R.id.tv_error_password);
        mErrPwdComform = getViewById(R.id.tv_error_password_comform);
        mRegister = getViewById(R.id.btn_register);
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
}
