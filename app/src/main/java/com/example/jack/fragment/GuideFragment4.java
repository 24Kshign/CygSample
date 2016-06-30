package com.example.jack.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jack.activity.R;

/**
 * ×÷Õß£ºCreated by JackCheng on 2015/9/23 12:53.
 * ÓÊÏä£º17764576259@163.com
 */
public class GuideFragment4 extends Fragment {

    private View mView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_guide4, null);
        return mView;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
