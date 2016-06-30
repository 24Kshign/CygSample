package com.example.jack.widget;

import android.media.AudioManager;
import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by Jack on 2015/9/10.
 */
public class MediaManager {

    private static MediaPlayer mMediaplayer;
    private static boolean isPause;

    public static void playSound(String path, MediaPlayer.OnCompletionListener onCompletionListener) {
        if (mMediaplayer == null) {
            mMediaplayer = new MediaPlayer();
            mMediaplayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    mMediaplayer.reset();
                    return false;
                }
            });
        } else {
            mMediaplayer.reset();
        }
        try {
            mMediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mMediaplayer.setOnCompletionListener(onCompletionListener);
            mMediaplayer.setDataSource(path);
            mMediaplayer.prepare();
            mMediaplayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pause() {
        if (mMediaplayer != null && mMediaplayer.isPlaying()) {
            mMediaplayer.pause();
            isPause = true;
        }
    }

    public void resume() {
        if (mMediaplayer != null && isPause) {
            mMediaplayer.start();
            isPause = false;
        }
    }

    public void release() {
        if (mMediaplayer != null) {
            mMediaplayer.release();
            mMediaplayer = null;
        }
    }
}
