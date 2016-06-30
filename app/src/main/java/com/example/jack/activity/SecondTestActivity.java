package com.example.jack.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * Author:Created by JackCheng
 * Email:17764576259@163.com
 * Time:2015/12/6 15:48
 * Copyright:1.0
 */

@ContentView(R.layout.activity_second_test)
public class SecondTestActivity extends Activity implements View.OnClickListener {

    @ViewInject(R.id.ast_btn_next)
    private Button btnNext;

    @ViewInject(R.id.ast_btn_back)
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("SecondTestActivity---->", this.toString());
        ViewInjectUtils.inject(this);
        initeListener();
    }

    private void initeListener() {
        btnNext.setOnClickListener(this);
        btnBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ast_btn_next:
                startActivity(new Intent(this, SecondTestActivity.class));
                break;
            case R.id.ast_btn_back:
                startActivity(new Intent(this, StartModeActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("SecondTestActivity---->", "onDestroy");
    }
}
