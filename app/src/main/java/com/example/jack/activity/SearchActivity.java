package com.example.jack.activity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * 作者：Created by JackCheng on 2015/10/21 20:10.
 * 邮箱：17764576259@163.com
 */

@ContentView(R.layout.activity_search)
public class SearchActivity extends BaseActivity implements SearchView.OnQueryTextListener {

    @ViewInject(R.id.as_sv_search)
    private SearchView mSearchView;

    @ViewInject(R.id.as_lv_list)
    private ListView mListView;

    private final String[] mContent = {"aaa", "aaaa", "bbb", "bbbbb",
            "eee", "eebee", "ebebeb", "dddd", "fdfdfd"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        initeListener();
    }

    private void initeListener() {
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mContent));
        //设置listview可以被过滤
        mListView.setTextFilterEnabled(true);
        // 设置该SearchView默认是否自动缩小为图标
        mSearchView.setIconifiedByDefault(false);
        // 为该SearchView组件设置事件监听器
        mSearchView.setOnQueryTextListener(this);
        // 设置该SearchView显示搜索按钮
        mSearchView.setSubmitButtonEnabled(true);
        // 设置该SearchView内默认显示的提示文本
        mSearchView.setQueryHint("查找");

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // 实际应用中应该在该方法内执行实际查询
        // 此处仅使用Toast显示用户输入的查询内容
        showToast("您的选择是:" + query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            //清除listview过滤
            mListView.clearTextFilter();
        } else {
            //使用用户输入的内容对listview进行过滤
            mListView.setFilterText(newText);

        }
        return true;
    }
}
