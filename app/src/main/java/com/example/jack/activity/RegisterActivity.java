package com.example.jack.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

@ContentView(R.layout.activity_register)
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.ra_et_pwd)
    private EditText etPwd;

    @ViewInject(R.id.ra_et_phone)
    private EditText etPhone;

    @ViewInject(R.id.ra_btn_register)
    private Button btnLoad;

    private String getStringCode;                    //得到验证码
    private String getStringPhone;                 //得到手机号
    private String getStringPwd;                  //得到密码
    private String getStringConfirmPwd;        //得到确认密码

    private String url = "http://1.chuquhai.sinaapp.com/chuquhaiAPI/RegisterApi.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this); // 调用ioc
        initeView();

    }

    private void initeView() {
        btnLoad.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        getStringPhone = etPhone.getText().toString();
        getStringPwd = etPwd.getText().toString();
        switch (v.getId()) {
            case R.id.ra_btn_register:
                if (getStringPhone.isEmpty()) {
                    showToast("请输入手机号");
                } else if (getStringCode.isEmpty()) {
                    showToast("请输入验证码");
                } else if (getStringPwd.isEmpty()) {
                    showToast("请输入密码");
                } else if (getStringConfirmPwd.isEmpty()) {
                    showToast("请输入确认密码");
                } else if (!getStringConfirmPwd.equals(getStringPwd)) {
                    showToast("两次输入的密码不一致");
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }
}
