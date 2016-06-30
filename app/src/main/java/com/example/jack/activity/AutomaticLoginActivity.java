package com.example.jack.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * 作者：Created by JackCheng on 2015/9/23 18:15.
 * 邮箱：17764576259@163.com
 */
@ContentView(R.layout.activity_automatic_login)
public class AutomaticLoginActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.aal_et_user)
    private EditText etUser;

    @ViewInject(R.id.aal_et_pwd)
    private EditText etPwd;

    @ViewInject(R.id.aal_check_pwd)
    private CheckBox checkPwd;

    @ViewInject(R.id.aal_check_login)
    private CheckBox checkLogin;

    @ViewInject(R.id.aal_btn_load)
    private Button btnLogin;

    @ViewInject(R.id.aal_btn_register)
    private Button btnRegister;

    @ViewInject(R.id.aal_tv_delete)
    private TextView tvDelete;

    private SharedPreferences sp;

    private boolean isRemember;   //表示是否记住密码

    private boolean isAutomatic;   //表示是否自动登录

    private String strUseNameValue;

    private String strPasswardValue;

    private String strUseName;     //表示用户输入的用户名

    private String strPwd;     //表示用户输入的密码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);

        inite();
    }

    private void inite() {
        sp = this.getSharedPreferences("useInfo", Context.MODE_PRIVATE);
        strUseName = sp.getString("USENAME", "");
        strPwd = sp.getString("PASSWARD", "");
        isRemember = sp.getBoolean("ISREMEMBER", false);
        isAutomatic = sp.getBoolean("ISAUTOMATIC", false);
        //如果上次选了记住密码，则下次进入也会自动勾选
        if (isRemember) {
            etUser.setText(strUseName);
            etPwd.setText(strPwd);
            checkPwd.setChecked(true);
        }

        if (isAutomatic) {
            checkLogin.setChecked(true);
            showToast("登陆成功");
        }

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        tvDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aal_btn_load:
                strUseNameValue = etUser.getText().toString();
                strPasswardValue = etPwd.getText().toString();
                SharedPreferences.Editor editor = sp.edit();
                if (etUser.getText().toString().equals("") || etPwd.getText().toString().equals("")) {
                    showToast("用户名或密码不能为空");
                } else {
                    editor.putString("USENAME", strUseNameValue);
                    editor.putString("PASSWARD", strPasswardValue);
                    if (checkPwd.isChecked()) {
                        editor.putBoolean("ISREMEMBER", true);
                    } else {
                        editor.putBoolean("ISREMEMBER", false);
                    }
                    if (checkLogin.isChecked()) {
                        editor.putBoolean("ISAUTOMATIC", true);
                    } else {
                        editor.putBoolean("ISAUTOMATIC", false);
                    }
                    editor.commit();    //提交
                    showToast("登陆成功");
                    startActivity(new Intent(this, StartModeActivity.class));
                }
                break;
            case R.id.aal_btn_register:
                showToast("注册成功");
                break;
            case R.id.aal_tv_delete:
                etUser.setText("");
                etPwd.setText("");
                break;
        }
    }
}
