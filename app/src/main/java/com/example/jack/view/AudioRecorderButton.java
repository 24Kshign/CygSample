package com.example.jack.view;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.jack.activity.R;
import com.example.jack.view.AudioManager.AudioStateListener;

/**
 * Created by Jack on 2015/9/9.
 * 自定义一个按住说话的按钮
 */
public class AudioRecorderButton extends Button implements AudioStateListener {

    private static final int DISTANCE_Y_CANCEL = 50;      //表示松开手指滑动y方向多少距离取消录音
    private static final int STATE_NORMAL = 1;          //默认状态
    private static final int STATE_RECORDERING = 2;   //录音状态
    private static final int STATE_WANT_CANCEL = 3;   //放弃录音状态

    private int mCurState = STATE_NORMAL;        //记录当前状态

    private boolean isRecordering = false;        //判断是否已经开始录音——时间大于1s
    private boolean isAeady;     //表示是否触发onLongClick

    private float mTime;        //用来计算录音的时间

    private DialogManager mDialogManager;
    private AudioManager mAudioManager;
    private String dir;


    public AudioRecorderButton(Context context) {
        this(context, null);
    }

    public AudioRecorderButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDialogManager = new DialogManager(getContext());

        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if (sdCardExist) {
            dir = Environment.getExternalStorageDirectory() + "/recode_raudio";    //在存储卡的根目录下创建一个文件夹
        } else {
            Toast.makeText(getContext(), "sd卡不存在，请重新检测", Toast.LENGTH_SHORT).show();
        }

        mAudioManager = AudioManager.getInstance(dir);
        mAudioManager.setOnAudioStageListener(this);

        setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                isAeady = true;
                mAudioManager.prepareAudio();
                return false;
            }
        });
    }

    //录音完成后的回调
    public interface AudioFinishRecorderListener {
        void onFinish(float second, String filePath);
    }

    private AudioFinishRecorderListener mListener;

    public void setAudioFinishRecorderListener(AudioFinishRecorderListener listener) {
        mListener = listener;
    }

    //获取音量大小的Runnable
    private Runnable mGetVoiceLevelRunnable = new Runnable() {
        @Override
        public void run() {
            while (isRecordering) {
                try {
                    Thread.sleep(100);       //每隔0.1秒获取一次
                    mTime += 0.1f;
                    mHandler.sendEmptyMessage(MSG_VOICE_CHANGE);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private static final int MSG_AUDIO_PREPARED = 0x110;
    private static final int MSG_VOICE_CHANGE = 0x111;
    private static final int MSG_DIALOG_DIMISS = 0x112;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_AUDIO_PREPARED:
                    //显示应该在audio end prepapre以后
                    mDialogManager.showRecordingDialog();
                    isRecordering = true;
                    //开启一个线程来获取音量的变化
                    new Thread(mGetVoiceLevelRunnable).start();
                    break;
                case MSG_VOICE_CHANGE:
                    mDialogManager.updateVoiceLevel(mAudioManager.getVoiceLevel(7));
                    break;
                case MSG_DIALOG_DIMISS:
                    mDialogManager.dimissDialog();
                    break;
            }
        }
    };

    @Override
    public void wellPrepared() {
        mHandler.sendEmptyMessage(MSG_AUDIO_PREPARED);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();    //获取当前的action
        //获取当前的坐标
        int x = (int) event.getX();
        int y = (int) event.getY();

        //switch所有的action，来判断每个action该执行什么
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                changeState(STATE_RECORDERING);      //当手指按下的时候，改变当前的状态
                break;
            case MotionEvent.ACTION_MOVE:
                if (isRecordering) {
                    //根据x, y的坐标来判断用户是否想要放弃
                    if (wangToCancel(x, y)) {      //如果想要放弃
                        changeState(STATE_WANT_CANCEL);
                    } else {           //如果不想放弃
                        changeState(STATE_RECORDERING);
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!isAeady) {
                    reset();
                    return super.onTouchEvent(event);
                }
                if (!isRecordering || mTime < 0.6f) {
                    mDialogManager.timeTooShort();
                    mAudioManager.cancel();
                    mHandler.sendEmptyMessageDelayed(MSG_DIALOG_DIMISS, 1300);
                } else if (mCurState == STATE_RECORDERING) {      //表示正在录音的时候手指松开，这时候要保存录音并发送出去
                    mDialogManager.dimissDialog();
                    mAudioManager.release();
                    if (mListener != null) {
                        mListener.onFinish(mTime, mAudioManager.getCurrentFilePath());     //回调时间和当前录音保存的位置
                    }
                } else if (mCurState == STATE_WANT_CANCEL) {   //表示想要放弃的时候松开
                    mDialogManager.dimissDialog();
                    mAudioManager.cancel();
                }
                reset();     //当用户抬起手指时，表示这段过程已经结束，调用这个方法来进行下一次录音
                break;

        }

        return super.onTouchEvent(event);
    }

    //用来恢复状态及标志位，一切都回归到最原始
    private void reset() {
        isRecordering = false;
        mTime = 0;
        isAeady = false;
        changeState(STATE_NORMAL);
    }

    //判断用户是否想要放弃
    private boolean wangToCancel(int x, int y) {
        if (x < 0 || x > getWidth()) {        //判断手指横坐标是否超出按钮
            return true;
        }
        if (y < -DISTANCE_Y_CANCEL || y > getHeight() + DISTANCE_Y_CANCEL) {      //判断手指纵坐标是否超出按钮下方，或者上方50的距离
            return true;
        }
        return false;
    }

    //改变状态的一个方法
    private void changeState(int state) {
        if (mCurState != state) {          //当传入的state不等于当前状态时，
            mCurState = state;
            switch (state) {
                case STATE_NORMAL:
                    setBackgroundResource(R.drawable.btn_recorder_normal);
                    setText(R.string.str_recorder_normal);
                    break;
                case STATE_RECORDERING:
                    setBackgroundResource(R.drawable.btn_recorder_recordering);
                    setText(R.string.str_recorde_recordering);
                    if (isRecordering) {
                        mDialogManager.recordering();
                    }
                    break;
                case STATE_WANT_CANCEL:
                    setBackgroundResource(R.drawable.btn_recorder_recordering);
                    setText(R.string.str_recorder_want_cancel);
                    mDialogManager.wnatToCancel();
                    break;
            }
        }
    }
}
