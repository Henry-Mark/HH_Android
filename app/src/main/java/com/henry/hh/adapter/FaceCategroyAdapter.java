package com.henry.hh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.henry.hh.fragment.ChatFunctionFragment;
import com.henry.hh.fragment.EmojiPageFragment;
import com.henry.hh.interfaces.OnOperationListener;
import com.henry.hh.widget.ChatKeyboard;

import java.util.List;

/**
 * Date: 16-10-15 下午9:58
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:控件分类的viewpager适配器
 */
public class FaceCategroyAdapter extends FragmentStatePagerAdapter {

    private int sMode;
    private List<String> datas;
    private OnOperationListener listener;

    public FaceCategroyAdapter(FragmentManager fm, int mode) {
        super(fm);
        sMode = mode;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (sMode == ChatKeyboard.LAYOUT_TYPE_FACE) {
            fragment = new EmojiPageFragment();
            ((EmojiPageFragment) fragment).setOnOperationListener(listener);
        } else {
            fragment = new ChatFunctionFragment();
            ((ChatFunctionFragment) fragment).setOnOperationListener(listener);
        }
        return fragment;
    }

    @Override
    public int getCount() {
        if (sMode == ChatKeyboard.LAYOUT_TYPE_FACE) {
            int count = datas == null ? 0 : datas.size();
            //加1是因为有默认的emoji表情分类
            return (count + 1);
        } else {
            return 1;
        }
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
    }

    public void refresh(List<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }
}
