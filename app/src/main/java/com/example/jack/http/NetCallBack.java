package com.example.jack.http;

import android.util.Log;

import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * Author:Created by JackCheng
 * Email:17764576259@163.com
 * Time:2015/12/27 19:05
 * Copyright:1.0
 */

//自定义回调接口-------->定义成抽象类，能将结果返回
public abstract class NetCallBack extends AsyncHttpResponseHandler {

    //请求开始
    @Override
    public void onStart() {
        Log.i("NetCqllBack----->","请求开始");
        super.onStart();
    }

    //请求成功
    @Override
    public void onSuccess(String s) {
        Log.i("NetCqllBack----->", "请求成功" + s);
        onMySuccess(s);
        super.onSuccess(s);
    }

    //请求失败
    @Override
    public void onFailure(Throwable throwable) {
        Log.i("NetCqllBack----->","请求失败");
        onMyFailure(throwable);
        super.onFailure(throwable);
    }

    public abstract void onMySuccess(String result);
    public abstract void onMyFailure(Throwable throwable);
}
