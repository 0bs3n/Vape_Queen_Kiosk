package com.thevapequeen.vapequeenkiosk;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;

import com.thevapequeen.vapequeenkiosk.artesianblends.ArtesianBlend;
import com.thevapequeen.vapequeenkiosk.fragments.NavigationDrawerFragment;
import com.thevapequeen.vapequeenkiosk.fragments.TopperFragment;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by James Campbell for exclusive use by The Vape Queen. All rights reserved.
 */
public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks,
         TopperFragment.OnFragmentInteractionListener {

    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));
    public static NavigationDrawerFragment mNavigationDrawerFragment;
    public static TopperFragment mTopperFragment;

    private ArrayList<ArtesianBlend> artesianBlendArrayList = new ArrayList<>();
    private ArrayList<PremiumJuice> premiumJuiceArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupKioskState();
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

        mTopperFragment = (TopperFragment)getSupportFragmentManager().findFragmentById(R.id.textViewMain);

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
    public void onNavigationDrawerItemSelected(String juiceType, String juiceBrand) {
        TopperFragment.changeText(juiceBrand);
        if(juiceType.equals("Artesian")){
            Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/Download/logo.png");
            TopperFragment.setImageViewTopper(bitmap);
            //Pass to List Fragment
        }
        else if(juiceType.equals("Premium")){
            Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/Download/" + juiceBrand.toLowerCase().replaceAll(" ","") + ".bmp");
            TopperFragment.setImageViewTopper(bitmap);
            //Pass to List Fragment
        }
        else{
            //Nothing To See Here. Move Along.
        }
    }

    @Override
    public void onFragmentTopperInteraction(String textTopper){

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

    public void setupArtesianBrandList(String brand){
        artesianBlendArrayList.clear();
        String csvFile = "/sdcard/Download/artesian_juices.csv";
        BufferedReader br = null;
        String line = new String();
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] _artesianblend = line.split(cvsSplitBy);
                if(_artesianblend[5].equals(brand)){
                    artesianBlendArrayList.add(new ArtesianBlend(_artesianblend[0], _artesianblend[1], _artesianblend[2], _artesianblend[3], _artesianblend[4], _artesianblend[5]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void setupPremiumBrandList(String brand){
        premiumJuiceArrayList.clear();
        String csvFile = "/sdcard/Download/premium_juices.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] _premiumjuice = line.split(cvsSplitBy);
                if(_premiumjuice[5].equals(brand)){
                    premiumJuiceArrayList.add(new PremiumJuice(_premiumjuice[0],_premiumjuice[1],_premiumjuice[2],_premiumjuice[3],_premiumjuice[4],_premiumjuice[5]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
