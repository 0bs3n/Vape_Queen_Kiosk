package com.thevapequeen.vapequeenkiosk.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.thevapequeen.vapequeenkiosk.R;
import com.thevapequeen.vapequeenkiosk.artesianblends.ArtesianAdapter;
import com.thevapequeen.vapequeenkiosk.artesianblends.ArtesianBlend;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumAdapter;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuice;

import java.util.ArrayList;
import java.util.List;


public class ItemFragment extends Fragment {
    private String mJuiceType;
    private List<ArtesianBlend> mArtesianItemList = new ArrayList<>();
    private ArrayList<PremiumJuice> mPremiumItemList = new ArrayList<>();
    private ArtesianAdapter artesianAdapter;
    private PremiumAdapter premiumAdapter;

    OnFragmentInteractionListener mListener;

    private ListView listView;

    public ItemFragment newInstance(String mJuiceType) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    public ItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_item, container, false);
        artesianAdapter = new ArtesianAdapter(mArtesianItemList,inflater);
        premiumAdapter = new PremiumAdapter(mPremiumItemList,inflater);
        listView = (ListView)v.findViewById(R.id.listViewMain);
        listView.setAdapter(new ArtesianAdapter(mArtesianItemList,getActivity().getLayoutInflater()));
        // Inflate the layout for this fragment
        return v;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {

        public void onFragmentItemInteraction(String string);

    }

    public void setupArtesianList(String artesian, List<ArtesianBlend> artesianBlends){
        mJuiceType = artesian;
        mArtesianItemList = artesianBlends;
        Log.v("LIST_JUICE_TYPE", mJuiceType);
        //listView.setAdapter(new ArtesianAdapter(mArtesianItemList,getActivity().getLayoutInflater()));
        Toast.makeText(getActivity(),mJuiceType,Toast.LENGTH_SHORT).show();
    }

    public void setupPremiumList(String premium, ArrayList<PremiumJuice> premiumJuices){
        mJuiceType = premium;
        mPremiumItemList = premiumJuices;
        Log.v("LIST_JUICE_TYPE", mJuiceType);
        //listView.setAdapter(new PremiumAdapter(mPremiumItemList, getActivity().getLayoutInflater()));
        Toast.makeText(getActivity(),mJuiceType,Toast.LENGTH_SHORT).show();
    }

}
