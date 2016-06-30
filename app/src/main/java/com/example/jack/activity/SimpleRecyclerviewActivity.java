package com.example.jack.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInjectUtils;

/**
 * Created by Jack on 16/6/30.
 */

@ContentView(R.layout.activity_simple_recycler)
public class SimpleRecyclerviewActivity extends BaseActivity {

    public static final String TAG = "SimpleRecyclerView";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);

        Configuration configuration = getResources().getConfiguration();
        Log.d(TAG, "国家码为－－－－－－>" + configuration.mcc);
        Log.d(TAG, "网络码为－－－－－－>" + configuration.mnc);
        if (configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Log.d(TAG, "纵向屏幕");
        } else {
            Log.d(TAG, "横向屏幕");
        }
    }
}