package com.example.jack.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jack.activity.R;

/**
 * ×÷Õß£ºCreated by JackCheng on 2015/9/20 15:45.
 * ÓÊÏä£º17764576259@163.com
 */
public class MenuFragment extends Fragment {

    private View mView;
    public View getCurrentView() {
        return mView;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_menu, container, false);
        return mView;
    }
}
