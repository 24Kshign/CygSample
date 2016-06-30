package com.example.jack.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jack on 16/6/30.
 */
public class CollapseView extends LinearLayout {

    private long duration = 350;    //持续时间
    private Context mContext;     //上下文
    private TextView mNumberTextView;  //左边数字文字
    private TextView mTitleTextView;    //中间标题文字
    private RelativeLayout mContentRelativeLayout;   //下面隐藏的layout
    private RelativeLayout mTitleRelativeLayout;    //上面显示的layout
    private ImageView mArrowImageView;      //右边箭头
    int parentWidthMeasureSpec;
    int parentHeightMeasureSpec;

    public CollapseView(Context context) {
        super(context, null);
    }

    public CollapseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
