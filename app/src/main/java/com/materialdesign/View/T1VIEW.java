package com.materialdesign.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.View;

/**
 * Created by Administrator on 2018/10/9
 * 邮箱 18780569202@163.com
 */
public class T1VIEW extends View{
    private int mViewWidth;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private Matrix mGradientMatrix;
    private int mTranslate;
    public T1VIEW(Context context) {
        super(context);
        mPaint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth == 0){
            mViewWidth = getMeasuredWidth();
            if(mViewWidth>0){
                // 注意这个地方不要写成new Paint()
                //参数一为渐变起初点坐标x位置，参数二为y轴位置，参数三和四分辨对应渐变终点，
                //参数五是参与渐变效果的颜色集合
                //参数六是定义每个颜色处于的渐变相对位置，这个参数可以为null，如果为null表示所有的颜色按顺序均匀的分布
                //最后参数为平铺方式
                mLinearGradient = new LinearGradient(
                        0,0,mViewWidth,0,new int[]{
                        Color.BLUE,0xffffffff,Color.BLUE
                },null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
                mGradientMatrix = new Matrix();
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(mGradientMatrix!=null){
            mTranslate += mViewWidth/10;
            if(mTranslate > 2 * mViewWidth){
                mTranslate = -mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate,0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(50);
        }
    }
}
