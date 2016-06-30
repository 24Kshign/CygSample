package com.example.jack.activity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInjectUtils;
import com.example.jack.view.CustomProgressDialog;

/**
 * Created by Jack on 2015/9/11.
 */
@ContentView(R.layout.activity_progress_dialog)
public class ProgressActivity extends Activity {

    private CustomProgressDialog progressDialog = null;
    private MainFrameTask mMainFrameTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        ViewInjectUtils.inject(this);     //调用注解

        mMainFrameTask = new MainFrameTask(this);
        mMainFrameTask.execute();
    }

    @Override
    protected void onDestroy() {
        stopProgressDialog();
        if (mMainFrameTask != null && !mMainFrameTask.isCancelled()) {
            mMainFrameTask.cancel(true);
        }
        super.onDestroy();
    }

    private void startProgressDialog() {
        if (progressDialog == null) {
            progressDialog = CustomProgressDialog.createDialog(this);
            progressDialog.setMessage("正在加载中...");
        }
        progressDialog.show();
    }

    private void stopProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    public class MainFrameTask extends AsyncTask<Integer, String, Integer> {
        private ProgressActivity progress = null;

        public MainFrameTask(ProgressActivity progress) {
            this.progress = progress;
        }

        @Override
        protected void onCancelled() {
            stopProgressDialog();
            super.onCancelled();
        }

        @Override
        protected Integer doInBackground(Integer... params) {
            try {
                Thread.sleep(10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            startProgressDialog();
        }

        @Override
        protected void onPostExecute(Integer result) {
            stopProgressDialog();
        }
    }
}
