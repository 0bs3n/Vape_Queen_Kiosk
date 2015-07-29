package com.thevapequeen.vapequeenkiosk.navigation;

/**
 * Created by Human on 7/21/2015.
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.thevapequeen.vapequeenkiosk.MainActivity;
import com.thevapequeen.vapequeenkiosk.R;
import com.thevapequeen.vapequeenkiosk.housejuices.ArtesianBlend;
import com.thevapequeen.vapequeenkiosk.housejuices.ArtesianBlendAdapter;

import java.io.BufferedReader;
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

    public static ArtesianBlendAdapter artesianBlendAdapter;
    public static String houseJuiceFile = null;
    public static List<ArtesianBlend> artesianBlendList;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        houseJuiceFile = view.getResources().getString(R.string.artesian_juice_file);

        //Setup Text Change
        setupTextView(view);

        //Setup Image Change
        setupImageView(view);

        //Setup List Change
        setupArtesianJuiceList();

        MainActivity.changeArtesianJuiceFrag();

        // toast the category
        Toast.makeText(view.getContext(),_mCategory, Toast.LENGTH_LONG).show();

        //close the drawer
        NavigationDrawerFragment.mDrawerLayout.closeDrawer(NavigationDrawerFragment.mFragmentContainerView);
    }

    private void setupTextView(View view) {
        _mCategory = null;
        Context context = view.getContext();
        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewNavItem));
        String listItemText = textViewItem.getText().toString();
        _mCategory = listItemText;
    }

    private void setupImageView(View view){
        _mBitmap = null;
        _mBitmap = BitmapFactory.decodeResource(view.getContext().getResources(),R.drawable.logo);
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
                if(_mCategory.equals(_artesianjuice[5])){
                    artesianBlendList.add(new ArtesianBlend(_artesianjuice[0],_artesianjuice[1],_artesianjuice[2],_artesianjuice[3],_artesianjuice[4],_artesianjuice[5]));
                }
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

}
