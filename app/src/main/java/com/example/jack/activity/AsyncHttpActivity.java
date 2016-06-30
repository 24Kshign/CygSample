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
        //创建asynchttpclient请求对象
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "";
        //两个参数的含义：请求的url，请求的回调
        client.get(url, new AsyncHttpResponseHandler() {
            //请求成功
            @Override
            public void onSuccess(String s) {
                //s为请求成功后返回的结果
            }

            //请求失败
            @Override
            public void onFailure(Throwable throwable) {
                super.onFailure(throwable);
            }
        });
    }

    private void asyncHttpPost() {
        //创建asynchttpclient请求对象-------->原生的
//        AsyncHttpClient client = new AsyncHttpClient();
        String url = "";
        RequestParams params = new RequestParams();
        params.put("", "");
        params.put("", "");
//        //三个参数的含义：请求的url，传递的参数，请求的回调
//        client.post(url, params, new AsyncHttpResponseHandler() {
//            //请求成功
//            @Override
//            public void onSuccess(String s) {
//                //s为请求成功后返回的结果
//            }
//
//            //请求失败
//            @Override
//            public void onFailure(Throwable throwable) {
//                super.onFailure(throwable);
//            }
//        });

        //自己封装的Async-http类
        RequestUtils.ClientPost(url, params, new NetCallBack() {
            //请求成功
            @Override
            public void onMySuccess(String result) {
                //result为请求成功后返回的结果
            }

            //请求失败
            @Override
            public void onMyFailure(Throwable throwable) {

            }
        });
    }
}
