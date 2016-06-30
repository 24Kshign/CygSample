package com.example.jack.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.jack.fragment.ActivityFragment;
import com.example.jack.fragment.SchoolFragment;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * Created by Jack on 2015/9/4.
 */

@ContentView(R.layout.activity_test)
public class TestActivity extends FragmentActivity implements View.OnClickListener {

    private Fragment schoolFragment;
    private Fragment activityFragment;

    @ViewInject(R.id.tv_activity)
    private TextView tvActivity;

    @ViewInject(R.id.tv_school)
    private TextView tvSchool;

    @ViewInject(R.id.rv_school)
    private RelativeLayout rvSchool;

    @ViewInject(R.id.rv_activity)
    private RelativeLayout rvActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);   //调用注解
        initeListener();
        select(0);
    }

    private void initeListener() {
        rvSchool.setOnClickListener(this);
        rvActivity.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rv_school:
                select(0);
                break;
            case R.id.rv_activity:
                select(1);
                break;
        }
    }

    private void select(int i) {
        // 设置内容区域
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        // 将内容区域隐藏
        hideFragment(transaction);
        switch (i) {
            case 0:
                if (schoolFragment == null) {
                    schoolFragment = new SchoolFragment();
                    transaction.add(R.id.fl_frame, schoolFragment);
                } else {
                    transaction.show(schoolFragment);
                }
                rvSchool.setBackgroundResource(R.drawable.school_rv_bg_white);
                rvActivity.setBackgroundResource(R.drawable.activity_rv_bg_blue);
                tvActivity.setTextColor(Color.WHITE);
                tvSchool.setTextColor(Color.BLUE);
                break;
            case 1:
                if (activityFragment == null) {
                    activityFragment = new ActivityFragment();
                    transaction.add(R.id.fl_frame, activityFragment);
                } else {
                    transaction.show(activityFragment);
                }
                rvActivity.setBackgroundResource(R.drawable.activity_rv_bg_white);
                rvSchool.setBackgroundResource(R.drawable.school_rv_bg_blue);
                tvSchool.setTextColor(Color.WHITE);
                tvActivity.setTextColor(Color.BLUE);
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction transaction) {
        if (schoolFragment != null) {
            transaction.hide(schoolFragment);
        }
        if (activityFragment != null) {
            transaction.hide(activityFragment);
        }
    }
}
