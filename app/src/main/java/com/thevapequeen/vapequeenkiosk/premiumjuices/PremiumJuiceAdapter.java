package com.thevapequeen.vapequeenkiosk.premiumjuices;
/**
 * Created by James Campbell for exclusive use by The Vape Queen. All rights reserved.
 */
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.R;

import java.util.ArrayList;
import java.util.List;

/*
 * Here you can control what to do next when the user selects an item
 */
public class PremiumJuiceAdapter extends BaseAdapter {

    private List<PremiumJuice> premiumElements = new ArrayList<>();
    private LayoutInflater vi;

    public PremiumJuiceAdapter(List<PremiumJuice> premiumElements, LayoutInflater inflater) {
        this.premiumElements = premiumElements;
        vi = inflater;
    }

    public PremiumJuiceAdapter(){

    }

    /**
     * Method get count of category list
     */
    @Override
    public int getCount() {
        return premiumElements.size();
    }

    /**
     * Method get item position
     */
    @Override
    public Object getItem(int position) {
        return premiumElements.get(position);
    }

    /**
     * Method get item id based on position
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * Method to get custom adapter view initialized
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewItem itemAdapter;

        if (convertView == null) {
            convertView = vi.inflate(R.layout.item_premium_juice, null);
            itemAdapter = new ViewItem();
            itemAdapter.jName = (TextView) convertView.findViewById(R.id.textViewPremiumName);
            itemAdapter.jDescription = (TextView) convertView.findViewById(R.id.textViewPremiumDescription);
            itemAdapter.jVG = (TextView) convertView.findViewById(R.id.textViewPremiumVG);
            itemAdapter.jPG = (TextView) convertView.findViewById(R.id.textViewPremiumPG);
            convertView.setTag(itemAdapter);
        } else {
            itemAdapter = (ViewItem) convertView.getTag();
        }
        PremiumJuice currentJuice = premiumElements.get(position);

        // Add received info to UI
        itemAdapter.jName.setText(currentJuice.getPjName());
        itemAdapter.jDescription.setText(currentJuice.getPjDescription());
        itemAdapter.jVG.setText(currentJuice.getPjVGratio());
        itemAdapter.jPG.setText(currentJuice.getPjPGratio());

        return convertView;
    }

    private class ViewItem {
        TextView jName;
        TextView jDescription;
        TextView jVG;
        TextView jPG;

    }

}
