package com.example.jack.activity;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.jack.adapter.RecorderAdapter;
import com.example.jack.bean.Recorder;
import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;
import com.example.jack.view.AudioRecorderButton;
import com.example.jack.widget.MediaManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jack on 2015/9/6.
 */

@ContentView(R.layout.activity_chat)
public class ChatActivity extends Activity {

    private ArrayAdapter<Recorder> mAdapter;
    private List<Recorder> mDates = new ArrayList<Recorder>();
    private View mAnimView;

    @ViewInject(R.id.listview)
    private ListView mLisiview;

    @ViewInject(R.id.recorder_button)
    private AudioRecorderButton mAudioRecorderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);   //调用注解
        initeView();
    }

    private void initeView() {
        mAudioRecorderButton.setAudioFinishRecorderListener(new AudioRecorderButton.AudioFinishRecorderListener() {
            @Override
            public void onFinish(float second, String filePath) {
                Recorder recorder = new Recorder(second, filePath);
                mDates.add(recorder);
                mAdapter.notifyDataSetChanged();
                mLisiview.setSelection(mDates.size() - 1);
            }
        });

        mAdapter = new RecorderAdapter(this, mDates);
        mLisiview.setAdapter(mAdapter);
        mLisiview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (mAnimView != null) {
                    mAnimView.setBackgroundResource(R.drawable.adj);
                    mAnimView = null;
                }
                //播放动画
                mAnimView = view.findViewById(R.id.view_recorder_anim);
                mAnimView.setBackgroundResource(R.drawable.play_anim);
                AnimationDrawable anim = (AnimationDrawable) mAnimView.getBackground();
                anim.start();
                //播放音频
                MediaManager.playSound(mDates.get(position).getFilePath(), new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mAnimView.setBackgroundResource(R.drawable.adj);
                    }
                });
            }
        });
    }
}
