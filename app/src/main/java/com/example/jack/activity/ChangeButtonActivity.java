package com.example.jack.activity;

import android.opengl.Visibility;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * 作者：Created by JackCheng on 2015/9/19 09:49.
 * 邮箱：17764576259@163.com
 */

@ContentView(R.layout.activity_change_button)
public class ChangeButtonActivity extends BaseActivity implements View.OnClickListener{

    @ViewInject(R.id.acb_btn_voice)
    private Button btnVoice;

    @ViewInject(R.id.acb_btn_send_msg)
    private Button btnSendMsg;

    @ViewInject(R.id.acb_btn_plus)
    private Button btnPlus;

    @ViewInject(R.id.acb_btn_express)
    private Button btnExpress;

    @ViewInject(R.id.acb_et_edittext)
    private EditText etEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);    //调用注解

        etEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("程应根", "beforeTextChanged");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                showView(btnSendMsg);
                hideView(btnVoice);
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (etEdit.getText().toString().isEmpty()) {
                    showView(btnVoice);
                    hideView(btnSendMsg);
                }
            }
        });

        initeListener();
    }

    private void initeListener() {
        btnSendMsg.setOnClickListener(this);
        btnVoice.setOnClickListener(this);
        btnPlus.setOnClickListener(this);
        btnExpress.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.acb_btn_send_msg:
                break;
            case R.id.acb_btn_voice:
                break;
            case R.id.acb_btn_plus:
                break;
            case R.id.acb_btn_express:
                break;
        }
    }
}
