package com.henry.library.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.google.code.microlog4android.Logger;
import com.google.code.microlog4android.LoggerFactory;
import com.henry.hh.library.R;
import com.henry.library.activity.BaseActivity;
import com.henry.library.utils.ToastUtils;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Looper;
import android.util.TypedValue;
import android.widget.ScrollView;
import android.widget.TextView;


/**
 * 全局异常处理类
 */
public class UnhandledExceptionHandler
        implements Thread.UncaughtExceptionHandler {

    /**
     * 类名
     */
    @SuppressWarnings("unused")
	private static final String CLASS_NAME = "UnhandledExceptionHandler";

    /**
     * 日志器
     */
    private static final Logger logger =
            LoggerFactory.getLogger(UnhandledExceptionHandler.class);

    /**
     * 画面
     */
    private final BaseActivity activity;

    /**
     * 构造函数
     *
     * @param activity 画面seActivity activity;
     * <p/>
     * /**
     * 构造函数
     * @param activity 画面
     */
    public UnhandledExceptionHandler(final BaseActivity activity) {

        this.activity = activity;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Thread.UncaughtExceptionHandler#uncaughtException(java.lang.Thread, java.lang.Throwable)
     */
    @Override
    public void uncaughtException(Thread unusedThread, final Throwable t) {

        // 错误日志
        final String title = "Fatal error: " + getTopLevelCauseMessage(t);
        final String msg = getRecursiveStackTrace(t);

        new Thread(new Runnable() {

            @Override
            public void run() {

                Looper.prepare();

                if (t.getCause() instanceof WebServiceException) {
                    ToastUtils.showLong(activity, R.string.isconnectde_failed);


                } else {
                    showErrorDialog(title, msg);
                }

                logger.error(msg);

                Looper.loop();
            }
        }).start();
    }

    /**
     * 显示错误信息对话框
     *
     * @param title 错误标题
     * @param msg 错误信息
     */
    private void showErrorDialog(String title, String msg) {

        TextView errorView = new TextView(activity);
        errorView.setText(msg);
        errorView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        ScrollView scrollingContainer = new ScrollView(activity);
        scrollingContainer.addView(errorView);

        DialogInterface.OnClickListener listener =
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                        // 重启应用
                        final Intent intent = activity.getPackageManager()
                                .getLaunchIntentForPackage(
                                        activity.getPackageName());
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        activity.startActivity(intent);
                    }
                };
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(title).setView(scrollingContainer)
                .setPositiveButton("Exit", listener).create().show();
    }

    /**
     * 取得顶层的异常对象
     *
     * @param t 异常
     * @return 顶层的异常对象
     */
    private String getTopLevelCauseMessage(Throwable t) {

        Throwable topLevelCause = t;
        while (topLevelCause.getCause() != null) {
            topLevelCause = topLevelCause.getCause();
        }
        return topLevelCause.getMessage();
    }

    /**
     * 取得异常信息
     *
     * @param t 异常
     * @return 异常信息
     */
    private String getRecursiveStackTrace(Throwable t) {

        StringWriter writer = new StringWriter();
        t.printStackTrace(new PrintWriter(writer));
        return writer.toString();
    }
}
