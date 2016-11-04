package com.henry.hh.activity;

import android.os.Bundle;

import com.henry.hh.R;
import com.henry.library.activity.TitleActivity;

/**
 * 可滑动卡片
 */
public class SwipeCardsActivity extends TitleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_cards);
    }
}
