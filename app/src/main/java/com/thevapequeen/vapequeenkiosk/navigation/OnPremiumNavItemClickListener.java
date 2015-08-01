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
import android.widget.Toast;

import com.thevapequeen.vapequeenkiosk.MainActivity;
import com.thevapequeen.vapequeenkiosk.R;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * Here you can control what to do next when the user selects an item
 */
public class OnPremiumNavItemClickListener implements OnItemClickListener {

    public static String _Brand;
    public static Bitmap _mBitmap;
    public static List<PremiumJuice> premiumJuiceList = new ArrayList<>();


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Setup Text Change
        setupPremiumTextView(view);
        MainActivity.textView.setText(_Brand);

        //Setup List Change
        setupPremiumBrandList(_Brand, view);

        //Setup Image Change
        setupPremiumImageView();

        // toast the category
        Toast.makeText(view.getContext(), _Brand, Toast.LENGTH_LONG).show();

        //close the drawer
        NavigationDrawerFragment.mDrawerLayout.closeDrawer(NavigationDrawerFragment.mFragmentContainerView);
    }

    private void setupPremiumTextView(View view) {
        _Brand = null;
        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewNavItem));
        String listItemText = textViewItem.getText().toString();
        _Brand = listItemText;
        MainActivity.textView.setText(_Brand);
    }

    public void setupPremiumBrandList(String brand, View v){
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

    private void setupPremiumImageView(){
        _mBitmap = null;
        _mBitmap = BitmapFactory.decodeFile(premiumJuiceList.get(0).getPjImageFilePath());
        MainActivity.imageView.setImageBitmap(_mBitmap);
    }



}
