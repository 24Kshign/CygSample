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
 * ���ߣ�Created by JackCheng on 2015/10/5 12:18.
 * ���䣺17764576259@163.com
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

    //Ӧ�ó����ID
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
        //��APP_IDע�ᵽ΢����
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
                Toast.makeText(this, "�����ɹ�", Toast.LENGTH_SHORT).show();
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

    //����һ����Ƶ
    private void shareAudio() {
        //1������һ��WXMusicObject��������ָ����Ƶurl
        WXMusicObject musicObject=new WXMusicObject();
        musicObject.musicUrl="http://y.qq.com/#type=song&mid=002A24VV0baRDP&play=1";
        //2������WXMediaMessage����
        WXMediaMessage mediaMessage=new WXMediaMessage();
        mediaMessage.mediaObject=musicObject;
        mediaMessage.title="��ɽ��";   //���ø���������
        mediaMessage.description="�ݳ�������";  //���ø�����Ϣ
        //3����������ͼ,ѡ���ͼƬ����̫�󣬷������ִ���
        Bitmap thumb=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        mediaMessage.thumbData=bmpToByteArray(thumb,true);
        //4������SendMessageToWX.Req
        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=buildTransaction("music");
        req.message=mediaMessage;
        //�����Ƿ��͵�����Ȧ
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4�����͸�΢�ſͻ���
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    //����һ����Ƶ
    private void shareVideo() {
        //1������һ��WXVideoObject��������ָ����Ƶurl
        WXVideoObject videoObject=new WXVideoObject();
        videoObject.videoUrl="http://www.iqiyi.com/v_19rri065p4.html#vfrm=2-3-0-1";
        //2������WXMediaMessage����
        WXMediaMessage mediaMessage=new WXMediaMessage();
        mediaMessage.mediaObject=videoObject;
        mediaMessage.title="Сƻ��";   //���ø���������
        mediaMessage.description="�����ֵܵ�����";  //���ø�����Ϣ
        //3����������ͼ��ѡ���ͼƬ����̫�󣬷������ִ���
        Bitmap thumb=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        mediaMessage.thumbData=bmpToByteArray(thumb,true);
        //4������SendMessageToWX.Req
        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=buildTransaction("video");
        req.message=mediaMessage;
        //�����Ƿ��͵�����Ȧ
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4�����͸�΢�ſͻ���
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    //����һ��URL
    private void shareUrl() {
        //1������һ��WXWebpageObject�����ڷ�װҪ���͵�Url
        WXWebpageObject wxWebpageObject=new WXWebpageObject();
        wxWebpageObject.webpageUrl="http://www.loldytt.com/";
        //2������һ��WXMediaMessage����
        WXMediaMessage mediaMessage=new WXMediaMessage(wxWebpageObject);
        mediaMessage.title="ȫ����";
        mediaMessage.description="LOL��Ӱ���ã����µ�Ӱ����";
        //3����������ͼ��ѡ���ͼƬ����̫�󣬷������ִ���
        Bitmap thumb=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        mediaMessage.thumbData=bmpToByteArray(thumb,true);
        //4������SendMessageToWX.Req
        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=buildTransaction("webpage");
        req.message=mediaMessage;
        //�����Ƿ��͵�����Ȧ
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4�����͸�΢�ſͻ���
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    //�������
    private void shareEmotion() {
        //1������һ��WXEmojiObject�������ڷ�װ�����ļ���·��
        String EMOJI_FILE_PATH="/sdcard/emotion.gif";
        WXEmojiObject emojiObject=new WXEmojiObject();
        emojiObject.emojiPath=EMOJI_FILE_PATH;
        //2������WXMediaMessage���󣬲���װWXImageObject����
        WXMediaMessage mediaMessage=new WXMediaMessage(emojiObject);
        mediaMessage.title="�������";
        mediaMessage.description="��������";
        //3����������ͼ
        Bitmap thumb=BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher);
        mediaMessage.thumbData=bmpToByteArray(thumb,true);
        //4������SendMessageToWX.Req
        SendMessageToWX.Req req=new SendMessageToWX.Req();
        req.transaction=buildTransaction("emotion");
        req.message=mediaMessage;
        //�����Ƿ��͵�����Ȧ
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4�����͸�΢�ſͻ���
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    //��bitmapת����byte��ʽ������
    private byte[] bmpToByteArray(final Bitmap bitmap, final boolean isNeedRecyle) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();   //��������������
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);    //��ѹ���Ľ������outputStream����
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

    //��΢�Ż�����Ȧ���������ͼƬ
    private void shareBinaryPic() {
        //1����ȡ������ͼ���Bitmap����
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.share_pic);
        //2������һ��WXImageObject���󣬲���װbitmap
        WXImageObject imageObject = new WXImageObject(bitmap);
        //3������WXMediaMessage���󣬲���װWXImageObject����
        WXMediaMessage mediaMessage = new WXMediaMessage();
        mediaMessage.mediaObject = imageObject;
        //4�����ڿ��ܷ���΢�ŵ�ͼƬ�ϴ�����Ҫ�������ѹ������
        Bitmap thumBap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
        //5���ͷ�ͼƬռ�õ���Դ
        bitmap.recycle();
        mediaMessage.thumbData = bmpToByteArray(thumBap, true);   //��������ͼ
        //6������SendMessageTo.Req�������ڷ���
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");     //����һ������ͼ��ı�ʶ
        req.message = mediaMessage;
        //�����Ƿ��͵�����Ȧ
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4�����͸�΢�ſͻ���
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }


    //��΢�Ż�����Ȧ������ͼƬ
    private void shareLocalPic() {
        //1���ж�ͼ���ļ��Ƿ����
        String path = "/sdcard/jack.jpg";
        File file = new File(path);
        if (!file.exists()) {
            Toast.makeText(WeChatShareActivity.this, "/sdcard/jack.jpg�ļ�������",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        //2������һ��WXImageObject���󣬲�����path
        WXImageObject imageObject = new WXImageObject();
        imageObject.setImagePath(path);      //����ͼ���ļ���·��
        //3������WXMediaMessage���󣬲���װWXImageObject����
        WXMediaMessage mediaMessage = new WXMediaMessage();
        mediaMessage.mediaObject = imageObject;
        //4�����ڿ��ܷ���΢�ŵ�ͼƬ�ϴ�����Ҫ�������ѹ������
        Bitmap bitmap = BitmapFactory.decodeFile(path);
        Bitmap thumBap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
        //5���ͷ�ͼƬռ�õ���Դ
        bitmap.recycle();
        mediaMessage.thumbData = bmpToByteArray(thumBap, true);   //��������ͼ
        //6������SendMessageTo.Req�������ڷ���
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("img");     //����һ������ͼ��ı�ʶ
        req.message = mediaMessage;
        //�����Ƿ��͵�����Ȧ
        if (checkBox.isChecked()) {
            req.scene = SendMessageToWX.Req.WXSceneTimeline;
        } else {
            req.scene = SendMessageToWX.Req.WXSceneSession;
        }
        //4�����͸�΢�ſͻ���
        Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                Toast.LENGTH_SHORT).show();
        finish();
    }

    //��΢�Ż�����Ȧ����urlͼƬ
    private void shareUrlPic() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //1������һ��WXImageObject���󣬲�����url
                    String url = "http://pic19.nipic.com/20120308/7491614_141057681000_2.png";
                    WXImageObject imageObject = new WXImageObject();
                    imageObject.imageUrl = url;     //����ͼ���url
                    //2������WXMediaMessage���󣬲���װWXImageObject����
                    WXMediaMessage mediaMessage = new WXMediaMessage();
                    mediaMessage.mediaObject = imageObject;
                    //3�����ڿ��ܷ���΢�ŵ�ͼƬ�ϴ�����Ҫ�������ѹ������
                    Bitmap bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
                    Bitmap thumBap = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
                    //4���ͷ�ͼƬռ�õ���Դ
                    bitmap.recycle();
                    mediaMessage.thumbData = bmpToByteArray(thumBap, true);   //��������ͼ
                    //5������SendMessageTo.Req�������ڷ���
                    SendMessageToWX.Req req = new SendMessageToWX.Req();
                    req.transaction = buildTransaction("img");     //����һ������ͼ��ı�ʶ
                    req.message = mediaMessage;
                    //�����Ƿ��͵�����Ȧ
                    if (checkBox.isChecked()) {
                        req.scene = SendMessageToWX.Req.WXSceneTimeline;
                    } else {
                        req.scene = SendMessageToWX.Req.WXSceneSession;
                    }
                    //6�����͸�΢�ſͻ���
                    api.sendReq(req);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
        finish();
    }

    //Ϊ��������һ��Ψһ�ı�ʶ
    private String buildTransaction(final String type) {
        if (type == null) {
            return String.valueOf(System.currentTimeMillis());
        }
        return type + System.currentTimeMillis();
    }

    //��΢�Ż�����Ȧ�����ı�
    private void shareText() {
        //����EditText�ı������ڷ���
        final EditText editor = new EditText(this);
        editor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        editor.setText("Ĭ�ϵķ����ı�");
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setTitle("�����ı�");
        //��EditText�ؼ���Ի����
        builder.setView(editor);
        builder.setMessage("������Ҫ������ı�");
        builder.setNegativeButton("����", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = editor.getText().toString();
                if (text == null || text.length() == 0) {
                    return;
                }

                //1����ʼ��һ�����ڷ�װ�������ı���WXTextObject����
                WXTextObject object = new WXTextObject();
                object.text = text;
                //2������һ��WXMediaMessage����������Android�ͻ��˷�������
                WXMediaMessage message = new WXMediaMessage();
                message.mediaObject = object;
                message.description = text;
                //3��׼���Ϳͻ��˽���������һ����������΢�ſͻ��˵�SendMessageToWx����
                SendMessageToWX.Req req = new SendMessageToWX.Req();
                req.message = message;
                //���������Ψһ��ʶ
                req.transaction = buildTransaction("text");
                //��ʾ���͸����ѻ�������Ȧ
                if (checkBox.isChecked()) {
                    req.scene = SendMessageToWX.Req.WXSceneTimeline;
                } else {
                    req.scene = SendMessageToWX.Req.WXSceneSession;
                }
                //4�����͸�΢�ſͻ���
                Toast.makeText(WeChatShareActivity.this, String.valueOf(api.sendReq(req)),
                        Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton("ȡ��", null);
        final AlertDialog alert = builder.create();
        alert.show();
    }
}
