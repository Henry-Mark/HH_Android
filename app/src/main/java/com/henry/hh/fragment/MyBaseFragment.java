package com.henry.hh.fragment;

import android.os.Bundle;

import com.google.gson.Gson;
import com.henry.hh.activity.MyApplication;
import com.henry.library.fragment.BaseFragment;

/**
 * Date: 2016/12/14. 11:49
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:
 */
public class MyBaseFragment extends BaseFragment {

    protected Gson gson = new Gson();

    @Override
    protected void doCreateView(Bundle savedInstanceState) {

    }

    /**
     * 全局变量
     *
     * @return
     */
    public MyApplication getMyApplication() {
        return (MyApplication) getActivity().getApplication();
    }
}
