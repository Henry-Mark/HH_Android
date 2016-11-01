package com.henry.hh.dialog;

import android.annotation.SuppressLint;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.interfaces.OnDialogClickListener;

/**
 * Date: 16-10-10 下午8:17
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:提示对话框
 */
@SuppressLint("ValidFragment")
public class PromptDialog extends DialogFragment implements View.OnClickListener {
    private Context context;
    //提示的信息
    private String msg;

    //显示消息控件
    private TextView mMsg;
    //确定按钮
    private Button mOk;
    //取消按钮
    private Button mCancel;

    private String CONTEXT;
    private String MESSAGE = "message";
    private String SCREENWIDTH = "width";

    private int screenWidth;

    //设置监听
    private OnDialogClickListener dialogClickListener = null;

    /**
     * 构造方法
     *
     * @param context
     * @param msg
     */
    public PromptDialog(Context context, String msg, int screenWidth) {
        this.context = context;
        this.msg = msg;
        this.screenWidth = screenWidth;
        Bundle arguments = new Bundle();
        arguments.putString(MESSAGE, msg);
        arguments.putInt(SCREENWIDTH, screenWidth);
    }

    public PromptDialog() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments;
        if ((arguments = this.getArguments()) != null) {
            this.MESSAGE = arguments.getString(MESSAGE);
            this.screenWidth = arguments.getInt(SCREENWIDTH);
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window = getDialog().getWindow();
        //无标题
        window.requestFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.fragment_dialog_prompt, container, false);
        //点击window外的区域 是否消失
        getDialog().setCanceledOnTouchOutside(false);
        bindView(view);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        mMsg.setText(msg == null ? "" : msg);

        return view;
    }

    /**
     * 绑定控件
     *
     * @param view
     */
    private void bindView(View view) {
        mMsg = (TextView) view.findViewById(R.id.dialog_content);
        mOk = (Button) view.findViewById(R.id.bt_dialog_ok);
        mCancel = (Button) view.findViewById(R.id.bt_dialog_cancel);
        mOk.setOnClickListener(this);
        mCancel.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDialog().getWindow().setLayout(screenWidth * 3 / 5, screenWidth * 3 / 8);
    }

    @Override
    public void onClick(View v) {
        if (v == mOk) {
            if (dialogClickListener != null) {
                dismiss();
                dialogClickListener.onOkClick();
            }
        } else if (v == mCancel) {
            if (dialogClickListener != null) {
                dismiss();
                dialogClickListener.onCancel();
            }
        }
    }

    /**
     * 设置监听事件
     *
     * @param listener
     */
    public void setOnDialogClickListener(OnDialogClickListener listener) {
        this.dialogClickListener = listener;
    }
}
