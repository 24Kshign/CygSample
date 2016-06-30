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

    private long duration = 350;    //����ʱ��
    private Context mContext;     //������
    private TextView mNumberTextView;  //�����������
    private TextView mTitleTextView;    //�м��������
    private RelativeLayout mContentRelativeLayout;   //�������ص�layout
    private RelativeLayout mTitleRelativeLayout;    //������ʾ��layout
    private ImageView mArrowImageView;      //�ұ߼�ͷ
    int parentWidthMeasureSpec;
    int parentHeightMeasureSpec;

    public CollapseView(Context context) {
        super(context, null);
    }

    public CollapseView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

}
