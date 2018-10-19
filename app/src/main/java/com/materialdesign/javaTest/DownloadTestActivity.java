package com.materialdesign.javaTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.materialdesign.R;

/**
 * Created by Administrator on 2018/10/19
 * 邮箱 18780569202@163.com
 */
public class DownloadTestActivity extends AppCompatActivity{
    public static void start(Activity activity){
        Intent intent = new Intent(activity, DownloadTestActivity.class);
        activity.startActivity(intent);
    }
    TextView percent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        percent = findViewById(R.id.percent);
        findViewById(R.id.btn_download1).setOnClickListener(v -> {

        });
        findViewById(R.id.btn_download2).setOnClickListener(v -> {

        });


    }
}
