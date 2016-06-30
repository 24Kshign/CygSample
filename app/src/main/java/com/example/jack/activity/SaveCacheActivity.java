package com.example.jack.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * 作者：Created by JackCheng on 2015/10/24 18:43.
 * 邮箱：17764576259@163.com
 */

@ContentView(R.layout.activity_save_cache)
public class SaveCacheActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.asc_act_autotext)
    private AutoCompleteTextView autoText;

    @ViewInject(R.id.asc_btn_search)
    private Button btnSearch;

    @ViewInject(R.id.asc_btn_clear)
    private Button btnClearCache;

    private ArrayAdapter<String> mAdapter;

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);

        initeListenre();
    }

    private void initeListenre() {
        //获取搜索记录文件
        sp = getSharedPreferences("search_history", 0);
        String history = sp.getString("history", "暂没有搜索记录");
        //用逗号分割内容返回数组
        String history_arr[] = history.split(",");
        //新建适配器,适配器数据为搜索历史文件内容
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, history_arr);
        //保留前20条记录
        if (history_arr.length>20) {
            String[] newArrays=new String[20];
            //复制数组
            System.arraycopy(history_arr,0,newArrays,0,20);
            mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, history_arr);
        }
        //设置适配器
        mAdapter.notifyDataSetChanged();
        autoText.setAdapter(mAdapter);
        autoText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("您点击了Item"+position);
            }
        });

        btnSearch.setOnClickListener(this);
        btnClearCache.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.asc_btn_search:
                save();
                autoText.setText("");
                break;
            case R.id.asc_btn_clear:
                SharedPreferences sharedPreferences = getSharedPreferences("search_history",0);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.clear();
                editor.commit();
                showToast("清除历史成功");
                break;
        }
    }

    private void save() {
        //获取搜索框信息
        String text=autoText.getText().toString();
        SharedPreferences mysp=getSharedPreferences("search_history", 0);
        String old_text=mysp.getString("history","暂时没有搜索记录");
        //利用StringBuilder.append新增内容，逗号便于读取内容时用逗号拆分开
        StringBuilder sb=new StringBuilder(old_text);
        sb.append(text+",");

        //判断搜索内容是否已经存在于历史记录中
        if (!old_text.contains(text+",")) {
            SharedPreferences.Editor myEditor=mysp.edit();
            myEditor.putString("history",sb.toString());
            myEditor.commit();
            showToast(text + "添加成功");
        }else {
            showToast(text + "已存在");
        }
    }
}
