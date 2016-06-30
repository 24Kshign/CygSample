package com.example.jack.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * Created by Jack on 2015/9/6.
 */

@ContentView(R.layout.activity_viewflipper)
public class ViewFlipperActivity extends Activity implements GestureDetector.OnGestureListener {

    @ViewInject(R.id.viewflipper)
    private ViewFlipper viewFlipper;

    private GestureDetector gusture; //手势检测

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);   //调用注解
        gusture = new GestureDetector(this);
        viewFlipper.addView(getImageView(R.drawable.a));
        viewFlipper.addView(getImageView(R.drawable.b));
        viewFlipper.addView(getImageView(R.drawable.c));
        viewFlipper.addView(getImageView(R.drawable.e));
    }

    private ImageView getImageView(int id) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(id);
        return imageView;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.gusture.onTouchEvent(event);      //touch事件交给手势处理
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getX() - e2.getX() > 100) {
            viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_in));
            viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_left_out));

            viewFlipper.showNext();//向右滑动
            return true;
        } else if (e1.getX() - e2.getY() < -100) {
            viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
            viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.push_right_in));
            viewFlipper.showPrevious();//向左滑动
            return true;
        }
        return false;
    }
}
