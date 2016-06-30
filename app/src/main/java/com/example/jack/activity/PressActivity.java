package com.example.jack.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * 作者：Created by JackCheng on 2015/10/4 17:06.
 * 邮箱：17764576259@163.com
 */
@ContentView(R.layout.activity_press)
public class PressActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.ap_et_user)
    private EditText etUser;

    @ViewInject(R.id.ap_et_pwd)
    private EditText etPwd;

    @ViewInject(R.id.ap_btn_login)
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);

        initeListener();
    }

    private void initeListener() {
        enableButtonOrNot(btnLogin, etUser, etPwd);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ap_btn_login:
                Toast.makeText(this,"登陆成功",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
