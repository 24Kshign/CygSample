package com.example.jack.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * ���ߣ�Created by JackCheng on 2015/9/19 10:30.
 * ���䣺17764576259@163.com
 */

@ContentView(R.layout.activity_webview)
public class WebViewActivity extends BaseActivity {

    @ViewInject(R.id.aw_webview)
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        webView.getSettings().setJavaScriptEnabled(true);      //����setJavaScriptEnabled��WebView֧��JavaScript
        webView.setWebViewClient(new WebViewClient());         //����setWebViewClient����һ��WebViewClientʵ��
        webView.loadUrl("https://www.baidu.com/");
    }
}
