package com.materialdesign;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;

/**
 * Created by Administrator on 2018/9/30
 * 邮箱 18780569202@163.com
 */
public class FlashLightActivity extends AppCompatActivity{
    public static void start(Activity activity){
        Intent intent = new Intent(activity, FlashLightActivity.class);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            activity.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(activity).toBundle());
        }else {
            activity.startActivity(intent);
        }
    }
    private boolean open = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_light);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setEnterTransition(new Explode().setDuration(1000));
            getWindow().setExitTransition(new Explode().setDuration(1000));
        }
        findViewById(R.id.open_light).setOnClickListener(v -> {
            if (!open){
                changeFlashLight(true);
            }
        });
        findViewById(R.id.close_light).setOnClickListener(v -> {
            if (open){
                changeFlashLight(false);
            }
        });
    }

    public void changeFlashLight(boolean openOrClose) {
        //判断API是否大于24（安卓7.0系统对应的API）
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            try {
                //获取CameraManager
                CameraManager mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
                //获取当前手机所有摄像头设备ID
                String[] ids = mCameraManager.getCameraIdList();
                for (String id : ids) {
                    CameraCharacteristics c = mCameraManager.getCameraCharacteristics(id);
                    //查询该摄像头组件是否包含闪光灯
                    Boolean flashAvailable = c.get(CameraCharacteristics.FLASH_INFO_AVAILABLE);
                    Integer lensFacing = c.get(CameraCharacteristics.LENS_FACING);
                    if (flashAvailable != null && flashAvailable
                            && lensFacing != null && lensFacing == CameraCharacteristics.LENS_FACING_BACK) {
                        //打开或关闭手电筒
                        mCameraManager.setTorchMode(id, openOrClose);
                    }
                }
            } catch (CameraAccessException e) {
                e.printStackTrace();
            }
        }else {
            Camera camera = Camera.open();
            if(openOrClose){
                Camera.Parameters mParameters = camera.getParameters();
                mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(mParameters);
            } else {
                Camera.Parameters mParameters = camera.getParameters();
                mParameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(mParameters);
            }
            camera.release();
        }
        open = openOrClose;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
