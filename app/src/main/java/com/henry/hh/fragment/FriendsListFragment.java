package com.henry.hh.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.utils.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 * 好友列表
 */
public class FriendsListFragment extends BaseFragment {


    public FriendsListFragment() {
        // Required empty public constructor
    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_list_friend);
        TextView tv = getViewById(R.id.f1);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(getActivity(),"f1");
            }
        });
    }

}
