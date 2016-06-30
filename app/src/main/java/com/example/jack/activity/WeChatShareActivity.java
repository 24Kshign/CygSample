package com.example.jack.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.jack.ioc.ContentView;
import com.example.jack.ioc.ViewInject;
import com.example.jack.ioc.ViewInjectUtils;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXEmojiObject;
import com.tencent.mm.sdk.modelmsg.WXImageObject;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXMusicObject;
import com.tencent.mm.sdk.modelmsg.WXTextObject;
import com.tencent.mm.sdk.modelmsg.WXVideoObject;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;

/**
 * 作者：Created by JackCheng on 2015/10/5 12:18.
 * 邮箱：17764576259@163.com
 */
@ContentView(R.layout.activity_wechat_share)
public class WeChatShareActivity extends BaseActivity implements View.OnClickListener {

    @ViewInject(R.id.aws_btn_share)
    private Button btnShare;

    @ViewInject(R.id.aws_btn_send_text)
    private Button btnSendText;

    @ViewInject(R.id.aws_check_share)
    private CheckBox checkBox;

    @ViewInject(R.id.aws_btn_send_binary_pic)
    private Button btnSendBinaryPic;

    @ViewInject(R.id.aws_btn_send_local_pic)
    private Button btnSendLocalPic;

    @ViewInject(R.id.aws_btn_send_url_pic)
    private Button btnSendUrlPic;

    @ViewInject(R.id.aws_btn_audio)
    private Button btnAudio;

    @ViewInject(R.id.aws_btn_video)
    private Button btnVideo;

    @ViewInject(R.id.aws_btn_url)
    private Button btnUrl;

    @ViewInject(R.id.aws_btn_emotion)
    private Button btnEmotion;

