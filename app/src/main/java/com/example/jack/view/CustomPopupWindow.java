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
        //����LayoutInflater�õ�view
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

        //����SelectPicPopupWindow��View
        this.setContentView(mView);
        //����SelectPicPopupWindow��������Ŀ�
        this.setWidth(ViewGroup.LayoutParams.FILL_PARENT);
        //����SelectPicPopupWindow��������ĸ�
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        //����SelectPicPopupWindow��������ɵ��
        this.setFocusable(true);
        //����SelectPicPopupWindow�������嶯��Ч��
        this.setAnimationStyle(R.style.mypopwindow_anim_style);
        //ʵ����һ��ColorDrawable��ɫΪ��͸��
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //����SelectPicPopupWindow��������ı���
        this.setBackgroundDrawable(dw);
        //mView���OnTouchListener���������жϻ�ȡ����λ�������ѡ������������ٵ�����
        mView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //��ȡ�ǵ�ǰView��������ĸ��������Ķ���λ�þ���
                int height = mView.findViewById(R.id.popup_lv_popup).getTop();
                int y = (int) event.getY();
                if (y < height) {
                    dismiss();
                }
                return true;
            }
        });

        //mView���OnTouchListener�����û������ؼ������ٵ�����
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
