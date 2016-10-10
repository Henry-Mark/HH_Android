package com.henry.hh.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;

/**
 * Date: 16-10-10 下午8:17
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:提示对话框
 */
public class PromptDialog extends DialogFragment {
    private Context context;
    //提示的信息
    private String msg;

    //对话框布局
    private LinearLayout layout;
    //显示消息控件
    private TextView mMsg;
    //确定按钮
    private Button mOk;
    //取消按钮
    private Button mCancel;

    /**
     * 构造方法
     *
     * @param context
     * @param msg
     */
    public PromptDialog(Context context, String msg) {
        this.context = context;
        this.msg = msg;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_prompt, container, false);

        bindView(view);

        mMsg.setText(msg == null ? "" : msg);

        return view;
    }

    private void bindView(View view) {
        layout = (LinearLayout) view.findViewById(R.id.layout_dialog_prompt);
        mMsg = (TextView) view.findViewById(R.id.dialog_content);
        mOk = (Button) view.findViewById(R.id.bt_dialog_ok);
        mCancel = (Button) view.findViewById(R.id.bt_dialog_cancel);
        //获取屏幕宽度
        int screenWidth = ScreenUtils.getScreenWidth(context);
        //设置对话框大小
        ControlsUtils.setSize(layout, screenWidth * 3 / 5, screenWidth * 3 / 8);
    }


}
