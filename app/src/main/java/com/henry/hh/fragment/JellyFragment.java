package com.henry.hh.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.adapter.JellyFragmentPagerAdapter;
import com.henry.hh.entity.PeopleNearby;
import com.henry.library.fragment.BaseFragment;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class JellyFragment extends BaseFragment {

    private ImageView mShow;//展示的图片
    private ImageView mIconSex;//性别图标
    private TextView mAge;//性别文字
    private TextView mNickName;//昵称
    private TextView mZodiac;//星座
    private TextView mDistance;//距离

    public JellyFragment() {
        // Required empty public constructor
    }


    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_jelly);
        initWidget();
    }

    /**
     * 初始化控件
     */
    private void initWidget() {
        mShow = getViewById(R.id.iv_show);
        mIconSex = getViewById(R.id.iv_sex);
        mAge = getViewById(R.id.tv_age);
        mNickName = getViewById(R.id.tv_nickname);
        mZodiac = getViewById(R.id.tv_zodiac);
        mDistance = getViewById(R.id.tv_distance);
        Bundle bundle = getArguments();
        PeopleNearby peopleNearby =
                (PeopleNearby) bundle.getSerializable(JellyFragmentPagerAdapter.KEY);
        mNickName.setText(peopleNearby.getNameNearby());
        mDistance.setText(peopleNearby.getDistance()+"米");
        mAge.setText(peopleNearby.getAge()+"");
        mZodiac.setText(peopleNearby.getZodiac());
        ControlsUtils.setHeight(mShow, ScreenUtils.getScreenWidth(getContext())*138/144);

    }


}
