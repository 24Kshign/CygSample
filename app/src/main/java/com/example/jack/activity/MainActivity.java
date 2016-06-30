package com.example.jack.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;

import com.example.jack.adapter.MainAdapter;
import com.example.jack.bean.MainBean;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;
import com.example.jack.view.XListView;

import java.util.ArrayList;
import java.util.List;


@ContentView(R.layout.activity_main)
public class MainActivity extends Activity implements XListView.IXListViewListener {

    @ViewInject(R.id.sa_xlistview)

    //定义一个listview
    private XListView mListview;
    //定义一个集合
    private List<MainBean> mList;

    private Handler mHandler;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this); // 调用ioc

        mListview.setPullLoadEnable(true);

        mList = new ArrayList<>();
        geneItems();
        mListview.setAdapter(new MainAdapter(this, mList));
        mListview.setXListViewListener(this);
        mHandler = new Handler();

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
    }

    private void geneItems() {
        for (int i = 1; i < 31; ++i) {
            if (i > 31) {
                i %= 31;
            }
            mList.add(new MainBean(R.mipmap.ic_launcher, "阿猫", "你好，请问是杭师大的同学吗"));
        }
    }

    private void onLoad() {
        mListview.stopRefresh();
        mListview.stopLoadMore();
        mListview.setRefreshTime("刚刚");
    }

    //下拉之后
    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mList.clear();
                geneItems();
                mListview.setAdapter(new MainAdapter(MainActivity.this, mList));
                onLoad();
            }
        }, 2000);
    }

    //上拉之后
    @Override
    public void onLoadMore() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                geneItems();
                onLoad();
            }
        }, 2000);
    }
}
