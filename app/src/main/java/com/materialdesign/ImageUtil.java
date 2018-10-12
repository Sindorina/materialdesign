package com.materialdesign;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2018/10/9
 * 邮箱 18780569202@163.com
 */
public class ImageUtil {
    /**
     * 质量压缩
     *
     * 超过200k就压缩
     * @param bitmap
     * @return
     */
    public static Bitmap compressImage(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        int options = 100;
        while (byteArrayOutputStream.toByteArray().length > 1024*200){
            byteArrayOutputStream.reset();
            options -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG,options,byteArrayOutputStream);
        }
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        return BitmapFactory.decodeStream(byteArrayInputStream);
    }

    /**
     * 大小压缩
     * 按720x1080压缩
     * @param path
     * @return
     */
    public static Bitmap compressImage(String path){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path,options);
        options.inJustDecodeBounds = false;
        int oldWidth = options.outWidth;
        int oldHeight = options.outHeight;
        int width = 720;
        int height = 1080;
        int be = 1;//缩放因子
        if (oldHeight > oldWidth && oldHeight > height){
            be = oldHeight/height;
        }else if (oldWidth > oldHeight && oldWidth > width){
            be = oldWidth/width;
        }
        if (be<=0){
            be = 1;
        }
        options.inSampleSize = be;
        //options.inPreferredConfig = Bitmap.Config.RGB_565;
        return BitmapFactory.decodeFile(path,options);
    }

    /**
     * 把图片转为base64字符串
     * @param path
     * @return
     */
    public static String createBase64Image(String path){
        //先压缩
        Bitmap bitmap = compressImage(BitmapFactory.decodeFile(path));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);
        return Base64.encodeToString(byteArrayOutputStream.toByteArray(), Base64.DEFAULT);
    }

    /**
     * 把base64转为图片
     * @param string
     * @return
     */
    public static Bitmap stringToBitmap(String string) {
        Bitmap bitmap = null;
        try {
            byte[] bitmapArray = Base64.decode(string.split(",")[1], Base64.DEFAULT);
            bitmap = BitmapFactory.decodeByteArray(bitmapArray, 0, bitmapArray.length);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }

}
