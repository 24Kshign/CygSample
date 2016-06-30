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

    private String getStringCode;                    //�õ���֤��
    private String getStringPhone;                 //�õ��ֻ���
    private String getStringPwd;                  //�õ�����
    private String getStringConfirmPwd;        //�õ�ȷ������

    private String url = "http://1.chuquhai.sinaapp.com/chuquhaiAPI/RegisterApi.php";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this); // ����ioc
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
                    showToast("�������ֻ���");
                } else if (getStringCode.isEmpty()) {
                    showToast("��������֤��");
                } else if (getStringPwd.isEmpty()) {
                    showToast("����������");
                } else if (getStringConfirmPwd.isEmpty()) {
                    showToast("������ȷ������");
                } else if (!getStringConfirmPwd.equals(getStringPwd)) {
                    showToast("������������벻һ��");
                } else {
                    startActivity(new Intent(this, LoginActivity.class));
                }
                break;
        }
    }
}
