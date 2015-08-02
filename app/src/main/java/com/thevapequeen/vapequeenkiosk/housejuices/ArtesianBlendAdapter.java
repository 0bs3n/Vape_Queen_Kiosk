package com.thevapequeen.vapequeenkiosk.housejuices;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Human on 8/1/2015.
 */
public class ArtesianBlendAdapter extends ArrayAdapter<ArtesianBlend> {

    private final Activity context;
    private List<ArtesianBlend> artesianBlendList = new ArrayList<>();

    public ArtesianBlendAdapter(Activity context,List<ArtesianBlend> artesianBlendList){
        super(context, R.layout.item_artesian_blend);
        this.context = context;
        this.artesianBlendList = artesianBlendList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.item_artesian_blend, null, true);

        ImageView imageViewHouseJuice = (ImageView) rowView.findViewById(R.id.imageViewHouseJuice);
        TextView txtViewHouseName = (TextView) rowView.findViewById(R.id.textViewHouseName);
        TextView txtViewHouseDescription = (TextView) rowView.findViewById(R.id.textViewHouseDescription);
        TextView txtViewHouseVG = (TextView) rowView.findViewById(R.id.textViewHouseVG);
        TextView txtViewHousePG = (TextView) rowView.findViewById(R.id.textViewHousePG);
        TextView txtViewHouseNumber = (TextView) rowView.findViewById(R.id.textViewHouseNumber);

        return rowView;
    }

}
