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
 * �Զ���һ����ס˵���İ�ť
 */
public class AudioRecorderButton extends Button implements AudioStateListener {

    private static final int DISTANCE_Y_CANCEL = 50;      //��ʾ�ɿ���ָ����y������پ���ȡ��¼��
    private static final int STATE_NORMAL = 1;          //Ĭ��״̬
    private static final int STATE_RECORDERING = 2;   //¼��״̬
    private static final int STATE_WANT_CANCEL = 3;   //����¼��״̬

    private int mCurState = STATE_NORMAL;        //��¼��ǰ״̬

    private boolean isRecordering = false;        //�ж��Ƿ��Ѿ���ʼ¼������ʱ�����1s
    private boolean isAeady;     //��ʾ�Ƿ񴥷�onLongClick

    private float mTime;        //��������¼����ʱ��

    private DialogManager mDialogManager;
    private AudioManager mAudioManager;
    private String dir;


    public AudioRecorderButton(Context context) {
        this(context, null);
    }

    public AudioRecorderButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDialogManager = new DialogManager(getContext());

        boolean sdCardExist = Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);   //�ж�sd���Ƿ����
        if (sdCardExist) {
            dir = Environment.getExternalStorageDirectory() + "/recode_raudio";    //�ڴ洢���ĸ�Ŀ¼�´���һ���ļ���
        } else {
            Toast.makeText(getContext(), "sd�������ڣ������¼��", Toast.LENGTH_SHORT).show();
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

    //¼����ɺ�Ļص�
    public interface AudioFinishRecorderListener {
        void onFinish(float second, String filePath);
    }

    private AudioFinishRecorderListener mListener;

    public void setAudioFinishRecorderListener(AudioFinishRecorderListener listener) {
        mListener = listener;
    }

    //��ȡ������С��Runnable
    private Runnable mGetVoiceLevelRunnable = new Runnable() {
        @Override
        public void run() {
            while (isRecordering) {
                try {
                    Thread.sleep(100);       //ÿ��0.1���ȡһ��
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
                    //��ʾӦ����audio end prepapre�Ժ�
                    mDialogManager.showRecordingDialog();
                    isRecordering = true;
                    //����һ���߳�����ȡ�����ı仯
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

        int action = event.getAction();    //��ȡ��ǰ��action
        //��ȡ��ǰ������
        int x = (int) event.getX();
        int y = (int) event.getY();

        //switch���е�action�����ж�ÿ��action��ִ��ʲô
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                changeState(STATE_RECORDERING);      //����ָ���µ�ʱ�򣬸ı䵱ǰ��״̬
                break;
            case MotionEvent.ACTION_MOVE:
                if (isRecordering) {
                    //����x, y���������ж��û��Ƿ���Ҫ����
                    if (wangToCancel(x, y)) {      //�����Ҫ����
                        changeState(STATE_WANT_CANCEL);
                    } else {           //����������
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
                } else if (mCurState == STATE_RECORDERING) {      //��ʾ����¼����ʱ����ָ�ɿ�����ʱ��Ҫ����¼�������ͳ�ȥ
                    mDialogManager.dimissDialog();
                    mAudioManager.release();
                    if (mListener != null) {
                        mListener.onFinish(mTime, mAudioManager.getCurrentFilePath());     //�ص�ʱ��͵�ǰ¼�������λ��
                    }
                } else if (mCurState == STATE_WANT_CANCEL) {   //��ʾ��Ҫ������ʱ���ɿ�
                    mDialogManager.dimissDialog();
                    mAudioManager.cancel();
                }
                reset();     //���û�̧����ָʱ����ʾ��ι����Ѿ��������������������������һ��¼��
                break;

        }

        return super.onTouchEvent(event);
    }

    //�����ָ�״̬����־λ��һ�ж��ع鵽��ԭʼ
    private void reset() {
        isRecordering = false;
        mTime = 0;
        isAeady = false;
        changeState(STATE_NORMAL);
    }

    //�ж��û��Ƿ���Ҫ����
    private boolean wangToCancel(int x, int y) {
        if (x < 0 || x > getWidth()) {        //�ж���ָ�������Ƿ񳬳���ť
            return true;
        }
        if (y < -DISTANCE_Y_CANCEL || y > getHeight() + DISTANCE_Y_CANCEL) {      //�ж���ָ�������Ƿ񳬳���ť�·��������Ϸ�50�ľ���
            return true;
        }
        return false;
    }

    //�ı�״̬��һ������
    private void changeState(int state) {
        if (mCurState != state) {          //�������state�����ڵ�ǰ״̬ʱ��
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
