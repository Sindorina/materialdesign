package com.materialdesign;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.Bundle;
        import android.support.annotation.Nullable;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.LinearLayout;

        import com.materialdesign.View.T1VIEW;

/**
 * Created by Administrator on 2018/10/9
 * 邮箱 18780569202@163.com
 */
public class MyViewTestActivity extends AppCompatActivity{
    public static void start(Activity activity){
        Intent intent = new Intent(activity,MyViewTestActivity.class);
        activity.startActivity(intent);
    }
    private LinearLayout root;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_view_test);
        root = findViewById(R.id.root);
        root.addView(new T1VIEW(this));
    }
}
