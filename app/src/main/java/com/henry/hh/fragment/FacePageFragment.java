package com.henry.hh.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.henry.hh.R;
import com.henry.hh.interfaces.OnOperationListener;
import com.henry.library.fragment.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FacePageFragment extends BaseFragment {

    private OnOperationListener listener;
    public static final String FACE_FOLDER_PATH = "face_folder_path";

    public FacePageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_face_page, container, false);
    }

    @Override
    protected void doCreateView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_face_page);
    }

    public void setOnOperationListener(OnOperationListener onOperationListener) {
        this.listener = onOperationListener;
    }
}
