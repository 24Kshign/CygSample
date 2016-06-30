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
 * ���ߣ�Created by JackCheng on 2015/10/21 20:10.
 * ���䣺17764576259@163.com
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
        //����listview���Ա�����
        mListView.setTextFilterEnabled(true);
        // ���ø�SearchViewĬ���Ƿ��Զ���СΪͼ��
        mSearchView.setIconifiedByDefault(false);
        // Ϊ��SearchView��������¼�������
        mSearchView.setOnQueryTextListener(this);
        // ���ø�SearchView��ʾ������ť
        mSearchView.setSubmitButtonEnabled(true);
        // ���ø�SearchView��Ĭ����ʾ����ʾ�ı�
        mSearchView.setQueryHint("����");

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        // ʵ��Ӧ����Ӧ���ڸ÷�����ִ��ʵ�ʲ�ѯ
        // �˴���ʹ��Toast��ʾ�û�����Ĳ�ѯ����
        showToast("����ѡ����:" + query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            //���listview����
            mListView.clearTextFilter();
        } else {
            //ʹ���û���������ݶ�listview���й���
            mListView.setFilterText(newText);

        }
        return true;
    }
}
