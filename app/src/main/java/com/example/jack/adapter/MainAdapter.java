package com.example.jack.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.jack.bean.MainBean;
import com.example.jack.activity.R;

import java.util.List;

/**
 * Created by Jacky on 2015/8/26.
 */
public class MainAdapter extends BaseAdapter {

    private List<MainBean> mList;

    private LayoutInflater mInflater;      //用来获取item_list的布局，获取整个View的一个变量

    public MainAdapter(Context context, List<MainBean> list) {
        mList = list;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_xlistview, null);      //获取到布局文件
            //获取布局文件中的各个控件的id
            viewHolder.iv_image = (ImageView) convertView.findViewById(R.id.ix_iv_image);
            viewHolder.tv_title = (TextView) convertView.findViewById(R.id.ix_tv_title);
            viewHolder.tv_content = (TextView) convertView.findViewById(R.id.ix_tv_content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // 设置item显示的内容，即我们存放在动态数组的数据
        MainBean bean = mList.get(position);
        viewHolder.iv_image.setImageResource(bean.beanImgRes);
        viewHolder.tv_title.setText(bean.beanStringTitle);
        viewHolder.tv_content.setText(bean.beanStringContent);
        return convertView;
    }

    class ViewHolder {
        public ImageView iv_image;
        public TextView tv_title;
        public TextView tv_content;
    }
}
