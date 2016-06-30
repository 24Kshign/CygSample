package com.example.jack.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.jack.adapter.CharacterParser;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * ×÷Õß£ºCreated by JackCheng on 2015/10/25 17:06.
 * ÓÊÏä£º17764576259@163.com
 */
@ContentView(R.layout.activity_list_filter)
public class ListFilterActivity extends BaseActivity {

    @ViewInject(R.id.alf_et_search)
    private EditText etSearch;

    @ViewInject(R.id.alf_lv_list)
    private ListView mListView;

    private ArrayAdapter<String> mAdapter;

    private CharacterParser characterParser;

    private final String[] mDate = {"MOTO", "HTC", "Samsung", "Iphone", "Nokia",
            "HUAWEI", "Vivo", "Coolpad", "Oppo", "Ipad","ÄãºÃ","nihao"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ViewInjectUtils.inject(this);

        initeListener();
    }

    private void initeListener() {
        mAdapter = new ArrayAdapter<String>(this, R.layout.item_filter_list,
                R.id.ifl_tv_text, mDate);
        mListView.setAdapter(mAdapter);
        characterParser=new CharacterParser();
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("CygSample----->", "beforeChange");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("CygSample----->", "afterChange");
            }
        });
    }
}
