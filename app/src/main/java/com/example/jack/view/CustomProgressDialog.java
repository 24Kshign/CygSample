package com.example.jack.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jack.activity.R;

/**
 * Created by Jack on 2015/9/11.
 */
public class CustomProgressDialog extends Dialog {
    private Context context;
    private static CustomProgressDialog customProgressDialog = null;

    public CustomProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    public CustomProgressDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static CustomProgressDialog createDialog(Context context) {
        customProgressDialog = new CustomProgressDialog(context, R.style.CustomProgressDialog);        //设置弹出窗的风格
        customProgressDialog.setContentView(R.layout.custom_progress_dialog);        //设置弹窗的内容
        customProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;    //设置弹出时的位置剧中
        return customProgressDialog;
    }

    public void onWindowFocousChanged(boolean hasFocous) {
        if (customProgressDialog == null) {
            return;
        }
        ImageView iv_loading = (ImageView) customProgressDialog.findViewById(R.id.progress_iv_loading);
        AnimationDrawable ad = (AnimationDrawable) iv_loading.getBackground();
        ad.start();
    }

    //设置标题
    public CustomProgressDialog setTitle(String title) {
        return customProgressDialog;
    }

    //设置内容
    public CustomProgressDialog setMessage(String message) {
        TextView tv_text = (TextView) customProgressDialog.findViewById(R.id.progress_tv_text);
        if (tv_text != null) {
            tv_text.setText(message);
        }
        return customProgressDialog;
    }
}
