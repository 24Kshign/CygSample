package com.example.jack.activity;

import android.os.Bundle;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.example.jack.bean.Author;
import com.example.jack.bean.Book;
import com.example.jack.bean.User;

import java.util.List;

/**
 * Created by Jack on 16/6/16.
 */
public class TTTestActivity extends BaseActivity {

    private String TAG = "TTTestActivity";
    private String jsonString = "{\"book\":{\"author\":[{\"money\":500,\"name\":\"南派三叔\"},{\"money\":200,\"name\":\"孔二狗\"}],\"name\":\"网络文学\"},\"name\":\"玄玉\"}";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        User user = JSON.parseObject(jsonString, User.class);
        if (user != null) {
            Book book = user.getBook();
            List<Author> list = book.getAuthor();
            Log.d(TAG, "UserName===" + user.getName() + "\n");
            Log.d(TAG, "BookName===" + book.getName() + "\n");
            for (int i = 0; i < list.size(); i++) {
                Log.d(TAG, list.get(i).getName() + "----" + list.get(i).getMoney());
            }
        }
    }

    public void asyncHttpClient(){
        String url = "http://www.imooc.com/api/teacher?type=4&num=30";
    }
}