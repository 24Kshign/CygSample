package com.example.jack.utils;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Author:Created by JackCheng
 * Email:17764576259@163.com
 * Time:2015/12/27 16:19
 * Copyright:1.0
 */
public class MyApplication extends Application {

    //����Volley���������
    public static RequestQueue queue;

    @Override
    public void onCreate() {
        super.onCreate();
        queue = Volley.newRequestQueue(getApplicationContext());
    }

    /**
     * ����������У����ڻ�ȡȫ�ֵ��������
     * @return
     */
    public static RequestQueue getHttpQueue(){
        return queue;
    }
}
