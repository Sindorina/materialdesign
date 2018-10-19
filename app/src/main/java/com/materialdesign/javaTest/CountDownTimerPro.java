package com.materialdesign.javaTest;

import android.os.CountDownTimer;

/**
 * Created by Administrator on 2018/10/18
 * 邮箱 18780569202@163.com
 */
public class CountDownTimerPro {
    public static void countDown(long target){
        CountDownTimer countDownTimer = new CountDownTimer(target,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        };
    }
}
