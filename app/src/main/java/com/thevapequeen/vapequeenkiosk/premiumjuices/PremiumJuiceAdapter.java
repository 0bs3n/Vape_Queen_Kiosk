package com.thevapequeen.vapequeenkiosk.premiumjuices;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Human on 8/1/2015.
 */
public class PremiumJuiceAdapter extends ArrayAdapter<PremiumJuice> {

    private final Activity context;
    private List<PremiumJuice> premiumJuiceList = new ArrayList<>();

    public PremiumJuiceAdapter(Activity context,List<PremiumJuice> premiumJuiceList){
        super(context, R.layout.item_premium_juice);
        this.context = context;
        this.premiumJuiceList = premiumJuiceList;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.item_premium_juice, null, true);

        TextView txtViewPremiumName = (TextView) rowView.findViewById(R.id.textViewPremiumName);
        TextView txtViewPremiumDescription = (TextView) rowView.findViewById(R.id.textViewPremiumDescription);
        TextView txtViewPremiumVG = (TextView) rowView.findViewById(R.id.textViewPremiumVG);
        TextView txtViewPremiumPG = (TextView) rowView.findViewById(R.id.textViewPremiumPG);

        return rowView;
    }
}
