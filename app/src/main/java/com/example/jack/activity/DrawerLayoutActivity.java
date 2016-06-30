package com.example.jack.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.example.jack.adapter.ContentAdapter;
import com.example.jack.bean.ContentModel;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Created by JackCheng on 2015/9/21 21:29.
 * 邮箱：17764576259@163.com
 */

@ContentView(R.layout.activity_drawerlayout)
public class DrawerLayoutActivity extends BaseActivity {

    @ViewInject(R.id.drawer_layout)
    private DrawerLayout drawerLayout;

    private RelativeLayout leftLayout;
    private List<ContentModel> list;
    private ContentAdapter adapter;

    private Button mBtnOpen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);

        leftLayout = (RelativeLayout) findViewById(R.id.left);
        drawerLayout= (DrawerLayout) findViewById(R.id.drawer_layout);
        mBtnOpen= (Button) findViewById(R.id.ad_btn_open);
        ListView listView = (ListView) leftLayout.findViewById(R.id.left_listview);

        initData();
        adapter = new ContentAdapter(this, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        showToast("点击了第1个");
                        drawerLayout.closeDrawer(leftLayout);
                        break;
                    case 1:
                        drawerLayout.closeDrawer(leftLayout);
                        showToast("点击了第2个");
                        break;
                    case 2:
                        showToast("点击了第3个");
                        break;
                    case 3:
                        showToast("点击了第4个");
                        break;
                    case 4:
                        showToast("点击了第5个");
                        break;
                    case 5:
                        showToast("点击了第6个");
                        break;
                }
            }
        });

        mBtnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.LEFT);
            }
        });
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new ContentModel(R.mipmap.ic_launcher, "你好"));
        list.add(new ContentModel(R.mipmap.ic_launcher, "我是"));
        list.add(new ContentModel(R.mipmap.ic_launcher, "大根"));
        list.add(new ContentModel(R.mipmap.ic_launcher, "请问"));
        list.add(new ContentModel(R.mipmap.ic_launcher, "你叫"));
        list.add(new ContentModel(R.mipmap.ic_launcher, "什么"));
    }

}
