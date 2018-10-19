package com.materialdesign.javaTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.materialdesign.R;

import cn.qqtheme.framework.picker.DatePicker;

/**
 * Created by Administrator on 2018/10/18
 * 邮箱 18780569202@163.com
 */
public class TestActivity11 extends AppCompatActivity{
    public static void start(Activity activity){
        Intent intent = new Intent(activity, TestActivity11.class);
        activity.startActivity(intent);
    }
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test11);
        findViewById(R.id.btn1).setOnClickListener(v -> openBottomDialog());
        textView = findViewById(R.id.textView);
    }
    public void openBottomDialog(){

        DatePicker picker = new DatePicker(this,0);
        picker.setRangeStart(2000,1,1);
        picker.setRangeEnd(2020,12,31);
        picker.setOnDatePickListener((DatePicker.OnYearMonthDayPickListener) (s, s1, s2) -> {
            textView.setText(s+"年"+s1+"月"+s2+"日");
        });
        picker.show();
    }
}
