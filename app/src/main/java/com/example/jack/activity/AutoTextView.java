package com.example.jack.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * ×÷Õß£ºCreated by JackCheng on 2015/10/24 17:49.
 * ÓÊÏä£º17764576259@163.com
 */

@ContentView(R.layout.activity_auto_completet)
public class AutoTextView extends Activity {

    @ViewInject(R.id.aac_auto_text)
    private AutoCompleteTextView textView;

    private String[] name = new String[]{"abcd", "bcde", "cdef", "defg", "efgh", "fghi", "ghij",
            "hijk", "ijkl", "jklm", "klmn", "lmno", "mnop"};

    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);

        initeListener();
    }

    private void initeListener() {
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, name);
        textView.setAdapter(mAdapter);
        textView.setThreshold(1);
    }
}