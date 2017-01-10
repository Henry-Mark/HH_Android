package com.henry.hh.activity;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.henry.hh.R;
import com.henry.hh.constants.Condtsnts_URL;
import com.henry.hh.entity.RequestMsg;
import com.henry.hh.entity.User;
import com.henry.hh.widget.CheckView;
import com.henry.library.View.CircleImageView;
import com.henry.library.activity.BaseActivity;
import com.henry.library.utils.DensityUtil;
import com.henry.library.utils.LogUtils;
import com.henry.library.utils.ScreenUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

public class LoginActivity extends BaseActivity
        implements View.OnClickListener, TextView.OnEditorActionListener {
    //账号标识
    private static int FLAG_ACCOUNT = 10;
    //密码标识
    private static int FLAG_PASSWORD = 11;
    /**
     * 头像
     */
    private CircleImageView mAvatar;
    /**
     * 账号
     */
    private EditText mAccount;
    /**
     * 密码栏目
     */
    private LinearLayout mCodeLL;
    /**
     * 密码
     */
    private EditText mPasswd;
    /**
     * 验证码控件
     */
//    private CheckView mCodeCV;
    /**
     * EditText，输入验证码
     */
//    private EditText mCodeEt;
    /**
     * 登录按钮
     */
    private Button mLogin;

    /**
     * 忘记密码
     */
    private TextView mForgetPWD;
    /**
     * 注册账号
     */
    private TextView mRegister;
    /**
     * 清除账号图标
     */
    private ImageView mClearAccount;
    /**
     * 清除密码图标
     */
    private ImageView mClearPwd;
    /**
     * 错误提示－－账号
     */
    private TextView mErrAccount;
    /**
     * 错误提示－－密码
     */
    private TextView mErrPwd;
//    /**
//     * 错误提示－－验证码
//     */
//    private TextView mErrCode;
    /**
     * 错误提示顶部布局
     */
    private LinearLayout mErrlogLL;
    /**
     * 错误提示--顶部文字
     */
    private TextView mErrlog;

//    private String code;  //获取每次更新的验证码，可用于判断用户输入是否正确
//    private boolean isCodeShow = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initWidget();


    }

    /**
     * 刷新验证码
     */
//    private void refreshCode() {
//        int width = ScreenUtils.getScreenWidth(mContext) * 4 / 15;
//        int height = DensityUtil.dip2px(mContext, 40);
//        code = mCodeCV.getValidataAndSetImage(width, height);
//    }

    /**
     * 初始化控件
     */
    private void initWidget() {
        mAvatar = getViewById(R.id.civ_avatar);
        mAccount = getViewById(R.id.et_account);
        mAccount.addTextChangedListener(new EditTextWatcher(FLAG_ACCOUNT));
//        mCodeLL = getViewById(R.id.ll_code);
        mPasswd = getViewById(R.id.et_password);
        mPasswd.setOnEditorActionListener(this);
        mPasswd.addTextChangedListener(new EditTextWatcher(FLAG_PASSWORD));
//        mCodeCV = getViewById(R.id.cv_code);
//        mCodeCV.setOnClickListener(this);
//        mCodeEt = getViewById(R.id.et_code);
//        mCodeEt.setOnEditorActionListener(this);
        mLogin = getViewById(R.id.btn_login);
        mLogin.setOnClickListener(this);
        mForgetPWD = getViewById(R.id.tv_forget_pwd);
        mForgetPWD.setOnClickListener(this);
        mRegister = getViewById(R.id.tv_register_account);
        mRegister.setOnClickListener(this);
        mClearAccount = getViewById(R.id.iv_cha_account);
        mClearAccount.setOnClickListener(this);
        mClearPwd = getViewById(R.id.iv_cha_pwd);
        mClearPwd.setOnClickListener(this);
        mErrAccount = getViewById(R.id.tv_error_account);
        mErrPwd = getViewById(R.id.tv_error_password);
//        mErrCode = getViewById(R.id.tv_error_code);
        mErrlogLL = getViewById(R.id.ll_errlog);
        mErrlog = getViewById(R.id.tv_errlog);

//        refreshCode();
    }

    /**
     * 检验验证码
     *
     * @return
     */
