package com.example.jack.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * 作者：Created by JackCheng on 2015/9/19 10:30.
 * 邮箱：17764576259@163.com
 */

@ContentView(R.layout.activity_webview)
public class WebViewActivity extends BaseActivity {

    @ViewInject(R.id.aw_webview)
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        webView.getSettings().setJavaScriptEnabled(true);      //调用setJavaScriptEnabled让WebView支持JavaScript
        webView.setWebViewClient(new WebViewClient());         //调用setWebViewClient传入一个WebViewClient实例
        webView.loadUrl("https://www.baidu.com/");
    }
}
