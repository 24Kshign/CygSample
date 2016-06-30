package com.example.jack.view;

import java.io.File;
import java.io.IOException;
import java.util.UUID;


import android.media.MediaRecorder;

public class AudioManager {

    private MediaRecorder mRecorder;
    private String mDirString;       //¼���ļ�
    private String mCurrentFilePathString;

    private boolean isPrepared;      //�Ƿ�׼������

    /**
     * �������ķ��� 1 ������һ��static ���͵ı���a 2 ������Ĭ�ϵĹ��캯�� 3 ����public synchronized static
     * ���� getInstance() { if(a==null) { a=new ��();} return a; } ���������µķ���
     */

    /**
     * �����������
     */
    private static AudioManager mInstance;

    private AudioManager(String dir) {
        mDirString = dir;
    }

    public static AudioManager getInstance(String dir) {
        if (mInstance == null) {
            synchronized (AudioManager.class) {
                if (mInstance == null) {
                    mInstance = new AudioManager(dir);

                }
            }
        }
        return mInstance;

    }

    /**
     * �ص�������׼����ϣ�׼���ú�button�ŻῪʼ��ʾ¼����
     */

    public interface AudioStateListener {
        void wellPrepared();
    }

    public void setOnAudioStageListener(AudioStateListener listener) {
        mListener = listener;
    }

    public AudioStateListener mListener;

    // ׼������
    public void prepareAudio() {
        try {
            // һ��ʼӦ����false��
            isPrepared = false;
            File dir = new File(mDirString);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String fileNameString = generalFileName();
            File file = new File(dir, fileNameString);

            mCurrentFilePathString = file.getAbsolutePath();

            mRecorder = new MediaRecorder();
            // ��������ļ�
            mRecorder.setOutputFile(file.getAbsolutePath());
            // ����meidaRecorder����ƵԴ����˷�
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            // �����ļ���Ƶ�������ʽΪamr
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            // ������Ƶ�ı����ʽΪamr
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            // �ϸ�����google�ٷ�api������mediaRecorder��״̬����ͼ
            mRecorder.prepare();

            mRecorder.start();
            // ׼������
            isPrepared = true;
            // �Ѿ�׼�����ˣ�����¼����
            if (mListener != null) {
                mListener.wellPrepared();
            }

        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * ��������ļ�������
     */
    private String generalFileName() {
        return UUID.randomUUID().toString() + ".amr";
    }

    // ���������level
    public int getVoiceLevel(int maxLevel) {
        // mRecorder.getMaxAmplitude()�������Ƶ�������Χ��ֵ����1-32767
        if (isPrepared) {
            try {
                // ȡ֤+1������ȥ����7
                return maxLevel * mRecorder.getMaxAmplitude() / 32768 + 1;
            } catch (Exception e) {

            }
        }

        return 1;
    }

    // �ͷ���Դ
    public void release() {
        // �ϸ���api���̽���
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;

    }

    // ȡ��,��Ϊprepareʱ������һ���ļ�������cancel����Ӧ��Ҫɾ������ļ���
    // ������release�ķ���������
    public void cancel() {
        release();
        if (mCurrentFilePathString != null) {
            File file = new File(mCurrentFilePathString);
            file.delete();
            mCurrentFilePathString = null;
        }

    }

    public String getCurrentFilePath() {
        return mCurrentFilePathString;
    }
}
