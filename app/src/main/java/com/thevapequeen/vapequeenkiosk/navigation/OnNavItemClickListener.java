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

/*
 * Here you can control what to do next when the user selects an item
 */
public class OnNavItemClickListener implements OnItemClickListener {

    public static String _mCategory;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        _mCategory = null;
        Context context = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewNavItem));

        // get the clicked item name
        String listItemText = textViewItem.getText().toString();
        _mCategory = listItemText;
        MainActivity.changeJuiceFragText();
        // just toast it
        Toast.makeText(context,_mCategory, Toast.LENGTH_LONG).show();
        NavigationDrawerFragment.mDrawerLayout.closeDrawer(NavigationDrawerFragment.mFragmentContainerView);

    }





}
