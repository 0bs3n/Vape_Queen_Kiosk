package com.thevapequeen.vapequeenkiosk.premiumjuices;
/**
 * Created by James Campbell for exclusive use by The Vape Queen. All rights reserved.
 */
import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.thevapequeen.vapequeenkiosk.R;

/*
 * Here you can control what to do next when the user selects an item
 */
public class PremiumJuiceAdapter implements OnItemClickListener {

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Context context = view.getContext();

        TextView textViewItem = ((TextView) view.findViewById(R.id.textViewNavItem));

        // get the clicked item name
        String listItemText = textViewItem.getText().toString();

        // just toast it
        Toast.makeText(context, listItemText, Toast.LENGTH_LONG).show();

    }

}
