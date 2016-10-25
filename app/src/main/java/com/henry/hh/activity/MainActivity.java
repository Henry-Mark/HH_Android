package com.henry.hh.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.henry.hh.R;
import com.henry.hh.constants.TabDatas;
import com.henry.hh.dialog.PromptDialog;
import com.henry.hh.interfaces.OnDialogClickListener;
import com.henry.library.activity.BaseActivity;
import com.henry.library.utils.ScreenUtils;

public class MainActivity extends BaseActivity implements
        TabHost.OnTabChangeListener, OnDialogClickListener {
    private FragmentTabHost tabHost;
    private String TAG_DIALOG = "promptDialog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        setTitle(R.string.app_name);

        tabHost = (FragmentTabHost) super.findViewById(android.R.id.tabhost);
        tabHost.setup(this, super.getSupportFragmentManager()
                , R.id.contentLayout);
        tabHost.getTabWidget().setDividerDrawable(null);
        tabHost.setOnTabChangedListener(this);
        initTab();

        //判断对话框是否存在，若存在，则销毁重新启动
        PromptDialog fragment = (PromptDialog) getFragmentManager().findFragmentByTag(TAG_DIALOG);
        if (fragment != null) {
            getFragmentManager().beginTransaction().remove(fragment).commit();
            showDialog();
        }
    }

    /**
     * 初始化tab
     */
    private void initTab() {
        int tabs[] = TabDatas.getTabsRes();
        for (int i = 0; i < tabs.length; i++) {
            TabHost.TabSpec tabSpec = tabHost.newTabSpec(getString(tabs[i])).setIndicator(getTabView(i));
            tabHost.addTab(tabSpec, TabDatas.getFragments()[i], null);
            tabHost.setTag(i);
        }
    }

    /**
     * 设置tab图像与字体颜色
     *
     * @param idx
     * @return
     */
    private View getTabView(int idx) {
        View view = LayoutInflater.from(this).inflate(R.layout.footer_tabs, null);
        ((TextView) view.findViewById(R.id.title_footer)).setText(TabDatas.getTabsRes()[idx]);
        if (idx == 0) {
            ((TextView) view.findViewById(R.id.title_footer)).
                    setTextColor(ContextCompat.getColor(mContext, R.color.colorMainStyle));
            ((ImageView) view.findViewById(R.id.img_footer)).
                    setImageResource(TabDatas.getTabsImgLight()[idx]);
        } else {
            ((ImageView) view.findViewById(R.id.img_footer)).
                    setImageResource(TabDatas.getTabsImg()[idx]);
        }
        return view;
    }

    @Override
    public void onTabChanged(String tabId) {
        updateTab();
    }

    /**
     * 更新tab内容
     */
    private void updateTab() {
        TabWidget tabw = tabHost.getTabWidget();
        for (int i = 0; i < tabw.getChildCount(); i++) {
            View view = tabw.getChildAt(i);
            ImageView iv = (ImageView) view.findViewById(R.id.img_footer);
            if (i == tabHost.getCurrentTab()) {
                ((TextView) view.findViewById(R.id.title_footer)).setTextColor(
                        ContextCompat.getColor(mContext, R.color.colorMainStyle));
                iv.setImageResource(TabDatas.getTabsImgLight()[i]);
            } else {
                ((TextView) view.findViewById(R.id.title_footer)).setTextColor(
                        ContextCompat.getColor(mContext, R.color.colorTabTextNormal));
                iv.setImageResource(TabDatas.getTabsImg()[i]);
            }

        }
    }

    /**
     * 显示提示对话框
     */
    private void showDialog() {
        PromptDialog promptDialog = new PromptDialog(this, "slfblabfl", ScreenUtils.getScreenWidth(this));
        promptDialog.show(getFragmentManager(), TAG_DIALOG);
        promptDialog.setOnDialogClickListener(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            showDialog();
        }

        return false;
    }

    @Override
    public void onOkClick() {
        finish();
    }

    @Override
    public void onCancel() {

    }
}
