package com.example.jack.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;
import com.example.jack.view.SlidingMenu;

/**
 * 作者：Created by JackCheng on 2015/9/20 14:13.
 * 邮箱：17764576259@163.com
 */

@ContentView(R.layout.activity_slide)
public class SlideMenuActivity extends Activity {

    @ViewInject(R.id.menu)
    private SlidingMenu mLeftMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 && mLeftMenu.isOpen()) {
            mLeftMenu.closeMenu();
        } else if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {      //否者的话返回桌面
            finish();
            return false;
        }
        return false;
    }
}
