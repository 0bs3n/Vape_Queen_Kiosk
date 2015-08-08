package com.thevapequeen.vapequeenkiosk.artesianblends;

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

public class ArtesianAdapter extends BaseAdapter {

    private List<ArtesianBlend> juiceItems;
    private LayoutInflater vi;

    public ArtesianAdapter(List<ArtesianBlend> juiceItems,LayoutInflater inflater) {
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
            convertView = vi.inflate(R.layout.item_artesian_blend, null);
            itemAdapter = new ViewItem();
            itemAdapter.jNumber = (TextView) convertView.findViewById(R.id.textViewHouseNumber);
            itemAdapter.jName = (TextView) convertView.findViewById(R.id.textViewHouseName);
            itemAdapter.jVG = (TextView) convertView.findViewById(R.id.textViewHouseVG);
            itemAdapter.jPG = (TextView) convertView.findViewById(R.id.textViewHousePG);
            itemAdapter.jDescription = (TextView) convertView.findViewById(R.id.textViewHouseDescription);
            convertView.setTag(itemAdapter);
        } else {
            itemAdapter = (ViewItem) convertView.getTag();
        }
        ArtesianBlend currentJuice = juiceItems.get(position);
        // Add received info to UI
        itemAdapter.jNumber.setText(currentJuice.getVqNumber());
        itemAdapter.jName.setText(currentJuice.getVqName());
        itemAdapter.jVG.setText(currentJuice.getVqVGratio());
        itemAdapter.jPG.setText(currentJuice.getVqPGratio());
        itemAdapter.jDescription.setText(currentJuice.getVqDescription());

        return convertView;
    }

    public void refreshArtesianList(List<ArtesianBlend> list) {
        this.juiceItems = list;
        notifyDataSetChanged();
    }

    private class ViewItem {
        TextView jNumber;
        TextView jName;
        TextView jVG;
        TextView jPG;
        TextView jDescription;
    }


}
