package com.materialdesign.javaTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.materialdesign.R;

/**
 * Created by Administrator on 2018/10/18
 * 邮箱 18780569202@163.com
 */
public class TestActivity10 extends AppCompatActivity{
    public static void start(Activity activity){
        Intent intent = new Intent(activity, TestActivity10.class);
        activity.startActivity(intent);
    }
    ImageView imageView;
    String url = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1539852868289&di=df71ea98654bf053f609e0f2a71a5cb5&imgtype=0&src=http%3A%2F%2Fwww.artsbj.com%2Fuploadfile%2F2017%2F0228%2F20170228113641934.jpg";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test10);
        imageView = findViewById(R.id.imageView);
        ImageLoader.loadUrl(url).into(imageView);
    }
}
