package com.example.jack.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

/**
 * 作者：Created by JackCheng on 2015/9/20 13:12.
 * 邮箱：17764576259@163.com
 */
public class SlidingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mScreenWidth;    //屏幕的宽度
    private int mMenuRightPadding = 50;     //菜单页与右侧屏幕的距离,默认为50dp
    private int mMenuWidth;          //菜单的宽度
    private boolean once = false; //设置是不是第一次调用onMeasure方法
    private boolean isopen;      //判断菜单是否打开

    /**
     * 未使用自定义属性时调用
     */
    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);  //获取屏幕的信息
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;

        //通过TypedValue方法将50dp转化为一个像素值px
        mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
    }

    /**
     * 设置子view的宽和高
     * 设置自己的宽和高
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!once) {
            mWapper = (LinearLayout) getChildAt(0);
            //LinearLayout里的第一个元素，为mMenu
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            //LinearLayout里的第二个元素，为mContent
            mContent = (ViewGroup) mWapper.getChildAt(1);
            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 通过设置偏移量，将menu隐藏
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //将menu进行隐藏
        if (changed) {
            //将菜单完全隐藏，瞬间完成
            this.scrollTo(mMenuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //通过action来判断用户的操作
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                //scollx为隐藏在左侧的那部分
                int scollx = getScrollX();
                if (scollx >= mMenuWidth / 2) {
                    this.smoothScrollTo(mMenuWidth, 0);
                    isopen = false;
                } else {
                    this.smoothScrollTo(0, 0);
                    isopen = true;
                }
                return true;
        }
        return super.onTouchEvent(ev);
    }
//    //打开菜单
//    public void openMenu() {
//        if (isopen)
//            return;
//        this.smoothScrollTo(0, 0);
//        isopen = true;
//    }
//

    //判断菜单是否是打开的
    public boolean isOpen() {
        return isopen;
    }

    //关闭菜单
    public void closeMenu() {
        if (!isopen)
            return;
        this.smoothScrollTo(mMenuWidth, 0);
        isopen = false;
    }
//
//    //切换菜单
//    public void toggle() {
//        if (isopen)
//            closeMenu();
//        else
//            openMenu();
//    }

}
