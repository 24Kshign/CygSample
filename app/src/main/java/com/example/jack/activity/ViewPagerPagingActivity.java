package com.example.jack.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.example.jack.adapter.FragmentPagerPagingAdapter;
import com.example.jack.fragment.PagerPagingFragment1;
import com.example.jack.fragment.PagerPagingFragment2;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2015/9/6.
 */

@ContentView(R.layout.activity_viewpager_paging)
public class ViewPagerPagingActivity extends FragmentActivity {

    @ViewInject(R.id.viewpager)
    private ViewPager viewPager;

    private List<Fragment> mList;

    private FragmentPagerPagingAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);   //调用注解
        initeFragment();

    }

    private void initeFragment() {
        mList = new ArrayList<Fragment>();
        PagerPagingFragment1 fragment1 = new PagerPagingFragment1();
        PagerPagingFragment2 fragment2 = new PagerPagingFragment2();

        mList.add(fragment1);
        mList.add(fragment2);
        adapter = new FragmentPagerPagingAdapter(getSupportFragmentManager(), mList);
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();       //更新数据
    }
}



