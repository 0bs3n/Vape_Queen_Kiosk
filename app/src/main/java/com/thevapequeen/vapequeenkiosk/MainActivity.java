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
    public void onNavigationDrawerItemSelected(String juiceType, String juiceBrand) {
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

//    public void setupArtesianBrandList(String brand){
//        //_artesianBlendList.clear();
//        String csvFile = "/sdcard/Download/artesian_juices.csv";
//        BufferedReader br = null;
//        String line = new String();
//        String cvsSplitBy = ",";
//        try {
//            br = new BufferedReader(new FileReader(csvFile));
//            while ((line = br.readLine()) != null) {
//                String[] _artesianblend = line.split(cvsSplitBy);
//                if(_artesianblend[5].equals(brand)){
//                    //_artesianBlendList.add(new ArtesianBlend(_artesianblend[0], _artesianblend[1], _artesianblend[2], _artesianblend[3], _artesianblend[4], _artesianblend[5]));
//                }
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void setupPremiumBrandList(String brand){
//        //premiumJuiceList.clear();
//        String csvFile = "/sdcard/Download/premium_juices.csv";
//        BufferedReader br = null;
//        String line = "";
//        String cvsSplitBy = ",";
//        try {
//            br = new BufferedReader(new FileReader(csvFile));
//            while ((line = br.readLine()) != null) {
//                String[] _premiumjuice = line.split(cvsSplitBy);
//                if(_premiumjuice[5].equals(brand)){
//                    //premiumJuiceList.add(new PremiumJuice(_premiumjuice[0],_premiumjuice[1],_premiumjuice[2],_premiumjuice[3],_premiumjuice[4],_premiumjuice[5]));
//                }
//            }
////            //Todo:Pass Extras
////            Intent intent = new Intent(_mContext, PremiumActivity.class);
////            _mContext.startActivity(intent);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            if (br != null) {
//                try {
//                    br.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }


}
