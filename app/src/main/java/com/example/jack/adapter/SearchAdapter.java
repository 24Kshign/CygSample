package com.example.jack.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.jack.bean.SearchPerson;

import java.util.List;

/**
 * ×÷Õß£ºCreated by JackCheng on 2015/10/21 21:53.
 * ÓÊÏä£º17764576259@163.com
 */
public class SearchAdapter extends BaseAdapter implements Filterable {

    private List<SearchPerson> list;

    private Context context;

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public Filter getFilter() {
        return null;
    }
}
