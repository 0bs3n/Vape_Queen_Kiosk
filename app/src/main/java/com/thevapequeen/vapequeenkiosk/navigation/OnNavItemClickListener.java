package com.thevapequeen.vapequeenkiosk.navigation;

/**
 * Created by Human on 7/21/2015.
 */

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.thevapequeen.vapequeenkiosk.MainActivity;
import com.thevapequeen.vapequeenkiosk.R;
import com.thevapequeen.vapequeenkiosk.housejuices.ArtesianBlend;
import com.thevapequeen.vapequeenkiosk.housejuices.ArtesianBlendAdapter;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuice;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuiceAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Here you can control what to do next when the user selects an item
 */
public class OnNavItemClickListener implements OnItemClickListener {

    public static String _mCategory;
    public static ArtesianBlendAdapter artesianBlendAdapter;
    public static PremiumJuiceAdapter premiumJuiceAdapter;

    public static String houseJuiceFile = null;
    public static String premiumJuiceFile = null;

    public static List<ArtesianBlend> artesianBlendList;
    public static List<PremiumJuice> premiumJuiceList;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        _mCategory = null;
        Context context = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewNavItem));

        // get the clicked item name
        String listItemText = textViewItem.getText().toString();
        _mCategory = listItemText;
        MainActivity.changeJuiceFrag();
        // just toast it
        Toast.makeText(context,_mCategory, Toast.LENGTH_LONG).show();
        NavigationDrawerFragment.mDrawerLayout.closeDrawer(NavigationDrawerFragment.mFragmentContainerView);
    }

    private void setupArtesianJuiceList(){
        artesianBlendList = new ArrayList<>();
        String csvFile = houseJuiceFile;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] _artesianjuice = line.split(cvsSplitBy);
                artesianBlendList.add(new ArtesianBlend(_artesianjuice[0],_artesianjuice[1],_artesianjuice[2],_artesianjuice[3],_artesianjuice[4],_artesianjuice[5]));
            }
        }  catch (IOException e) {
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

    private void setupPremiumJuiceList(){
        premiumJuiceList = new ArrayList<>();
        String csvFile = premiumJuiceFile;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] _premiumjuice = line.split(cvsSplitBy);
                premiumJuiceList.add(new PremiumJuice(_premiumjuice[0],_premiumjuice[1],_premiumjuice[2],_premiumjuice[3],_premiumjuice[4],_premiumjuice[5]));
            }
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
