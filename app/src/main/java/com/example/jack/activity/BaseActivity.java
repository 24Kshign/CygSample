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

    //根据输入框中的内容是否为空来判断按钮是否显示
    protected void buttonShowOrNot(EditText editText, Button button) {
        String str = editText.getText().toString();
        if (!str.isEmpty()) {
            showView(button);
        } else {
            hideView(button);
        }
    }

    //隐藏控件
    protected void hideView(View v) {
        v.setVisibility(v.INVISIBLE);
    }

    //显示控件
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

    //显示进度条
    protected void showDialog(ProgressDialog dialog) {
        //判断进度条是否正在显示
        if (!dialog.isShowing()) {
            dialog.show();
        }
    }

    //隐藏进度条
    protected void hideDialog(ProgressDialog dialog) {
        //判断进度条是否正在显示
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    //让按钮不可点击
    protected void disableButton(Button button) {
        button.setEnabled(false);
    }

    //让按钮不可点击
    protected void enableButton(Button button) {
        button.setEnabled(true);
    }

    /**
     *判断如果数组中的所有标记是不是为非空
     *如果有一个标记为空，则返回false，否则返回true
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
     *根据EditText文本内容是否为空判断是否disable按钮
     *如果有一个EditText文本内容为空，则disable按钮
     */
    protected void enableButtonOrNot(final Button btn, final EditText... editText) {
        int length=editText.length;   //表示传入edittext的个数
        final boolean[] flag=new boolean[length];   //表示各个edittext是否为空，false表示为空
        for (int i=0; i<length;i++) {
            final int flagI=i;
            editText[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    Log.v("出去嗨", "beforeTextChanged------");
                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    Log.v("出去嗨", "onTextChanged------");
                    //判断当前edittext中的内容是否为空
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
                    Log.v("出去嗨", "afterTextChanged------");
                }
            });
        }
    }

    //监听输入框中输入了多少内容
    //length表示该输入框限制输入的字数
    protected void limitTextLength(final EditText et_edit, final int length, final TextView tv_text) {
        et_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.v("出去嗨", "beforeTextChanged------");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.v("出去嗨", "onTextChanged------");
                String str = et_edit.getText().toString();       //保存输入框中的内容
                int textLength = str.length();           //获取输入框中当前的字符长度
                int leftLength = length - textLength;      //获取输入框中剩余可输入字符的长度
                tv_text.setText(leftLength + "");
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.v("出去嗨", "afterTextChanged------");
            }
        });
    }

}
