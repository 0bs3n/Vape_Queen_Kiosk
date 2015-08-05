package com.thevapequeen.vapequeenkiosk.artesianblends;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.MainActivity;
import com.thevapequeen.vapequeenkiosk.R;
import com.thevapequeen.vapequeenkiosk.fragments.NavigationDrawerFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArtesianActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    private final List blockedKeys = new ArrayList(Arrays.asList(KeyEvent.KEYCODE_VOLUME_DOWN, KeyEvent.KEYCODE_VOLUME_UP, KeyEvent.KEYCODE_HOME));
    private NavigationDrawerFragment mNavigationDrawerFragment;
    public static ImageView imageViewArtesian;
    public static TextView textViewArtesian;
    private ArrayList<ArtesianBlend> mArtesianBlendList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = this.getIntent();
        setupKioskState();
        setContentView(R.layout.activity_artesian);
        imageViewArtesian = (ImageView)findViewById(R.id.imageViewArtesianActivity);
        textViewArtesian = (TextView)findViewById(R.id.textViewArtesianActivity);
        ListView listViewArtesian = (ListView)findViewById(R.id.listViewArtesianActivity);
        Context ctx = ArtesianActivity.this.getApplicationContext();

        setLogo();
        mNavigationDrawerFragment = MainActivity.mNavigationDrawerFragment;
        mArtesianBlendList = new ArrayList<>();
        mArtesianBlendList = intent.getParcelableArrayListExtra("artesians");
        textViewArtesian.setText(mArtesianBlendList.get(0).getVqCategory());
        Log.v("CATEGORYSET", textViewArtesian.getText().toString());
        //Todo:Setup List using ArtesianBlendAdapter
        listViewArtesian.setAdapter(new ArtesianAdapter(ctx,R.layout.item_artesian_blend,mArtesianBlendList));
        Log.v("NUMBEROFSENT",String.valueOf(mArtesianBlendList.size()));
    }

    @Override
    public void onBackPressed(){
        //Nothing
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

    private void setLogo(){
        Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/Download/logo.png");
        ArtesianActivity.imageViewArtesian.setImageBitmap(bitmap);
    }

}
