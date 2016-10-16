package com.henry.hh.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.henry.hh.R;
import com.henry.hh.interfaces.OnOperationListener;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.utils.ToastUtils;

/**
 * A simple {@link Fragment} subclass.
 * 聊天键盘功能界面
 */
public class ChatFunctionFragment extends BaseFragment implements View.OnClickListener {

    private OnOperationListener listener;
    private LinearLayout mPicLayout;
    private LinearLayout mPhotoLayout;

    public ChatFunctionFragment() {
        // Required empty public constructor
    }


    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_chat_function);
        mPicLayout = getViewById(R.id.chat_menu_images);
        mPhotoLayout = getViewById(R.id.chat_menu_photo);

        mPicLayout.setOnClickListener(this);
        mPhotoLayout.setOnClickListener(this);
    }

    /**
     * 点击菜单
     *
     * @param i
     */
    private void clickMenu(int i) {
        if (listener != null) {
            listener.selectedFunction(i);
        }
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chat_menu_images:
                clickMenu(0);
                ToastUtils.showShort(getActivity(), "image");
                break;
            case R.id.chat_menu_photo:
                clickMenu(1);
                ToastUtils.showShort(getActivity(), "photo");
                break;
            default:
                break;
        }
    }
}
