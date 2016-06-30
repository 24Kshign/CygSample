package com.example.jack.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by Jack on 2015/9/9.
 */
public class FragmentPagerPagingAdapter extends FragmentPagerAdapter {

    private List<Fragment> mList;
    public FragmentPagerPagingAdapter(FragmentManager fm) {
        super(fm);
    }

    public FragmentPagerPagingAdapter(FragmentManager fm, List<Fragment> mList) {
        super(fm);
        this.mList=mList;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }
}
