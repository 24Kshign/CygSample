package com.example.jack.activity;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SlidingPaneLayout;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.jack.fragment.ContentFragment;
import com.example.jack.fragment.MenuFragment;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * 作者：Created by JackCheng on 2015/9/20 15:38.
 * 邮箱：17764576259@163.com
 */

@ContentView(R.layout.activity_sliding_menu)
public class SlidindMenuActivity extends Activity {

    private SlidingPaneLayout slidingPaneLayout;
    private MenuFragment menuFragment;
    private ContentFragment contentFragment;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private int maxMargin = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        initeSlidingMenu();
    }

    private void initeSlidingMenu() {
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        slidingPaneLayout = (SlidingPaneLayout) findViewById(R.id.slidingpanellayout);
        menuFragment = new MenuFragment();
        contentFragment = new ContentFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.slidingpane_menu, menuFragment);
        transaction.replace(R.id.slidingpane_content, contentFragment);
        transaction.commit();

        slidingPaneLayout.setPanelSlideListener(new SlidingPaneLayout.PanelSlideListener() {

            @Override
            public void onPanelSlide(View panel, float slideOffset) {
                int contentMargin = (int) (slideOffset * maxMargin);
                FrameLayout.LayoutParams contentParams = contentFragment
                        .getCurrentViewParams();
                contentParams.setMargins(0, contentMargin, 0, contentMargin);
                contentFragment.setCurrentViewPararms(contentParams);

                float scale = 1 - ((1 - slideOffset) * maxMargin * 2)
                        / (float) displayMetrics.heightPixels;
                menuFragment.getCurrentView().setScaleX(scale);// 设置缩放的基准点
                menuFragment.getCurrentView().setScaleY(scale);// 设置缩放的基准点
                menuFragment.getCurrentView().setPivotX(0);// 设置缩放和选择的点
                menuFragment.getCurrentView().setPivotY(
                        displayMetrics.heightPixels / 2);
                menuFragment.getCurrentView().setAlpha(slideOffset);
            }

            @Override
            public void onPanelOpened(View panel) {
            }

            @Override
            public void onPanelClosed(View panel) {
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0 && slidingPaneLayout.isOpen()) {
            slidingPaneLayout.closePane();
        } else if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
        }
        return false;
    }
}
