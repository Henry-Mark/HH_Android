package com.henry.library.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Date: 2016/10/13. 11:56
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: Fragment打印生命周期基类
 */
public class BaseLogFragment extends Fragment {
    protected String TAG = getClass().getName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate...");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView...");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.i(TAG, "onResume...");
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.i(TAG, "onAttach...");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.i(TAG, "onStart...");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i(TAG, "onStop...");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "onPause...");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "onDetach...");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.i(TAG, "onDestroyView...");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i(TAG, "onHiddenChanged...hidden>> " + hidden);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy...");
    }

}
