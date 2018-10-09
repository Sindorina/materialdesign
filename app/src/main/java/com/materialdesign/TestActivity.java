package com.materialdesign;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.TextView;


/**
 * Created by Administrator on 2018/9/7
 * 邮箱 18780569202@163.com
 */
public class TestActivity extends AppCompatActivity {
    public static void start(Activity activity){
        Intent intent = new Intent(activity, TestActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
        }else {
            activity.startActivity(intent);
        }
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setEnterTransition(new Explode().setDuration(1000));
            getWindow().setExitTransition(new Explode().setDuration(1000));
            findViewById(R.id.btn1).setOnClickListener(v -> openBottomSheet());
        }

    }


    private void openBottomSheet(){
        BottomSheetDialog dialog = new BottomSheetDialog(this);
        View view = View.inflate(this,R.layout.bottom_sheet,null);
        TextView turn_page1 = view.findViewById(R.id.turn_page1);
        TextView tv2 = view.findViewById(R.id.tv2);
        turn_page1.setOnClickListener(v -> {
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                TestActivity1.start(TestActivity.this);
                dialog.dismiss();
            }
        });
        tv2.setOnClickListener(v->{
            if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                FlashLightActivity.start(TestActivity.this);
            }
        });
        view.findViewById(R.id.tv3).setOnClickListener(v->
                MyViewTestActivity.start(TestActivity.this)
        );
        dialog.setContentView(view);
        dialog.setCancelable(true);
        dialog.show();
    }

}
