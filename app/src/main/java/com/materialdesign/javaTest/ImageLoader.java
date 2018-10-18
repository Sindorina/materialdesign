package com.materialdesign.javaTest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Administrator on 2018/10/18
 * 邮箱 18780569202@163.com
 */
public class ImageLoader {
    private LruCache<String,Bitmap> lruCache;
    private static volatile ImageLoader loader;
    private static String url;
    private ExecutorService pool = null;
    private ImageView imageView;
    private ImageLoader() {
        long size = Runtime.getRuntime().maxMemory();
        lruCache = new LruCache<String,Bitmap>((int) (size/8)){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getByteCount();
            }
        };
    }
    private static ImageLoader getInstance(){
        if (loader == null){
            synchronized (ImageLoader.class){
                if (loader == null){
                    loader = new ImageLoader();
                }
            }
        }
        return loader;
    }
    public static ImageLoader loadUrl(String url){
        ImageLoader.url = url;
        return getInstance();
    }

    public void into(ImageView imageView){
        this.imageView = imageView;
        Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap == null){
            downloadImage(url);
        }else {
            imageView.setImageBitmap(bitmap);
            Log.e("ImageLoader","图片缓存");
        }
    }
    /**
     * 添加图片到缓存
     * @param url
     * @param bitmap
     */
    private void addBitmapToCache(String url,Bitmap bitmap){
        lruCache.put(url,bitmap);
    }

    /**
     * 从缓存中移除指定图片
     * @param url
     */
    public void removeBitmapFromCache(String url){
        lruCache.remove(url);
    }

    /**
     * 从缓存读取指定图片
     * @param url
     */
    private Bitmap getBitmapFromCache(String url){
        return lruCache.get(url);
    }
    private void downloadImage(String url){

        if (pool == null){
            synchronized (ExecutorService.class){
                if (pool == null){
                    pool = Executors.newFixedThreadPool(10);
                }
            }
        }
        pool.execute(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url1 = new URL(url);
                    connection = (HttpURLConnection) url1.openConnection();
                    connection.setConnectTimeout(10 * 1000);
                    connection.setReadTimeout(10 * 1000);
                    connection.setDoInput(true);
                    connection.setRequestMethod("GET");
                    Bitmap bitmap = BitmapFactory.decodeStream(connection.getInputStream());
                    addBitmapToCache(url,bitmap);
                    Message message = Message.obtain();
                    message.obj = bitmap;
                    message.what = 200;
                    imageHandler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.e("ImageLoader","网络错误");
                }
            }
        });
    }
    private Handler imageHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 200){
                Log.e("ImageLoader","图片来源网络");
                Bitmap bitmap = (Bitmap) msg.obj;
                imageView.setImageBitmap(bitmap);
            }
            return false;
        }
    });
}