    //应用程序的ID
    public static final String APP_ID = "wxaf566b24ef84f216";
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewInjectUtils.inject(this);
        initeListener();
    }

    private void initeListener() {
        api = WXAPIFactory.createWXAPI(this, APP_ID);
        //将APP_ID注册到微信中
        api.registerApp(APP_ID);
        btnShare.setOnClickListener(this);
        btnSendText.setOnClickListener(this);
        btnSendBinaryPic.setOnClickListener(this);
        btnSendLocalPic.setOnClickListener(this);
        btnSendUrlPic.setOnClickListener(this);
        btnAudio.setOnClickListener(this);;
        btnVideo.setOnClickListener(this);;
        btnUrl.setOnClickListener(this);;
        btnEmotion.setOnClickListener(this);;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.aws_btn_share:
                Toast.makeText(this, "启动成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.aws_btn_send_text:
                shareText();
                break;
            case R.id.aws_btn_send_binary_pic:
                shareBinaryPic();
                break;
            case R.id.aws_btn_send_local_pic:
                shareLocalPic();
                break;
            case R.id.aws_btn_send_url_pic:
                shareUrlPic();
                break;
            case R.id.aws_btn_audio:
                shareAudio();
                break;
            case R.id.aws_btn_video:
                shareVideo();
                break;
            case R.id.aws_btn_url:
                shareUrl();
                break;
            case R.id.aws_btn_emotion:
                shareEmotion();
                break;
        }
    }

    //分享一个音频
    private void shareAudio() {
        //1、创建一个WXMusicObject对象，用来指定音频url
        WXMusicObject musicObject=new WXMusicObject();
        musicObject.musicUrl="http://y.qq.com/#type=song&mid=002A24VV0baRDP&play=1";
        //2、创建WXMediaMessage对象
        WXMediaMessage mediaMessage=new WXMediaMessage();
        mediaMessage.mediaObject=musicObject;
        mediaMessage.title="南山南";   //设置歌曲的名字
        mediaMessage.description="演唱：张磊";  //设置歌曲信息
        //3、设置缩略图,选择的图片不能太大，否则会出现错误
        Bitmap thumb=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        mediaMessage.thumbData=bmpToByteArray(thumb,true);
        //4、创建SendMessageToWX.Req
        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=buildTransaction("music");
        req.message=mediaMessage;
        //设置是否发送到朋友圈
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4、发送给微信客户端
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    //分享一个视频
    private void shareVideo() {
        //1、创建一个WXVideoObject对象，用于指定视频url
        WXVideoObject videoObject=new WXVideoObject();
        videoObject.videoUrl="http://www.iqiyi.com/v_19rri065p4.html#vfrm=2-3-0-1";
        //2、创建WXMediaMessage对象
        WXMediaMessage mediaMessage=new WXMediaMessage();
        mediaMessage.mediaObject=videoObject;
        mediaMessage.title="小苹果";   //设置歌曲的名字
        mediaMessage.description="筷子兄弟的新作";  //设置歌曲信息
        //3、设置缩略图，选择的图片不能太大，否则会出现错误
        Bitmap thumb=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        mediaMessage.thumbData=bmpToByteArray(thumb,true);
        //4、创建SendMessageToWX.Req
        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=buildTransaction("video");
        req.message=mediaMessage;
        //设置是否发送到朋友圈
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4、发送给微信客户端
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    //分享一个URL
    private void shareUrl() {
        //1、创建一个WXWebpageObject，用于封装要发送的Url
        WXWebpageObject wxWebpageObject=new WXWebpageObject();
        wxWebpageObject.webpageUrl="http://www.loldytt.com/";
        //2、创建一个WXMediaMessage对像
        WXMediaMessage mediaMessage=new WXMediaMessage(wxWebpageObject);
        mediaMessage.title="全集网";
        mediaMessage.description="LOL电影天堂，最新电影下载";
        //3、设置缩略图，选择的图片不能太大，否则会出现错误
        Bitmap thumb=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        mediaMessage.thumbData=bmpToByteArray(thumb,true);
        //4、创建SendMessageToWX.Req
        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=buildTransaction("webpage");
        req.message=mediaMessage;
        //设置是否发送到朋友圈
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4、发送给微信客户端
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    //分享表情
    private void shareEmotion() {
        //1、创建一个WXEmojiObject对象，用于封装表情文件的路径
        String EMOJI_FILE_PATH="/sdcard/emotion.gif";
        WXEmojiObject emojiObject=new WXEmojiObject();
        emojiObject.emojiPath=EMOJI_FILE_PATH;
        //2、创建WXMediaMessage对象，并包装WXImageObject对象
        WXMediaMessage mediaMessage=new WXMediaMessage(emojiObject);
        mediaMessage.title="表情标题";
        mediaMessage.description="表情描述";
        //3、设置缩略图
        Bitmap thumb=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        mediaMessage.thumbData=bmpToByteArray(thumb,true);
        //4、创建SendMessageToWX.Req
        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=buildTransaction("emotion");
        req.message=mediaMessage;
        //设置是否发送到朋友圈
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4、发送给微信客户端
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    //将bitmap转换成byte格式的数组
    private byte[] bmpToByteArray(final Bitmap bitmap, final boolean isNeedRecyle) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();   //用于输出的输出流
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);    //把压缩的结果放在outputStream里面
        if (isNeedRecyle) {
            bitmap.recycle();
        }
        byte[] result = outputStream.toByteArray();
        try {
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //向微信或朋友圈分享二进制图片
    private void shareBinaryPic() {
        //1、获取二进制图像的Bitmap对象
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.share_pic);
        //2、创建一个WXImageObject对象，并包装bitmap
        WXImageObject imageObject = new WXImageObject(bitmap);
        //3、创建WXMediaMessage对象，并包装WXImageObject对象
        WXMediaMessage mediaMessage = new WXMediaMessage();
        mediaMessage.mediaObject = imageObject;
        //4、由于可能分享到微信的图片较大，所以要对其进行压缩处理
        Bitmap thumBap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
        //5、释放图片占用的资源
        bitmap.recycle();
        mediaMessage.thumbData = bmpToByteArray(thumBap, true);   //设置缩略图
        //6、创建SendMessageTo.Req对象，用于发送
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");     //创建一个发送图像的标识
        req.message = mediaMessage;
        //设置是否发送到朋友圈
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4、发送给微信客户端
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }


    //向微信或朋友圈分享本地图片
    private void shareLocalPic() {
        //1、判断图像文件是否存在
        String path = "/sdcard/jack.jpg";
        File file = new File(path);
        if (!file.exists()) {
            Toast.makeText(WeChatShareActivity.this, "/sdcard/jack.jpg文件不存在",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //2、创建一个WXImageObject对象，并设置path
        WXImageObject imageObject = new WXImageObject();
        imageObject.setImagePath(path);      //设置图像文件的路径
        //3、创建WXMediaMessage对象，并包装WXImageObject对象
        WXMediaMessage mediaMessage = new WXMediaMessage();
        mediaMessage.mediaObject = imageObject;
        //4、由于可能分享到微信的图片较大，所以要对其进行压缩处理
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Bitmap thumBap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
        //5、释放图片占用的资源
        bitmap.recycle();
        mediaMessage.thumbData = bmpToByteArray(thumBap, true);   //设置缩略图
        //6、创建SendMessageTo.Req对象，用于发送
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");     //创建一个发送图像的标识
        req.message = mediaMessage;
        //设置是否发送到朋友圈
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4、发送给微信客户端
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    //向微信或朋友圈分享url图片
    private void shareUrlPic() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1、创建一个WXImageObject对象，并设置url
                    String url = "http://pic19.nipic.com/20120308/7491614_141057681000_2.png";
                    WXImageObject imageObject = new WXImageObject();
                    imageObject.imageUrl = url;     //设置图像的url
                    //2、创建WXMediaMessage对象，并包装WXImageObject对象
                    WXMediaMessage mediaMessage = new WXMediaMessage();
                    mediaMessage.mediaObject = imageObject;
                    //3、由于可能分享到微信的图片较大，所以要对其进行压缩处理
                    Bitmap bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
                    Bitmap thumBap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
                    //4、释放图片占用的资源
                    bitmap.recycle();
                    mediaMessage.thumbData = bmpToByteArray(thumBap, true);   //设置缩略图
                    //5、创建SendMessageTo.Req对象，用于发送
                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = buildTransaction("img");     //创建一个发送图像的标识
                    req.message = mediaMessage;
                    //设置是否发送到朋友圈
                    if (checkBox.isChecked()) {
                        req.scene = SendMessageToWX.Req.WXSceneTimeline;
                    } else {
                        req.scene = SendMessageToWX.Req.WXSceneSession;
                    }
                    //6、发送给微信客户端
                    api.sendReq(req);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        finish();
    }

    //为请求生成一个唯一的标识
    private String buildTransaction(final String type) {
        if (type == null) {
            return String.valueOf(System.currentTimeMillis());
        }
        return type + System.currentTimeMillis();
    }

    //向微信或朋友圈分享文本
    private void shareText() {
        //创建EditText文本，用于分享
        final EditText editor = new EditText(this);
        editor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        editor.setText("默认的分享文本");
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("共享文本");
        //将EditText控件与对话框绑定
        builder.setView(editor);
        builder.setMessage("请输入要分享的文本");
        builder.setNegativeButton("分享", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = editor.getText().toString();
                if (text == null || text.length() == 0) {
                    return;
                }

                //1、初始化一个用于封装待分享文本的WXTextObject对象
                WXTextObject object = new WXTextObject();
                object.text = text;
                //2、创建一个WXMediaMessage对象，用来向Android客户端发送数据
                WXMediaMessage message = new WXMediaMessage();
                message.mediaObject = object;
                message.description = text;
                //3、准备和客户端交互，创建一个用于请求微信客户端的SendMessageToWx对象
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.message = message;
                //设置请求的唯一标识
                req.transaction = buildTransaction("text");
                //表示发送给朋友还是朋友圈
                if (checkBox.isChecked()) {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
                } else {
                    req.scene = SendMessageToWX.Req.WXSceneSession;
                }
                //4、发送给微信客户端
                Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("取消", null);
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
