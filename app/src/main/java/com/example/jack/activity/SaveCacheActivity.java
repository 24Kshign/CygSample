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
 * ���ߣ�Created by JackCheng on 2015/10/24 18:43.
 * ���䣺17764576259@163.com
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
        //��ȡ������¼�ļ�
        sp = getSharedPreferences("search_history", 0);
        String history = sp.getString("history", "��û��������¼");
        //�ö��ŷָ����ݷ�������
        String history_arr[] = history.split(",");
        //�½�������,����������Ϊ������ʷ�ļ�����
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, history_arr);
        //����ǰ20����¼
        if (history_arr.length>20) {
            String[] newArrays=new String[20];
            //��������
            System.arraycopy(history_arr,0,newArrays,0,20);
            mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, history_arr);
        }
        //����������
        mAdapter.notifyDataSetChanged();
        autoText.setAdapter(mAdapter);
        autoText.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showToast("�������Item"+position);
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
                showToast("�����ʷ�ɹ�");
                break;
        }
    }

    private void save() {
        //��ȡ��������Ϣ
        String text=autoText.getText().toString();
        SharedPreferences mysp=getSharedPreferences("search_history", 0);
        String old_text=mysp.getString("history","��ʱû��������¼");
        //����StringBuilder.append�������ݣ����ű��ڶ�ȡ����ʱ�ö��Ų�ֿ�
        StringBuilder sb=new StringBuilder(old_text);
        sb.append(text+",");

        //�ж����������Ƿ��Ѿ���������ʷ��¼��
        if (!old_text.contains(text+",")) {
            SharedPreferences.Editor myEditor=mysp.edit();
            myEditor.putString("history",sb.toString());
            myEditor.commit();
            showToast(text + "��ӳɹ�");
        }else {
            showToast(text + "�Ѵ���");
        }
    }
}
