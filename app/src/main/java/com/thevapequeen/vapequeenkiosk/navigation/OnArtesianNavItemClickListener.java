package com.thevapequeen.vapequeenkiosk.navigation;

/**
 * Created by Human on 7/21/2015.
 */


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.R;
import com.thevapequeen.vapequeenkiosk.artesianblends.ArtesianActivity;
import com.thevapequeen.vapequeenkiosk.artesianblends.ArtesianBlend;
import com.thevapequeen.vapequeenkiosk.fragments.NavigationDrawerFragment;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Here you can control what to do next when the user selects an item
 */
public class OnArtesianNavItemClickListener implements OnItemClickListener {
    public static Context _mContext;
    public static String _mCategory;
    public static ArrayList<ArtesianBlend> _artesianBlendList = new ArrayList<>();

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        _mContext = view.getContext();
        _mCategory = ((TextView)view.findViewById(R.id.textViewNavItem)).getText().toString();

        //Setup ArtesianActivity List
        setupArtesianBrandList(_mCategory);
        //close the drawer
        NavigationDrawerFragment.mDrawerLayout.closeDrawer(NavigationDrawerFragment.mFragmentContainerView);
    }

    public void setupArtesianBrandList(String brand){
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

            Intent intent = new Intent(_mContext, ArtesianActivity.class);
            intent.putParcelableArrayListExtra("artesians", _artesianBlendList);
            _mContext.startActivity(intent);
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
