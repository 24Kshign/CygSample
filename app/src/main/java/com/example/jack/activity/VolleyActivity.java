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

    //volley��Get����ʽ
    private void volley_Get() {
        String url = "";
        //�ĸ������ֱ��������ķ�ʽ�������url,����Ļص�,����Ļص�
        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //sΪ����ɹ��󷵻صĽ��
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        });
        request.setTag("abcGet");     //����������һ��Tag���Է����Ժ��������������ȡ��
        MyApplication.getHttpQueue().add(request);     //��������ӵ����������
    }

    private void volley_Post() {
        String url = "";
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                //sΪ����ɹ��󷵻صĽ��
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("", "");     //����������
                map.put("", "");
                return map;
            }
        };
        request.setTag("abcPost");     //����������һ��Tag���Է����Ժ��������������ȡ��
        MyApplication.getHttpQueue().add(request);     //��������ӵ����������
    }

    //volley��Activity���������ڹ�������Activity Stopʱ������Ҳֹͣ
    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getHttpQueue().cancelAll("abcGet");    //��ָ���ĺ�"abcTag"������ȡ����
    }
}
