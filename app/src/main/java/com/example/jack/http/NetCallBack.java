package com.example.jack.http;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Author:Created by JackCheng
 * Email:17764576259@163.com
 * Time:2015/12/27 19:05
 * Copyright:1.0
 */

//�Զ���ص��ӿ�-------->����ɳ����࣬�ܽ��������
public abstract class NetCallBack extends AsyncHttpResponseHandler {

    //����ʼ
    @Override
    public void onStart() {
        Log.i("NetCqllBack----->","����ʼ");
        super.onStart();
    }

    //����ɹ�
    @Override
    public void onSuccess(String s) {
        Log.i("NetCqllBack----->", "����ɹ�" + s);
        onMySuccess(s);
        super.onSuccess(s);
    }

    //����ʧ��
    @Override
    public void onFailure(Throwable throwable) {
        Log.i("NetCqllBack----->","����ʧ��");
        onMyFailure(throwable);
        super.onFailure(throwable);
    }

    public abstract void onMySuccess(String result);
    public abstract void onMyFailure(Throwable throwable);
}