//    private boolean checkCode() {
//        String codeInput = mCodeEt.getText().toString();
//        if (!TextUtils.isEmpty(code) && !TextUtils.isEmpty(codeInput) && code.equalsIgnoreCase(codeInput)) {
//            return true;
//        }
//        return false;
//    }

    /**
     * 本地验证登录条件
     *
     * @return
     */
    private boolean checkLoginCondition() {
        clearErrText();
//        if (!isCodeShow) {
//            mCodeLL.setVisibility(View.VISIBLE);
//            isCodeShow = true;
//            mErrCode.setVisibility(View.VISIBLE);
//            mErrCode.setText(R.string.login_err_input_code);
//            return false;
//        }
        if (TextUtils.isEmpty(mAccount.getText().toString())) {
            mErrAccount.setVisibility(View.VISIBLE);
            mErrAccount.setText(R.string.login_err_account_not_null);
            return false;
        } else if (TextUtils.isEmpty(mPasswd.getText().toString())) {
            mErrPwd.setVisibility(View.VISIBLE);
            mErrPwd.setText(R.string.login_err_password_not_null);
            return false;
        } else {
            return true;
        }

//        else if (checkCode()) {
//            return true;
//        } else {
//            mErrCode.setVisibility(View.VISIBLE);
//            mErrCode.setText(R.string.login_err_code_wrong);
//            return false;
//        }
    }

    /**
     * 清除错误提示
     */
    private void clearErrText() {
//        mErrCode.setVisibility(View.GONE);
        mErrAccount.setVisibility(View.GONE);
        mErrPwd.setVisibility(View.GONE);
    }

    /**
     * 执行登录事件
     */
    private void dologin() {
        showProgressDialog(R.string.loginning);
        if (checkLoginCondition()) {
            User user = new User();
            user.setAccount(mAccount.getText().toString());
            user.setPassword(mPasswd.getText().toString());

            requestLoginMsg(gson.toJson(user));

        }
    }

    private long firstClickMillis = System.currentTimeMillis();

    /**
     * 点击事件处理
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v == mLogin) {
            //600ms内不能再次点击
            long secondClickMillis = System.currentTimeMillis();
            if (secondClickMillis - firstClickMillis >= 600) {
                dologin();
                firstClickMillis = secondClickMillis;
            }

        } else if (v == mForgetPWD) {
            startActivity(FindPasswordActivity.class);
        } else if (v == mRegister) {
            startActivity(RegisterActivity.class);
        } else if (v == mClearAccount) {
            mAccount.setText("");
        } else if (v == mClearPwd) {
            mPasswd.setText("");
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //点击软键盘的ＧＯ
        if (actionId == EditorInfo.IME_ACTION_GO) {
            dologin();
        }
        return false;
    }

    /**
     * EditText改变监听
     */
    class EditTextWatcher implements TextWatcher {
        private int flag;

        EditTextWatcher(int flag) {
            this.flag = flag;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (flag == FLAG_PASSWORD) {
                if (!TextUtils.isEmpty(s) && s.length() > 0)
                    mClearPwd.setVisibility(View.VISIBLE);
                else
                    mClearPwd.setVisibility(View.GONE);
            } else if (flag == FLAG_ACCOUNT) {
                if (!TextUtils.isEmpty(s) && s.length() > 0)
                    mClearAccount.setVisibility(View.VISIBLE);
                else
                    mClearAccount.setVisibility(View.GONE);
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }

    /**
     * 请求登录
     *
     * @param value
     */
    private void requestLoginMsg(String value) {
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("loginMsg", value);
        client.post(Condtsnts_URL.LOGIN, params, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                String result = new String(bytes);
                LogUtils.d(TAG, "login result=" + result);
                RequestMsg msg = gson.fromJson(result, new TypeToken<RequestMsg<User>>() {
                }.getType());
                User user = (User) msg.getData();
                if (msg.getCode() == 1) {
                    //设置为全局变量
                    ((MyApplication) getApplication()).setUser(user);
                    cancelProgressDialog();
                    startActivity(MainActivity.class);
                    finish();
                } else {
                    cancelProgressDialog();
                    showErrlog(user.getMessage());
                }
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
            }
        });
    }

    /**
     * 显示顶部错误信息
     *
     * @param log
     */
    private void showErrlog(final String log) {
        //显示错误信息
        mErrlogLL.setVisibility(View.VISIBLE);
        mErrlog.setText(log);
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mErrlogLL.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }
}
