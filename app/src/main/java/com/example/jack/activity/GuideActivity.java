package com.example.jack.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.example.jack.adapter.FragmentPagerPagingAdapter;
import com.example.jack.fragment.GuideFragment1;
import com.example.jack.fragment.GuideFragment2;
import com.example.jack.fragment.GuideFragment3;
import com.example.jack.fragment.GuideFragment4;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * ���ߣ�Created by JackCheng on 2015/9/23 12:32.
 * ���䣺17764576259@163.com
 */

@ContentView(R.layout.activity_guide)
public class GuideActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    @ViewInject(R.id.ag_iv_dot1)
    private ImageView ivDot1;

    @ViewInject(R.id.ag_iv_dot2)
    private ImageView ivDot2;

    @ViewInject(R.id.ag_iv_dot3)
    private ImageView ivDot3;

    @ViewInject(R.id.ag_iv_dot4)
    private ImageView ivDot4;

    @ViewInject(R.id.ag_viewpager)
    private ViewPager mViewpager;

    //ΪViewPager׼��һ��������
    private FragmentPagerPagingAdapter adapter;

    //ΪViewPager׼��һ�����ݼ�
    private List<Fragment> mList = new ArrayList<Fragment>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        initeEvent();
    }

    private void initeEvent() {
        GuideFragment1 fg1 = new GuideFragment1();    //ʵ����fragment
        GuideFragment2 fg2 = new GuideFragment2();
        GuideFragment3 fg3 = new GuideFragment3();
        GuideFragment4 fg4 = new GuideFragment4();

        //�����е�fragment���뵽���ݼ���
        mList.add(fg1);
        mList.add(fg2);
        mList.add(fg3);
        mList.add(fg4);

        adapter = new FragmentPagerPagingAdapter(getSupportFragmentManager(), mList);
        mViewpager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mViewpager.setOnPageChangeListener(this);       //�������һ������Ա�ı�������СԲ�����ɫ
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        int currentItem = mViewpager.getCurrentItem();        //�õ���ǰ��ʾ��������һ��fragment
        resetImage();
        switch (currentItem) {
            case 0:
                ivDot1.setImageResource(R.drawable.dot_guide_focus);       //ѡ��֮��͸ı�ײ�СԲ�����ɫ
                break;
            case 1:
                ivDot2.setImageResource(R.drawable.dot_guide_focus);
                break;
            case 2:
                ivDot3.setImageResource(R.drawable.dot_guide_focus);
                break;
            case 3:
                ivDot4.setImageResource(R.drawable.dot_guide_focus);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    //���������Բ��Ϊδѡ�е���ɫ
    private void resetImage() {
        ivDot1.setImageResource(R.drawable.dot_guide_normal);
        ivDot2.setImageResource(R.drawable.dot_guide_normal);
        ivDot3.setImageResource(R.drawable.dot_guide_normal);
        ivDot4.setImageResource(R.drawable.dot_guide_normal);
    }
}
