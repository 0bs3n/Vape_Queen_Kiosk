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

/*
 * Here you can control what to do next when the user selects an item
 */
public class OnArtesianNavItemClickListener implements OnItemClickListener {

    public static String _mCategory;
    public static Bitmap _mBitmap;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        //Setup Text Change
        setupTextView(view);
        MainActivity.textView.setText(_mCategory);

        //Setup Image Change
        setupImageView(view);

        // toast the category
        Toast.makeText(view.getContext(),_mCategory, Toast.LENGTH_LONG).show();

        //close the drawer
        NavigationDrawerFragment.mDrawerLayout.closeDrawer(NavigationDrawerFragment.mFragmentContainerView);
    }

    private void setupTextView(View view) {
        _mCategory = null;
        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewNavItem));
        String listItemText = textViewItem.getText().toString();
        _mCategory = listItemText;
    }

    private void setupImageView(View view){
        _mBitmap = null;
        _mBitmap = BitmapFactory.decodeResource(view.getContext().getResources(),R.drawable.logo);
    }



}
