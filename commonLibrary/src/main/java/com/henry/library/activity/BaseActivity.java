package com.henry.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.henry.library.utils.ToastUtils;

/**
 * Date: 16-9-24 下午11:52
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 基类，打印生命周期，以及重写启动方法
 */
public class BaseActivity extends BaseLogActivity {
    protected Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }

    /**
     * 查找View
     *
     * @param id   控件的id
     * @param <VT> View类型
     * @return
     */
    protected <VT extends View> VT getViewById(@IdRes int id) {
        return (VT) findViewById(id);
    }

    /**
     * 重写startActivity方法
     *
     * @param cls
     */
    protected void startActivity(Class<?> cls) {
        startActivity(mContext, cls);
    }

    /**
     * @param packageContext
     * @param cls
     */
    protected void startActivity(Context packageContext, Class<?> cls) {
        startActivity(packageContext, cls, null);
    }

    /**
     * @param cls
     * @param bundle
     */
    protected void startActivity(Class<?> cls, Bundle bundle) {
        startActivity(mContext, cls, bundle);
    }

    /**
     * @param packageContext
     * @param cls
     * @param bundle
     */
    protected void startActivity(Context packageContext, Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(packageContext, cls);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 显示Toast
     *
     * @param text
     */
    protected void showToast(String text) {
        ToastUtils.showShort(mContext, text);
    }

    /**
     * 显示Toast
     *
     * @param ResStr
     */
    protected void showToast(int ResStr) {
        ToastUtils.showShort(mContext, ResStr);
    }

    /**
     * 隐藏软键盘
     */
    protected void hideSoftKeyboard(){
        // 先隐藏键盘
        ((InputMethodManager) getSystemService(INPUT_METHOD_SERVICE))
                .hideSoftInputFromWindow(this.getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }
}
