package com.example.jack.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.jack.http.NetCallBack;
import com.example.jack.http.RequestUtils;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInjectUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Author:Created by JackCheng
 * Email:17764576259@163.com
 * Time:2015/12/27 16:45
 * Copyright:1.0
 */

@ContentView(R.layout.activity_async_http)
public class AsyncHttpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);

        asyncHttpGet();
        asyncHttpPost();
    }

    private void asyncHttpGet() {
        //����asynchttpclient�������
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "";
        //���������ĺ��壺�����url������Ļص�
        client.get(url, new AsyncHttpResponseHandler() {
            //����ɹ�
            @Override
            public void onSuccess(String s) {
                //sΪ����ɹ��󷵻صĽ��
            }

            //����ʧ��
            @Override
            public void onFailure(Throwable throwable) {
                super.onFailure(throwable);
            }
        });
    }

    private void asyncHttpPost() {
        //����asynchttpclient�������-------->ԭ����
//        AsyncHttpClient client = new AsyncHttpClient();
        String url = "";
        RequestParams params = new RequestParams();
        params.put("", "");
        params.put("", "");
//        //���������ĺ��壺�����url�����ݵĲ���������Ļص�
//        client.post(url, params, new AsyncHttpResponseHandler() {
//            //����ɹ�
//            @Override
//            public void onSuccess(String s) {
//                //sΪ����ɹ��󷵻صĽ��
//            }
//
//            //����ʧ��
//            @Override
//            public void onFailure(Throwable throwable) {
//                super.onFailure(throwable);
//            }
//        });

        //�Լ���װ��Async-http��
        RequestUtils.ClientPost(url, params, new NetCallBack() {
            //����ɹ�
            @Override
            public void onMySuccess(String result) {
                //resultΪ����ɹ��󷵻صĽ��
            }

            //����ʧ��
            @Override
            public void onMyFailure(Throwable throwable) {

            }
        });
    }
}
