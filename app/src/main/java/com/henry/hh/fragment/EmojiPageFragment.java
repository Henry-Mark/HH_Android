package com.henry.hh.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henry.hh.R;
import com.henry.hh.interfaces.OnOperationListener;
import com.henry.library.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmojiPageFragment extends BaseFragment {

    private OnOperationListener listener;
    public EmojiPageFragment() {
        // Required empty public constructor
    }


    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_emoji_page);
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
    }
}
