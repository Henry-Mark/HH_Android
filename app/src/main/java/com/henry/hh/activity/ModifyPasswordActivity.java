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

public class ModifyPasswordActivity extends MyBaseActivity
        implements View.OnClickListener, TextView.OnEditorActionListener{
    /*数字或字母*/
    private static String charOrNum = "^[A-Za-z0-9]+$";
    /*账号*/
    private TextView mAccount;
    /*旧密码*/
    private EditText mPwdOld;
    /*新密码*/
    private EditText mPwdNew;
    /*确认新密码*/
    private EditText mPwdNewConform;
    /*错误提示--旧密码*/
    private TextView mErrPwdOld;
    /*错误提示--新密码*/
    private TextView mErrPwdNew;
    /*错误提示--确认新密码*/
    private TextView mErrPwdNewConform;
    /*修改按钮*/
    private Button mModify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_password);
        initView();
    }

    /**
     * 初始化控件
     */
    private void initView() {
        //设置标题
        setTitle(R.string.modify_string_modify_password);
        //设置返回
        showBackwardView(R.string.register_back,true);
        mAccount = getViewById(R.id.tv_account_modify);
        mPwdOld = getViewById(R.id.et_pwd_old_modify);
        mPwdNew = getViewById(R.id.et_pwd_new_modify);
        mPwdNewConform = getViewById(R.id.et_pwd_new_conform_modify);
        mErrPwdOld = getViewById(R.id.tv_error_password_old_modify);
        mErrPwdNew = getViewById(R.id.tv_error_pwd_new_modify);
        mErrPwdNewConform = getViewById(R.id.tv_error_password_new_comform_modify);
        mPwdNewConform.setOnEditorActionListener(this);
        mModify = getViewById(R.id.btn_modify);
        mModify.setOnClickListener(this);
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
     * 执行修改操作
     */
    private void doModify(){
        //隐藏错误提示
        mErrPwdOld.setVisibility(View.GONE);
        mErrPwdNew.setVisibility(View.GONE);
        mErrPwdNewConform.setVisibility(View.GONE);
        //获取输入的内容
        String account = mAccount.getText().toString();
        String pwdOld = mPwdOld.getText().toString();
        String pwdNew = mPwdNew.getText().toString();
        String pwdNewConform = mPwdNewConform.getText().toString();

        getMyApplication().getUser().setPassword("s");
        if(TextUtils.isEmpty(pwdOld) || !pwdOld.equals(getMyApplication().getUser().getPassword())){
            //密码错误
            mErrPwdOld.setVisibility(View.VISIBLE);
        }else if (!isPwdSuit(pwdNew)){
            //新密码不合法
            mErrPwdNew.setVisibility(View.VISIBLE);
        }else if (TextUtils.isEmpty(pwdNewConform)||!pwdNewConform.equals(pwdNew)){
            //新密码不一致
            mErrPwdNewConform.setVisibility(View.VISIBLE);
        }else {
            showToast("modify");
        }
    }

    @Override
    protected void onBackward(View backwardView) {
        finish();
        super.onBackward(backwardView);
    }

    @Override
    public void onClick(View v) {
        if (v==mModify){
            doModify();
        }
        super.onClick(v);
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //点击软键盘的ＧＯ
        if (actionId == EditorInfo.IME_ACTION_GO) {
           doModify();
        }
        return false;
    }
}
