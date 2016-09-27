package com.henry.library.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Date: 16-9-24 下午11:52
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 基类，打印生命周期，以及重写启动方法
 */
public class BaseActivity extends AppCompatActivity {

    protected String TAG = "BaseActivity";
    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;

        // 获取当前类名
        TAG = getClass().getName();
        Log.i(TAG, "onCreate...");


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart...");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart...");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume...");

    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause...");

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop...");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy...");

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

}
