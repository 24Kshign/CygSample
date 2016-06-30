package com.example.jack.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jack.activity.R;
import com.example.jack.bean.Recorder;

import java.util.List;

/**
 * Created by Jack on 2015/9/10.
 */
public class RecorderAdapter extends ArrayAdapter<Recorder> {
    private int mMinItemWidth;    //对话框的最小长度
    private int mMaxItemWidth;   //对话框的最大宽度
    private LayoutInflater mLayoutInflater;

    public RecorderAdapter(Context context, List<Recorder> datas) {
        super(context, -1, datas);
        mLayoutInflater = LayoutInflater.from(context);

        //获取系统的服务
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        //通过系统的服务来获取屏幕的大小信息
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);

        mMaxItemWidth = (int) (outMetrics.widthPixels * 0.7f);
        mMinItemWidth = (int) (outMetrics.widthPixels * 0.15f);
    }

    /**
     * 控制recorder_item的显示
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewholder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_recorder_chat_msg, parent, false);
            viewholder = new ViewHolder();
            viewholder.seconds = (TextView) convertView.findViewById(R.id.tv_recorder_time);
            viewholder.length = convertView.findViewById(R.id.view_recorder_anim);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.seconds.setText(Math.round(getItem(position).getTime()) + "\"");
        ViewGroup.LayoutParams lp = viewholder.length.getLayoutParams();
        lp.width = (int) (mMinItemWidth + (mMaxItemWidth / 60f * getItem(position).getTime()));
        return convertView;
    }

    private class ViewHolder {
        private TextView seconds;
        private View length;
    }
}
