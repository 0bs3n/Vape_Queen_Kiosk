package com.thevapequeen.vapequeenkiosk.navigation;

/**
 * Created by Human on 7/21/2015.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.MainActivity;
import com.thevapequeen.vapequeenkiosk.R;
import com.thevapequeen.vapequeenkiosk.artesianblends.ArtesianBlend;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Here you can control what to do next when the user selects an item
 */
public class OnArtesianNavItemClickListener implements OnItemClickListener {

    public static String _mCategory;
    public static Bitmap _mBitmap;
    public static List<ArtesianBlend> _artesianBlendList = new ArrayList<>();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        //Setup Text Change
        setupTextView(view);        

        //Setup Artesian List
        setupArtesianBrandList(_mCategory, view);
        
        //Setup ImageView
        setupImageView(view);



        //close the drawer
        NavigationDrawerFragment.mDrawerLayout.closeDrawer(NavigationDrawerFragment.mFragmentContainerView);
    }

    private void setupTextView(View view) {
        _mCategory = null;
        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewNavItem));
        String listItemText = textViewItem.getText().toString();
        _mCategory = listItemText;
        MainActivity.textView.setText(_mCategory);
    }

    public void setupArtesianBrandList(String brand, View view){
        _artesianBlendList.clear();
        String csvFile = "/sdcard/Download/artesian_juices.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] _artesianblend = line.split(cvsSplitBy);
                if(_artesianblend[5].equals(brand)){
                    _artesianBlendList.add(new ArtesianBlend(_artesianblend[0], _artesianblend[1], _artesianblend[2], _artesianblend[3], _artesianblend[4], _artesianblend[5]));
                }
            }
            ((MainActivity)view.getContext()).refreshArtesianListView();
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

    private void setupImageView(View view){
        _mBitmap = null;
        _mBitmap = BitmapFactory.decodeFile("/sdcard/Download/logo.png");
        MainActivity.imageView.setImageBitmap(_mBitmap);
    }

}
