package com.thevapequeen.vapequeenkiosk.navigation;

/**
 * Created by Human on 7/21/2015.
 */

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.thevapequeen.vapequeenkiosk.PremiumActivity;
import com.thevapequeen.vapequeenkiosk.fragments.NavigationDrawerFragment;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Here you can control what to do next when the user selects an item
 */
public class OnPremiumNavItemClickListener implements OnItemClickListener {

    public static String _Brand;
    public static Context _mContext;
    public static ArrayList<PremiumJuice> premiumJuiceList = new ArrayList<>();


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        _mContext = view.getContext();

        //Todo:Get text from list textview
        //Setup List Change
        setupPremiumBrandList(_Brand);
        //close the drawer
        NavigationDrawerFragment.mDrawerLayout.closeDrawer(NavigationDrawerFragment.mFragmentContainerView);
    }

    public void setupPremiumBrandList(String brand){
        premiumJuiceList.clear();
        String csvFile = "/sdcard/Download/premium_juices.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] _premiumjuice = line.split(cvsSplitBy);
                if(_premiumjuice[5].equals(brand)){
                    premiumJuiceList.add(new PremiumJuice(_premiumjuice[0],_premiumjuice[1],_premiumjuice[2],_premiumjuice[3],_premiumjuice[4],_premiumjuice[5]));
                }
            }
            //Todo:Pass Extras
            Intent intent = new Intent(_mContext, PremiumActivity.class);
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
