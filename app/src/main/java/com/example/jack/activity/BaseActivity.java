package com.example.jack.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jacky on 2015/8/25.
 */
public class BaseActivity extends Activity {

    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //����������е������Ƿ�Ϊ�����жϰ�ť�Ƿ���ʾ
    protected void buttonShowOrNot(EditText editText, Button button) {
        String str = editText.getText().toString();
        if (!str.isEmpty()) {
            showView(button);
        } else {
            hideView(button);
        }
    }

    //���ؿؼ�
    protected void hideView(View v) {
        v.setVisibility(v.INVISIBLE);
    }

    //��ʾ�ؼ�
    protected void showView(View v) {
        v.setVisibility(v.VISIBLE);
    }

    protected void showToast(String str) {
        if(mToast == null) {
            mToast = Toast.makeText(this, str, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(str);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }

    //��ʾ������
    protected void showDialog(ProgressDialog dialog) {
        //�жϽ������Ƿ�������ʾ
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    //���ؽ�����
    protected void hideDialog(ProgressDialog dialog) {
        //�жϽ������Ƿ�������ʾ
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    //�ð�ť���ɵ��
    protected void disableButton(Button button) {
        button.setEnabled(false);
    }

    //�ð�ť���ɵ��
    protected void enableButton(Button button) {
        button.setEnabled(true);
    }

    /**
     *�ж���������е����б���ǲ���Ϊ�ǿ�
     *�����һ�����Ϊ�գ��򷵻�false�����򷵻�true
     */
    private boolean allNotEmpty(boolean[] flag) {
        for (int i=0; i<flag.length; i++) {
            if (flag[i]==false) {
                return true;
            }
        }
        return false;
    }

    /**
     *����EditText�ı������Ƿ�Ϊ���ж��Ƿ�disable��ť
     *�����һ��EditText�ı�����Ϊ�գ���disable��ť
     */
    protected void enableButtonOrNot(final Button btn, final EditText... editText) {
        int length=editText.length;   //��ʾ����edittext�ĸ���
        final boolean[] flag=new boolean[length];   //��ʾ����edittext�Ƿ�Ϊ�գ�false��ʾΪ��
        for (int i=0; i<length;i++) {
            final int flagI=i;
            editText[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.v("��ȥ��", "beforeTextChanged------");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.v("��ȥ��", "onTextChanged------");
                    //�жϵ�ǰedittext�е������Ƿ�Ϊ��
                    if (editText[flagI].getText().toString().length()==0) {
                        flag[flagI]=false;
                    }else {
                        flag[flagI]=true;
                    }
                    if (allNotEmpty(flag)) {
                        enableButtonOrNot(btn);
                    }else {
                        disableButton(btn);
                    }
                }

                @Override
                public void afterTextChanged(Editable s) {
                    Log.v("��ȥ��", "afterTextChanged------");
                }
            });
        }
    }

    //����������������˶�������
    //length��ʾ��������������������
    protected void limitTextLength(final EditText et_edit, final int length, final TextView tv_text) {
        et_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.v("��ȥ��", "beforeTextChanged------");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.v("��ȥ��", "onTextChanged------");
                String str = et_edit.getText().toString();       //����������е�����
                int textLength = str.length();           //��ȡ������е�ǰ���ַ�����
                int leftLength = length - textLength;      //��ȡ�������ʣ��������ַ��ĳ���
                tv_text.setText(leftLength + "");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.v("��ȥ��", "afterTextChanged------");
            }
        });
    }

}
