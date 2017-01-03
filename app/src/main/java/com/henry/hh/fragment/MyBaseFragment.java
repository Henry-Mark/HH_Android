package com.henry.hh.fragment;

import android.os.Bundle;

import com.google.gson.Gson;
import com.henry.hh.activity.MyApplication;
import com.henry.library.fragment.BaseFragment;
import com.litesuits.orm.LiteOrm;

/**
 * Date: 2016/12/14. 11:49
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:
 */
public class MyBaseFragment extends BaseFragment {

    protected Gson gson = new Gson();

    protected static LiteOrm liteOrm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化数据库
        if (liteOrm == null) {
            liteOrm = LiteOrm.newSingleInstance(getActivity(), "hh.db");
        }
        liteOrm.setDebugged(true); // open the log
    }

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
