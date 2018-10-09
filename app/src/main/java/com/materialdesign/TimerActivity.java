package com.materialdesign;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.util.Log;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2018/9/27
 * 邮箱 18780569202@163.com
 */
public class TimerActivity extends AppCompatActivity{
    public static void start(Activity activity){
        Intent intent = new Intent(activity, TimerActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
        }else {
            activity.startActivity(intent);
        }
    }
    int i = 0;
    TextView text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        text = findViewById(R.id.text);
        findViewById(R.id.start).setOnClickListener(v -> {
            Log.e("ss","开启timer-->"+(timer!=null));
            if (timer==null){
                Field field;
                try {
                    field = TimerTask.class.getDeclaredField("state");
                    field.setAccessible(true);
                    field.set(timerTask, 0);
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                timer = new Timer(true);
                timer.schedule(timerTask,0,1000);
            }
        });
        findViewById(R.id.end).setOnClickListener(v -> {
            if (timer!=null){
                timerTask.cancel();
                timer.cancel();
                timer = null;
                Log.e("ss","取消timer-->"+(timer!=null));
                i = 0;
            }
        });
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setEnterTransition(new Explode().setDuration(1000));
            getWindow().setExitTransition(new Explode().setDuration(1000));
        }
    }
    private Timer timer;
    private TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            i++;
            Log.e("SSS","测试-->"+i);
        }
    };
}
