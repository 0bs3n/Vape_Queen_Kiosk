package com.thevapequeen.vapequeenkiosk.artesianblends;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.R;

import java.util.ArrayList;
import java.util.List;

public class ArtesianAdapter extends ArrayAdapter {

    public static LayoutInflater mInflater;
    public static List<ArtesianBlend> artesianBlends = new ArrayList<>();
    public static int mViewResourceId;

    //constructor
    public ArtesianAdapter(Context ctx, int viewResourceId,ArrayList<ArtesianBlend> artesianBlends) {
        super(ctx, viewResourceId, artesianBlends);
        mInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        artesianBlends = artesianBlends;
        mViewResourceId = viewResourceId;
        Log.v("Alistsizein", String.valueOf(artesianBlends.size()));
    }

    @Override
    public int getCount() {
        return artesianBlends.size();
    }

    @Override
    public ArtesianBlend getItem(int position) {
        return artesianBlends.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.v("Aposition", String.valueOf(artesianBlends.size()));
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = mInflater.inflate(mViewResourceId, parent, false);

            holder.artNumber = (TextView) convertView.findViewById(R.id.textViewHouseNumber);
            holder.artName = (TextView) convertView.findViewById(R.id.textViewHouseName);
            holder.artVG = (TextView) convertView.findViewById(R.id.textViewHouseVG);
            holder.artPG = (TextView) convertView.findViewById(R.id.textViewHousePG);
            holder.artDescription = (TextView) convertView.findViewById(R.id.textViewHouseDescription);

            convertView.setTag(holder);
        }

        else
        {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.artNumber.setText(artesianBlends.get(position).getVqNumber());
        holder.artName.setText(artesianBlends.get(position).getVqName());
        holder.artVG.setText(artesianBlends.get(position).getVqVGratio());
        holder.artPG.setText(artesianBlends.get(position).getVqPGratio());
        holder.artDescription.setText(artesianBlends.get(position).getVqDescription());

        return convertView;
    }
}

  class ViewHolder {

    TextView artNumber;
    TextView artName;
    TextView artVG;
    TextView artPG;
    TextView artDescription;
}
