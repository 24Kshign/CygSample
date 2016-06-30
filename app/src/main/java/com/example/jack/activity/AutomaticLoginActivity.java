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
 * ���ߣ�Created by JackCheng on 2015/9/23 18:15.
 * ���䣺17764576259@163.com
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

    private boolean isRemember;   //��ʾ�Ƿ��ס����

    private boolean isAutomatic;   //��ʾ�Ƿ��Զ���¼

    private String strUseNameValue;

    private String strPasswardValue;

    private String strUseName;     //��ʾ�û�������û���

    private String strPwd;     //��ʾ�û����������

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
        //����ϴ�ѡ�˼�ס���룬���´ν���Ҳ���Զ���ѡ
        if (isRemember) {
            etUser.setText(strUseName);
            etPwd.setText(strPwd);
            checkPwd.setChecked(true);
        }

        if (isAutomatic) {
            checkLogin.setChecked(true);
            showToast("��½�ɹ�");
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
                    showToast("�û��������벻��Ϊ��");
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
                    editor.commit();    //�ύ
                    showToast("��½�ɹ�");
                    startActivity(new Intent(this, StartModeActivity.class));
                }
                break;
            case R.id.aal_btn_register:
                showToast("ע��ɹ�");
                break;
            case R.id.aal_tv_delete:
                etUser.setText("");
                etPwd.setText("");
                break;
        }
    }
}
