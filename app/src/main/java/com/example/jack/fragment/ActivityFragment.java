package com.example.jack.fragment;

import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.jack.activity.ChatActivity;
import com.example.jack.activity.ListFilterActivity;
import com.example.jack.activity.ProgressActivity;
import com.example.jack.activity.R;
import com.example.jack.activity.SaveCacheActivity;
import com.example.jack.activity.SlideMenuActivity;
import com.example.jack.activity.ViewFlipperActivity;
import com.example.jack.activity.ViewPagerPagingActivity;
import com.example.jack.view.CustomDialog;
import com.example.jack.view.CustomPopupWindow;
import com.example.jack.view.CustomProgressDialog;

/**
 * Created by Jack on 2015/9/4.
 */
public class ActivityFragment extends Fragment implements View.OnClickListener {

    private View mView;
    private Button btnViewPagerPaging;
    private Button btnViewPagerSliding;
    private Button btnChat;
    private Button btnViewFlipper;
    private Button btnCustomPopupWindow;
    private Button btnCustomDialog;
    private Button btnCustomProgress;
    private Button btnVibration;
    private Button btnVibrationCancel;
    private Button btnVoice;
    private Button btnVoiceCancel;
    private Button btnSaveCache;
    private Button btnListFilter;
    private CustomPopupWindow popupWindow;
    private Toast mToast;
    private Vibrator vibrator;    //定义一个震动的变量
    private CustomProgressDialog progressDialog = null;

    //一些声音的常量
//    public static final int RINGER_MODE_SILENT = 0;
//    public static final int RINGER_MODE_VIBRATE = 1;
//    public static final int RINGER_MODE_NORMAL = 2;

    private AudioManager audio;     //获取系统的声音
    private MediaPlayer media;     //播放音乐
    private boolean isSoundEnabled = false;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_activity, null);

        initeView();
        return mView;
    }

    private void initeView() {
        btnViewPagerPaging = (Button) mView.findViewById(R.id.activity_btn_viewpager_paging);
        btnViewPagerSliding = (Button) mView.findViewById(R.id.activity_btn_viewpager_sliding);
        btnChat = (Button) mView.findViewById(R.id.activity_btn_chat);
        btnViewFlipper = (Button) mView.findViewById(R.id.activity_btn_viewflipper);
        btnCustomPopupWindow = (Button) mView.findViewById(R.id.activity_btn_custom_popupwindow);
        btnCustomDialog = (Button) mView.findViewById(R.id.activity_btn_custom_dialog);
        btnCustomProgress = (Button) mView.findViewById(R.id.activity_btn_custom_progress);
        btnVibration = (Button) mView.findViewById(R.id.activity_btn_vibration);
        btnVibrationCancel = (Button) mView.findViewById(R.id.activity_btn_vibration_cancel);
        btnVoice = (Button) mView.findViewById(R.id.activity_btn_voice);
        btnVoiceCancel = (Button) mView.findViewById(R.id.activity_btn_voice_cancel);
        btnSaveCache= (Button) mView.findViewById(R.id.activity_btn_save_cache);
        btnListFilter=(Button) mView.findViewById(R.id.activity_btn_list_filter);
        vibrator = (Vibrator) getActivity().getSystemService(Service.VIBRATOR_SERVICE);      //获取系统的震动
        audio = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);

        btnViewPagerPaging.setOnClickListener(this);
        btnViewPagerSliding.setOnClickListener(this);
        btnChat.setOnClickListener(this);
        btnViewFlipper.setOnClickListener(this);
        btnCustomPopupWindow.setOnClickListener(this);
        btnCustomDialog.setOnClickListener(this);
        btnCustomProgress.setOnClickListener(this);
        btnVibration.setOnClickListener(this);
        btnVibrationCancel.setOnClickListener(this);
        btnVoice.setOnClickListener(this);
        btnVoiceCancel.setOnClickListener(this);
        btnSaveCache.setOnClickListener(this);
        btnListFilter.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.activity_btn_viewpager_paging:
                startActivity(new Intent(getActivity(), ViewPagerPagingActivity.class));
                break;
            case R.id.activity_btn_viewpager_sliding:
                startActivity(new Intent(getActivity(), SlideMenuActivity.class));
                break;
            case R.id.activity_btn_viewflipper:
                startActivity(new Intent(getActivity(), ViewFlipperActivity.class));
                break;
            case R.id.activity_btn_chat:
                startActivity(new Intent(getActivity(), ChatActivity.class));
                break;
            case R.id.activity_btn_custom_dialog:
                showCustomDialog();
                break;
            case R.id.activity_btn_custom_popupwindow:
                showSelectPicPopupWindow();
                break;
            case R.id.activity_btn_custom_progress:
                if (progressDialog == null) {
                    progressDialog = new CustomProgressDialog(getActivity(), R.layout.custom_progress_dialog);
                }
                progressDialog.show();
                startActivity(new Intent(getActivity(), ProgressActivity.class));
                break;
            case R.id.activity_btn_vibration:
                vibrator.vibrate(3000);      //设置震动时间为3s
//                第一个参数为等待指定时间后开始震动，震动时间为第二个参数。后边的参数依次为等待震动和震动的时间
//                第二个参数为重复次数，-1为不重复，0为一直震动
//                vibrator.vibrate(new long[]{100,10,100,1000}, -1);
                break;
            case R.id.activity_btn_vibration_cancel:
                vibrator.cancel();         //取消震动
                break;
            case R.id.activity_btn_voice:
                media = MediaPlayer.create(getContext(), R.raw.iphone);
                media.start();
                break;
            case R.id.activity_btn_voice_cancel:
//                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);    //设置通话音量为静音
//                audio.setRingerMode(AudioManager.RINGER_MODE_SILENT);
                if (!isSoundEnabled) {
                    audio.setStreamMute(AudioManager.STREAM_MUSIC, true);        //设置媒体音量为静音
                    isSoundEnabled=true;
                }else {
                    audio.setStreamMute(AudioManager.STREAM_MUSIC, false);      //设置媒体音量不为静音
                    isSoundEnabled=false;
                }
//                audio.setRingerMode(AudioManager.STREAM_MUSIC);
//                media.pause();
                break;
            case R.id.activity_btn_save_cache:
                startActivity(new Intent(getActivity(), SaveCacheActivity.class));
                break;
            case R.id.activity_btn_list_filter:
                startActivity(new Intent(getActivity(), ListFilterActivity.class));
                break;
        }
    }

    private void showCustomDialog() {
        CustomDialog.Builder builder = new CustomDialog.Builder(getActivity());
        builder.setMessage("这是一个自定义dialog");
        builder.setTitle("提示");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("确定键");
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                showToast("取消键");
                dialog.dismiss();
            }
        });
        builder.create().show();
    }

    private void showSelectPicPopupWindow() {
        popupWindow = new CustomPopupWindow(getActivity(), itemsOnClick);
        popupWindow.showAtLocation(getActivity().findViewById(R.id.activity_main),
                Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); //设置popupwindow在activity中显示的位置
    }

    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.popup_btn_camera:
                    showToast("打开相机成功");
                    popupWindow.dismiss();
                    break;
                case R.id.popup_btn_album:
                    showToast("打开相册成功");
                    popupWindow.dismiss();
                    break;
            }
        }
    };


    public void showToast(String text) {
        if (mToast == null) {
            mToast = Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(text);
            mToast.setDuration(Toast.LENGTH_SHORT);
        }
        mToast.show();
    }
}
