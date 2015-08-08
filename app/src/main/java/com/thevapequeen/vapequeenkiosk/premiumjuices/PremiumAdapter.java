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

import java.util.List;

public class PremiumAdapter extends BaseAdapter {

    private List<PremiumJuice> juiceItems;
    private LayoutInflater vi;

    public PremiumAdapter(List<PremiumJuice> juiceItems,LayoutInflater inflater) {
        this.juiceItems = juiceItems;
        vi = inflater;
    }

    /**
     * Method get count of category list
     */
    @Override
    public int getCount() {
        return juiceItems.size();
    }

    /**
     * Method get item position
     */
    @Override
    public Object getItem(int position) {
        return juiceItems.get(position);
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
            itemAdapter.jVG = (TextView) convertView.findViewById(R.id.textViewPremiumVG);
            itemAdapter.jPG = (TextView) convertView.findViewById(R.id.textViewPremiumPG);
            itemAdapter.jDescription = (TextView) convertView.findViewById(R.id.textViewPremiumDescription);
            convertView.setTag(itemAdapter);
        } else {
            itemAdapter = (ViewItem) convertView.getTag();
        }
        PremiumJuice currentJuice = juiceItems.get(position);
        // Add received info to UI
        itemAdapter.jName.setText(currentJuice.getPjName());
        itemAdapter.jVG.setText(currentJuice.getPjVGratio());
        itemAdapter.jPG.setText(currentJuice.getPjPGratio());
        itemAdapter.jDescription.setText(currentJuice.getPjDescription());

        return convertView;
    }

    public void refreshDeviceList(List<PremiumJuice> list) {
        this.juiceItems = list;
        notifyDataSetChanged();
    }

    private class ViewItem {
        TextView jName;
        TextView jVG;
        TextView jPG;
        TextView jDescription;
    }


}
