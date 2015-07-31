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
public class OnPremiumNavItemClickListener implements OnItemClickListener {

    public static String _Brand;
    public static Bitmap _mBitmap;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        //Setup Text Change
        setupPremiumTextView(view);
        MainActivity.textView.setText(_Brand);

        //Setup Image Change
        setupPremiumImageView(view);

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
    }

    private void setupPremiumImageView(View view){
        _mBitmap = null;
        _mBitmap = BitmapFactory.decodeResource(view.getContext().getResources(),R.drawable.logo);
    }



}
