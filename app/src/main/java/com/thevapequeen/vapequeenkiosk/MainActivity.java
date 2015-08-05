package com.thevapequeen.vapequeenkiosk;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.thevapequeen.vapequeenkiosk.fragments.ItemListFragment;
import com.thevapequeen.vapequeenkiosk.fragments.NavigationDrawerFragment;
import com.thevapequeen.vapequeenkiosk.fragments.TopperFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by James Campbell for exclusive use by The Vape Queen. All rights reserved.
 */
public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        ItemListFragment.OnFragmentInteractionListener, TopperFragment.OnFragmentInteractionListener{

    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));
    public static NavigationDrawerFragment mNavigationDrawerFragment;
    public static ItemListFragment mItemListFragment;
    public static TopperFragment mTopperFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupKioskState();
        setContentView(R.layout.activity_main);

        mTopperFragment = (TopperFragment)getSupportFragmentManager().findFragmentById(R.id.fragment_topper);

        mItemListFragment = (ItemListFragment)getSupportFragmentManager().findFragmentById(R.id.listViewFragmentList);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,(DrawerLayout) findViewById(R.id.drawer_layout));

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


    @Override
    public void onFragmentTopperInteraction(String textTopper){

    }

    @Override
    public void onFragmentItemListInteraction(String string){

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
