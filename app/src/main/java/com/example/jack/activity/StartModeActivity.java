package com.example.jack.activity;

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
 * Time:2015/12/6 14:42
 * Copyright:1.0
 */

@ContentView(R.layout.activity_start_mode)
public class StartModeActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.asm_btn_myself)
    private Button btnMyself;

    @ViewInject(R.id.asm_btn_next)
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("StartModeActivity---->", this.toString());
        ViewInjectUtils.inject(this);

        initeListener();
    }

    private void initeListener() {
        btnMyself.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.asm_btn_myself:
                startActivity(new Intent(this, StartModeActivity.class));
                break;
            case R.id.asm_btn_next:
                startActivity(new Intent(this, SecondTestActivity.class));
                break;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("StartModeActivity---->", "onRestart");
    }
}
