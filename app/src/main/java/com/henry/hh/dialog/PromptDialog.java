package com.henry.hh.dialog;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.henry.hh.R;
import com.henry.library.utils.ControlsUtils;
import com.henry.library.utils.ScreenUtils;

/**
 * Date: 16-10-10 下午8:17
 * Creator: henry
 * Email: heneymark@gmail.com
 * Description:
 */
public class PromptDialog extends DialogFragment {
    private Context context;
    public PromptDialog(Context context){
        this.context = context;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_prompt,container,false);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout_dialog_prompt);
        int screenWidth = ScreenUtils.getScreenWidth(context);
        ControlsUtils.setSize(layout,screenWidth*3/5,screenWidth*3/8);
        return view;
    }


}
