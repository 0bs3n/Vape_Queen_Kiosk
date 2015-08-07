package com.thevapequeen.vapequeenkiosk.artesianblends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.R;

import java.util.List;

public class ArtesianAdapter extends ArrayAdapter<ArtesianBlend> {

    public ArtesianAdapter(Context context, List<ArtesianBlend> items) {
        super(context, R.layout.item_artesian_blend, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_artesian_blend, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();

            viewHolder.vqJuiceNumber = (TextView) convertView.findViewById(R.id.textViewHouseNumber);
            viewHolder.vqJuiceNumber = (TextView) convertView.findViewById(R.id.textViewHouseName);
            viewHolder.vqJuiceNumber = (TextView) convertView.findViewById(R.id.textViewHouseVG);
            viewHolder.vqJuiceNumber = (TextView) convertView.findViewById(R.id.textViewHousePG);
            viewHolder.vqJuiceNumber = (TextView) convertView.findViewById(R.id.textViewHouseDescription);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        ArtesianBlend item = getItem(position);

        viewHolder.vqJuiceNumber.setText(item.getVqNumber());
        viewHolder.vqJuiceName.setText(item.getVqName());
        viewHolder.vqJuiceVG.setText(item.getVqVGratio());
        viewHolder.vqJuicePG.setText(item.getVqPGratio());
        viewHolder.vqJuiceDescription.setText(item.getVqName());

        return convertView;
    }

    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     *
     */
    private static class ViewHolder {
        TextView vqJuiceNumber;
        TextView vqJuiceName;
        TextView vqJuiceVG;
        TextView vqJuicePG;
        TextView vqJuiceDescription;
    }
}
