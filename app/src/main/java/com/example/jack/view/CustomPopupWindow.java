package com.example.jack.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.jack.activity.R;

/**
 * Created by Jack on 2015/9/10.
 */
public class CustomPopupWindow extends PopupWindow {

    private Button mBtnCamera;
    private Button mBtnAlbum;
    private Button mBtnCancel;

    private View mView;

    public CustomPopupWindow(Activity context, View.OnClickListener itemsOnClick) {
        super(context);
        //利用LayoutInflater得到view
        LayoutInflater inflater = LayoutInflater.from(context);
        mView = inflater.inflate(R.layout.dialog_custom_popupwindow, null);
        initeView();

        mBtnCamera.setOnClickListener(itemsOnClick);
        mBtnAlbum.setOnClickListener(itemsOnClick);
        mBtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        initePopupWindow();

    }

    private void initePopupWindow() {

        //设置SelectPicPopupWindow的View
        this.setContentView(mView);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);
        //mView添加OnTouchListener监听用来判断获取触屏位置如果在选择框外面则销毁弹出框
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //获取是当前View相对于它的父类容器的顶部位置距离
                int height = mView.findViewById(R.id.popup_lv_popup).getTop();
                int y = (int) event.getY();
                if (y < height) {
                    dismiss();
                }
                return true;
            }
        });

        //mView添加OnTouchListener监听用户按返回键来销毁弹出框
        mView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });
    }

    private void initeView() {
        mBtnCamera = (Button) mView.findViewById(R.id.popup_btn_camera);
        mBtnAlbum = (Button) mView.findViewById(R.id.popup_btn_album);
        mBtnCancel = (Button) mView.findViewById(R.id.popup_btn_cancel);
    }
}
