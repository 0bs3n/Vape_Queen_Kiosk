package com.thevapequeen.vapequeenkiosk;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.navigation.NavigationDrawerFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * Created by James Campbell for exclusive use by The Vape Queen. All rights reserved.
 */
public class MainActivity extends FragmentActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));
    private NavigationDrawerFragment mNavigationDrawerFragment;

    public static ImageView imageView;
    public static TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupKioskState();
        setContentView(R.layout.activity_main);

        imageView = (ImageView)findViewById(R.id.imageViewMain);
        textView = (TextView)findViewById(R.id.textViewMain);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
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




}
