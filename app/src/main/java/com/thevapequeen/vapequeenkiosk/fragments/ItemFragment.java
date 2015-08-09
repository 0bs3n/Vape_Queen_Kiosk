package com.thevapequeen.vapequeenkiosk.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thevapequeen.vapequeenkiosk.R;
import com.thevapequeen.vapequeenkiosk.artesianblends.ArtesianAdapter;
import com.thevapequeen.vapequeenkiosk.artesianblends.ArtesianBlend;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumAdapter;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuice;

import java.util.ArrayList;
import java.util.List;


public class ItemFragment extends ListFragment {
    String mJuiceType;
    private List<ArtesianBlend> mArtesianItemList;
    private ArrayList<PremiumJuice> mPremiumItemList;
    ArtesianAdapter artesianAdapter;
    PremiumAdapter premiumAdapter;

    OnFragmentInteractionListener mListener;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_item, null, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

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
        artesianAdapter = new ArtesianAdapter(getActivity(),mArtesianItemList);
        setListAdapter(artesianAdapter);

    }

    public void setupPremiumList(String premium, ArrayList<PremiumJuice> premiumJuices){
        mJuiceType = premium;
        mPremiumItemList = premiumJuices;
        premiumAdapter = new PremiumAdapter(getActivity(),mPremiumItemList);
        setListAdapter(premiumAdapter);
    }

}
