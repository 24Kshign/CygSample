package com.example.jack.test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.jack.activity.BaseActivity;
import com.example.jack.activity.R;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * Created by Jack on 16/6/27.
 */
@ContentView(R.layout.activity_t1)
public class T1 extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.at_btn_dialog)
    Button mBtnDialog;
    @ViewInject(R.id.at_btn_popup)
    Button mBtnPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);

        initView();
    }

    private void initView() {
        mBtnDialog.setOnClickListener(this);
        mBtnPopupWindow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.at_btn_dialog:
                showCustomDialog();
                break;
            case R.id.at_btn_popup:
                showCustomPopup(v);
                break;
        }
    }

    private void showCustomDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.create();
        builder.setTitle("AlertDialog");
        builder.setMessage("我是一个alertDialog");
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                setTitle("Cancel");
                builder.create().dismiss();
            }
        });
        builder.show();
    }

    private void showCustomPopup(View v) {
        View view = LayoutInflater.from(this).inflate(R.layout.dialog_custom_popupwindow, null);
        Button button1 = (Button) view.findViewById(R.id.popup_btn_camera);
        Button button2 = (Button) view.findViewById(R.id.popup_btn_album);
        final PopupWindow popupWindow = new PopupWindow(view,
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.drawable.cqh8_background));

        // 设置好参数之后再show
        popupWindow.showAsDropDown(v);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("我是button1");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showToast("我是button2");
                popupWindow.dismiss();
            }
        });
    }
}
