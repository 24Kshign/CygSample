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
 * ���ߣ�Created by JackCheng on 2015/9/20 13:12.
 * ���䣺17764576259@163.com
 */
public class SlidingMenu extends HorizontalScrollView {

    private LinearLayout mWapper;
    private ViewGroup mMenu;
    private ViewGroup mContent;
    private int mScreenWidth;    //��Ļ�Ŀ��
    private int mMenuRightPadding = 50;     //�˵�ҳ���Ҳ���Ļ�ľ���,Ĭ��Ϊ50dp
    private int mMenuWidth;          //�˵��Ŀ��
    private boolean once = false; //�����ǲ��ǵ�һ�ε���onMeasure����
    private boolean isopen;      //�жϲ˵��Ƿ��

    /**
     * δʹ���Զ�������ʱ����
     */
    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);  //��ȡ��Ļ����Ϣ
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        mScreenWidth = outMetrics.widthPixels;

        //ͨ��TypedValue������50dpת��Ϊһ������ֵpx
        mMenuRightPadding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50, context.getResources().getDisplayMetrics());
    }

    /**
     * ������view�Ŀ�͸�
     * �����Լ��Ŀ�͸�
     */

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        if (!once) {
            mWapper = (LinearLayout) getChildAt(0);
            //LinearLayout��ĵ�һ��Ԫ�أ�ΪmMenu
            mMenu = (ViewGroup) mWapper.getChildAt(0);
            //LinearLayout��ĵڶ���Ԫ�أ�ΪmContent
            mContent = (ViewGroup) mWapper.getChildAt(1);
            mMenuWidth = mMenu.getLayoutParams().width = mScreenWidth - mMenuRightPadding;
            mContent.getLayoutParams().width = mScreenWidth;
            once = true;
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * ͨ������ƫ��������menu����
     */
    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        //��menu��������
        if (changed) {
            //���˵���ȫ���أ�˲�����
            this.scrollTo(mMenuWidth, 0);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        //ͨ��action���ж��û��Ĳ���
        int action = ev.getAction();
        switch (action) {
            case MotionEvent.ACTION_UP:
                //scollxΪ�����������ǲ���
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
//    //�򿪲˵�
//    public void openMenu() {
//        if (isopen)
//            return;
//        this.smoothScrollTo(0, 0);
//        isopen = true;
//    }
//

    //�жϲ˵��Ƿ��Ǵ򿪵�
    public boolean isOpen() {
        return isopen;
    }

    //�رղ˵�
    public void closeMenu() {
        if (!isopen)
            return;
        this.smoothScrollTo(mMenuWidth, 0);
        isopen = false;
    }
//
//    //�л��˵�
//    public void toggle() {
//        if (isopen)
//            closeMenu();
//        else
//            openMenu();
//    }

}
