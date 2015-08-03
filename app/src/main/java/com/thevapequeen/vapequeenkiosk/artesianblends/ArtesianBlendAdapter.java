package com.thevapequeen.vapequeenkiosk.artesianblends;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Human on 8/3/2015.
 */
public class ArtesianBlendAdapter extends ArrayAdapter<ArtesianBlend> {
    Context mContext;
    int layoutResourceId;
    List<ArtesianBlend> artesianBlendList = new ArrayList<>();

    public ArtesianBlendAdapter(Context mContext, int layoutResourceId, List<ArtesianBlend> artesianBlendList) {

        super(mContext, layoutResourceId, artesianBlendList);

        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.artesianBlendList = artesianBlendList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        /*
         * The convertView argument is essentially a "ScrapView" as described is Lucas post
         * http://lucasr.org/2012/04/05/performance-tips-for-androids-listview/
         * It will have a non-null value when ListView is asking you recycle the row layout.
         * So, when convertView is not null, you should simply update its contents instead of inflating a new row layout.
         */
        if(convertView==null){
            // inflate the layout
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutResourceId, parent, false);
        }

        // object item based on the position
        ArtesianBlend objectItem = artesianBlendList.get(position);

        // get the TextView and then set the text (item name) and tag (item ID) values
        TextView textViewItemName = (TextView) convertView.findViewById(R.id.textViewHouseName);
        textViewItemName.setText(objectItem.getVqName());
        TextView textViewItemDescription = (TextView) convertView.findViewById(R.id.textViewHouseDescription);
        textViewItemDescription.setText(objectItem.getVqName());
        TextView textViewItemVG = (TextView) convertView.findViewById(R.id.textViewHouseVG);
        textViewItemVG.setText(objectItem.getVqName());
        TextView textViewItemPG = (TextView) convertView.findViewById(R.id.textViewHousePG);
        textViewItemPG.setText(objectItem.getVqName());
        TextView textViewItemNumber = (TextView) convertView.findViewById(R.id.textViewHouseNumber);
        textViewItemNumber.setText(objectItem.getVqName());

        return convertView;

    }
}
