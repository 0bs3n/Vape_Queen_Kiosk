package com.thevapequeen.vapequeenkiosk.housejuices;
/**
 * Created by James Campbell for exclusive use by The Vape Queen. All rights reserved.
 */

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.R;

import java.util.List;

/*
 * Here you can control what to do next when the user selects an item
 */
public class ArtesianBlendAdapter extends BaseAdapter {

    private List<ArtesianBlend> artesianElements;
    private LayoutInflater vi;

    public ArtesianBlendAdapter(List<ArtesianBlend> artesianElements, LayoutInflater inflater) {
        this.artesianElements = artesianElements;
        vi = inflater;
    }

    /**
     * Method get count of category list
     */
    @Override
    public int getCount() {
        return artesianElements.size();
    }

    /**
     * Method get item position
     */
    @Override
    public Object getItem(int position) {
        return artesianElements.get(position);
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
            itemAdapter.jImage = (ImageView) convertView.findViewById(R.id.imageViewHouseJuice);
            itemAdapter.jName = (TextView) convertView.findViewById(R.id.textViewHouseName);
            itemAdapter.jDescription = (TextView) convertView.findViewById(R.id.textViewHouseDescription);
            itemAdapter.jVG = (TextView) convertView.findViewById(R.id.textViewHouseVG);
            itemAdapter.jPG = (TextView) convertView.findViewById(R.id.textViewHousePG);
            itemAdapter.jNumber = (TextView) convertView.findViewById(R.id.textViewHouseNumber);
            convertView.setTag(itemAdapter);
        } else {
            itemAdapter = (ViewItem) convertView.getTag();
        }
        ArtesianBlend currentJuice = artesianElements.get(position);

        // Add received info to UI
        itemAdapter.jImage.setImageResource(R.drawable.logo_thumbnail);
        itemAdapter.jName.setText(currentJuice.getVqName());
        itemAdapter.jDescription.setText(currentJuice.getVqDescription());
        itemAdapter.jVG.setText(currentJuice.getVqVGratio());
        itemAdapter.jPG.setText(currentJuice.getVqPGratio());
        itemAdapter.jNumber.setText(currentJuice.getVqNumber());

        return convertView;
    }

    private class ViewItem {
        ImageView jImage;
        TextView jName;
        TextView jDescription;
        TextView jVG;
        TextView jPG;
        TextView jNumber;

    }

}
