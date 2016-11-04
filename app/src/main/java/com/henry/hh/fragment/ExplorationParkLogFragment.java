package com.henry.hh.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;

import com.henry.hh.R;
import com.henry.hh.activity.SwipeCardsActivity;
import com.henry.library.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * 探索园
 */
public class ExplorationParkLogFragment extends BaseFragment {


    public ExplorationParkLogFragment() {
        // Required empty public constructor
    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_exploration_park);

        (getViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), SwipeCardsActivity.class));
            }
        });

    }


}
