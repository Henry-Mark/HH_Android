package com.henry.hh.dialog;

import android.app.DialogFragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.interfaces.OnPhotoGetListener;
import com.henry.library.utils.ScreenUtils;

/**
 * Date: 2016/11/1. 14:12
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description: 从底部弹出选择菜单
 */
public class ButtonMenuFragment extends DialogFragment implements View.OnClickListener {

    private TextView mTakePhoto;
    private TextView mChoosePic;
    private TextView mCancel;
    private OnPhotoGetListener listener = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//设置背景透明
        getDialog().getWindow().setWindowAnimations(R.style.menu_animation);//添加一组进出动画

        View view = inflater.inflate(R.layout.fragment_menu_buttom, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化控件
     *
     * @param view
     */
    private void initView(View view) {
        mTakePhoto = (TextView) view.findViewById(R.id.tv_take_photo);
        mChoosePic = (TextView) view.findViewById(R.id.tv_choose_pic);
        mCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mTakePhoto.setOnClickListener(this);
        mCancel.setOnClickListener(this);
        mChoosePic.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mCancel) {
            dismiss();
        } else if (v == mChoosePic) {
            if (listener != null)
                listener.onPicSelected();
            dismiss();
        } else if (v == mTakePhoto) {
            if (listener != null)
                listener.onPhotoTaken();
            dismiss();
        }
    }

    public void setOnPhotoGetListener(OnPhotoGetListener listener) {
        this.listener = listener;
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置弹出框宽屏显示，适应屏幕宽度
        getDialog().getWindow().setLayout(ScreenUtils.getScreenWidth(getActivity()),
                getDialog().getWindow().getAttributes().height);

        //移动弹出菜单到底部
        WindowManager.LayoutParams wlp = getDialog().getWindow().getAttributes();
        wlp.gravity = Gravity.BOTTOM;
        // wlp.width = WindowManager.LayoutParams.MATCH_PARENT;
        getDialog().getWindow().setAttributes(wlp);
    }

    @Override
    public void onStop() {
        this.getView().setAnimation(AnimationUtils.loadAnimation(getActivity(),
                R.anim.menu_disappear));
        super.onStop();
    }
}
