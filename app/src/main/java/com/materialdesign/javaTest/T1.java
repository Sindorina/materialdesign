package com.materialdesign.javaTest;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/10/9
 * 邮箱 18780569202@163.com
 */
public class T1 extends AppCompatActivity{
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        P2.setName();
        List<String> list2 = new ArrayList<>();
        List<String> list = new ArrayList<>();
        list.stream().map(String::toLowerCase).filter(list2::contains).forEach(list::add);

    }
}
