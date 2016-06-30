package com.example.jack.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;

import com.example.jack.activity.R;

/**
 * ×÷Õß£ºCreated by JackCheng on 2015/9/20 15:45.
 * ÓÊÏä£º17764576259@163.com
 */
public class ContentFragment extends Fragment {
    private View mView;

    public void setCurrentViewPararms(FrameLayout.LayoutParams layoutParams) {
        mView.setLayoutParams(layoutParams);
    }

    public FrameLayout.LayoutParams getCurrentViewParams() {
        return (LayoutParams) mView.getLayoutParams();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_content, container, false);
        return mView;
    }
}
