package com.example.jack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * Created by Jacky on 2015/8/26.
 */

@ContentView(R.layout.activity_load)
public class LoginActivity extends BaseActivity implements View.OnClickListener {


    @ViewInject(R.id.la_btn_register)
    private Button btnRegister;

    @ViewInject(R.id.la_btn_load)
    private Button btnLoad;

    @ViewInject(R.id.la_et_phone)
    private EditText etPhone;

    @ViewInject(R.id.la_et_pwd)
    private EditText etPwd;


    private String getStringPhone;
    private String getStringPwd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this); // 调用ioc

        initeView();
    }

    private void initeView() {
        btnLoad.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        getStringPhone = etPhone.getText().toString();
        getStringPwd = etPwd.getText().toString();
        switch (v.getId()) {
            case R.id.la_btn_load:
                if (getStringPhone.isEmpty()) {
                    showToast("请输入手机号");
                } else if (getStringPwd.isEmpty()) {
                    showToast("请输入密码");
                } else {
                    startActivity(new Intent(this, MainActivity.class));
                }
                break;
            case R.id.la_btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }

    }
}
