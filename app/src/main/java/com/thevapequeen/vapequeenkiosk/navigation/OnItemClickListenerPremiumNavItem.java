package com.thevapequeen.vapequeenkiosk.navigation;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.thevapequeen.vapequeenkiosk.MainActivity;
import com.thevapequeen.vapequeenkiosk.R;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuice;

import java.util.List;

/**
 * Created by Human on 7/25/2015.
 */
public class OnItemClickListenerPremiumNavItem implements OnItemClickListener {

    public static String _mBrand;
    List<PremiumJuice> premiumJuiceList = MainActivity.premiumJuiceList;

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        _mBrand = new String();
        Context context = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewNavItem));

        // get the clicked item name
        String listItemText = textViewItem.getText().toString();
        _mBrand = listItemText;


        //create proper list and assign it to the proper MainActivity list
        //Refresh notify adapter
        //MainActivity.mlistViewMainArtesian.setVisibility(View.VISIBLE);

        // just toast it
        Toast.makeText(context, _mBrand, Toast.LENGTH_LONG).show();
        NavigationDrawerFragment.mDrawerLayout.closeDrawer(NavigationDrawerFragment.mFragmentContainerView);
    }
}
