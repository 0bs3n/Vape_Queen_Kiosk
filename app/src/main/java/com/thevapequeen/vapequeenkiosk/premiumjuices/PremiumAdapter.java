package com.thevapequeen.vapequeenkiosk.premiumjuices;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.R;

import java.util.List;

public class PremiumAdapter extends ArrayAdapter<PremiumJuice> {

    public PremiumAdapter(Context context, List<PremiumJuice> items) {
        super(context, R.layout.item_premium_juice, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if(convertView == null) {
            // inflate the GridView item layout
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_premium_juice, parent, false);

            // initialize the view holder
            viewHolder = new ViewHolder();

            viewHolder.prJuiceName = (TextView) convertView.findViewById(R.id.textViewPremiumName);
            viewHolder.prJuiceVG = (TextView) convertView.findViewById(R.id.textViewPremiumVG);
            viewHolder.prJuicePG= (TextView) convertView.findViewById(R.id.textViewPremiumPG);
            viewHolder.prJuiceDescription = (TextView) convertView.findViewById(R.id.textViewPremiumDescription);

            convertView.setTag(viewHolder);
        } else {
            // recycle the already inflated view
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // update the item view
        PremiumJuice item = getItem(position);


        viewHolder.prJuiceName.setText(item.getPjName());
        viewHolder.prJuiceVG.setText(item.getPjVGratio());
        viewHolder.prJuicePG.setText(item.getPjPGratio());
        viewHolder.prJuiceDescription.setText(item.getPjDescription());

        return convertView;
    }

    /**
     * The view holder design pattern prevents using findViewById()
     * repeatedly in the getView() method of the adapter.
     *
     *
     */
    private static class ViewHolder {
        TextView prJuiceName;
        TextView prJuiceVG;
        TextView prJuicePG;
        TextView prJuiceDescription;
    }
}
