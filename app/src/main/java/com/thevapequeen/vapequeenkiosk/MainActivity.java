package com.thevapequeen.vapequeenkiosk;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.housejuices.ArtesianBlendAdapter;
import com.thevapequeen.vapequeenkiosk.navigation.NavigationDrawerFragment;
import com.thevapequeen.vapequeenkiosk.navigation.OnArtesianNavItemClickListener;
import com.thevapequeen.vapequeenkiosk.navigation.OnPremiumNavItemClickListener;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuiceAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by James Campbell for exclusive use by The Vape Queen. All rights reserved.
 */
public class MainActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));
    private NavigationDrawerFragment mNavigationDrawerFragment;

    public static ImageView imageView;
    public static TextView textView;
    public static ListView ArtesianListView;
    public static ListView PremiumListView;
    public static Bitmap mBitmap;
    public static ArtesianBlendAdapter artesianadapter;
    public static PremiumJuiceAdapter premiumadapter;
    public static Activity mcontext;

    Timer timer;
    TimerTask timerTask;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupKioskState();
        setContentView(R.layout.activity_main);
        mcontext = this;
        imageView = (ImageView)findViewById(R.id.imageViewMain);
        mBitmap = BitmapFactory.decodeFile("/sdcard/Download/logo.png");
        imageView.setImageBitmap(mBitmap);
        startTimer();

        textView = (TextView)findViewById(R.id.textViewMain);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        ArtesianListView = (ListView)findViewById(R.id.listViewMainArtesian);
        artesianadapter = new ArtesianBlendAdapter(MainActivity.this,OnArtesianNavItemClickListener._artesianBlendList);
        ArtesianListView.setAdapter(artesianadapter);

        PremiumListView = (ListView)findViewById(R.id.listViewMainPremium);
        premiumadapter = new PremiumJuiceAdapter(MainActivity.this, OnPremiumNavItemClickListener.premiumJuiceList);
        PremiumListView.setAdapter(premiumadapter);

    }

    @Override
    public void onBackPressed() {
        // nothing to do here
        // … kiosk mode app

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus) {
            // Close every kind of system dialog
            Intent closeDialog = new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(closeDialog);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {

        if (blockedKeys.contains(event.getKeyCode())) {
            return true;
        } else {
            return super.dispatchKeyEvent(event);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //onResume we start our timer so it can start when the app comes from the background
        startTimer();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
    //This callback to Nav must remain empty for custom layout

    }

    private void setupKioskState() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        Settings.System.putInt(getContentResolver(),
                Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
        Settings.System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, 255);
    }

    private void animationImageFadeOut(Bitmap bitmap){
        Animation animationFadeOut = AnimationUtils.loadAnimation(this, R.anim.fadeout);
        imageView.startAnimation(animationFadeOut);
    }

    public void startTimer() {
        //set a new Timer
        timer = new Timer();

        //initialize the TimerTask's job
        initializeTimerTask();

        //schedule the timer, after the first 5000ms the TimerTask will run every 10000ms
        timer.schedule(timerTask, 5000, 5500); //
    }

    private void initializeTimerTask() {

        timerTask = new TimerTask() {
            public void run() {

                //use a handler to run a toast that shows the current timestamp
                handler.post(new Runnable() {
                    public void run() {
                        animationImageFadeOut(mBitmap);
                    }
                });
            }
        };

    }

}
