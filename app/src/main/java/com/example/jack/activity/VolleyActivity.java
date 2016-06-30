package com.example.jack.activity;

import android.os.Bundle;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInjectUtils;
import com.example.jack.utils.MyApplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Author:Created by JackCheng
 * Email:17764576259@163.com
 * Time:2015/12/27 16:14
 * Copyright:1.0
 */


@ContentView(R.layout.activity_volley)
public class VolleyActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);

        volley_Get();
//        volley_Post();
    }

    //volley的Get请求方式
    private void volley_Get() {
        String url = "";
        //四个参数分别代表：请求的方式，请求的url,请求的回调,错误的回调
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //s为请求成功后返回的结果
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        request.setTag("abcGet");     //给请求设置一个Tag，以方便以后想对这个请求进行取消
        MyApplication.getHttpQueue().add(request);     //将请求添加到请求队列中
    }

    private void volley_Post() {
        String url = "";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //s为请求成功后返回的结果
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("", "");     //添加请求参数
                map.put("", "");
                return map;
            }
        };
        request.setTag("abcPost");     //给请求设置一个Tag，以方便以后想对这个请求进行取消
        MyApplication.getHttpQueue().add(request);     //将请求添加到请求队列中
    }

    //volley与Activity的生命周期关联，当Activity Stop时，请求也停止
    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getHttpQueue().cancelAll("abcGet");    //将指定的含"abcTag"的请求取消掉
    }
}
