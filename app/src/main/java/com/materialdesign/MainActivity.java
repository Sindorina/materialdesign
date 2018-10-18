package com.materialdesign;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;

import com.materialdesign.javaTest.TestActivity10;

import java.util.ArrayList;
import java.util.List;

import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //测试分支
        setContentView(R.layout.activity_main);
        checkPermission();
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            floatButtonTest();
            getWindow().setExitTransition(new Explode().setDuration(400));
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
            TestActivity.start(MainActivity.this);
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            TestActivity10.start(this);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void floatButtonTest(){
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.container);

        PromotedActionsLibrary promotedActionsLibrary = new PromotedActionsLibrary();

        // setup library
        promotedActionsLibrary.setup(getApplicationContext(), frameLayout);

        // create onClickListener for each promoted action
        View.OnClickListener onClickListener = view -> {
            // Do something
            ArrayList<ImageButton> promotedActions = promotedActionsLibrary.promotedActions;
            for (int i= 0 ; i < promotedActions.size() ; i++){
                if (promotedActions.get(i)==view){
                    if (i==2){
                        TestActivity.start(MainActivity.this);
                    }else if (i==0){
                        TestActivity2.start(MainActivity.this);
                    }else if (i==1){
                        TimerActivity.start(MainActivity.this);
                    }
                    Toast.makeText(MainActivity.this,"点击了第" +i+
                            "个按钮",Toast.LENGTH_SHORT).show();
                }
            }
        };

        // customize promoted actions with a drawable
        promotedActionsLibrary.addItem(getResources().getDrawable(android.R.drawable.ic_menu_edit), onClickListener);
        promotedActionsLibrary.addItem(getResources().getDrawable(android.R.drawable.ic_menu_send), onClickListener);
        promotedActionsLibrary.addItem(getResources().getDrawable(android.R.drawable.ic_input_get), onClickListener);

        // create main floating button and customize it with a drawable
        promotedActionsLibrary.addMainItem(getResources().getDrawable(android.R.drawable.ic_input_add));
    }

    private void checkPermission(){
        List<PermissionItem> permissionItems = new ArrayList<>();
        permissionItems.add(new PermissionItem(Manifest.permission.CAMERA, "照相机", R.mipmap.permission_ic_memory));
        permissionItems.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "定位", R.mipmap.permission_location));


        HiPermission.create(this).permissions(permissionItems).checkMutiPermission(new PermissionCallback() {
            @Override
            public void onClose() {

            }

            @Override
            public void onFinish() {

            }

            @Override
            public void onDeny(String permission, int position) {

            }

            @Override
            public void onGuarantee(String permission, int position) {

            }
        });
    }
}
