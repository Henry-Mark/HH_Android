package com.henry.hh.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henry.hh.R;

/**
 * A simple {@link Fragment} subclass.
 * 探索园
 */
public class ExplorationParkFragment extends Fragment {


    public ExplorationParkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exploration_park, container, false);
    }

}
