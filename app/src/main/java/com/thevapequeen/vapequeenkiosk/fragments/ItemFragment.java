package com.thevapequeen.vapequeenkiosk.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.thevapequeen.vapequeenkiosk.R;
import com.thevapequeen.vapequeenkiosk.artesianblends.ArtesianBlend;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuice;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ItemFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ItemFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_TYPE = "";
    private static final ArrayList<ArtesianBlend> ARG_LIST = new ArrayList<>();

    // TODO: Rename and change types of parameters
    String mJuiceType;
    public static ArrayList<ArtesianBlend> mArtesianItemList = new ArrayList<>();
    public static ArrayList<PremiumJuice> mPremiumItemList = new ArrayList<>();

    public static ListView listView;

    private OnFragmentInteractionListener mListener;

    public static ItemFragment newInstance(String mJuiceType) {
        ItemFragment fragment = new ItemFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TYPE, mJuiceType);
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
            mJuiceType = getArguments().getString(ARG_TYPE);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v= inflater.inflate(R.layout.fragment_item, container, false);
        listView = (ListView)v.findViewById(R.id.listViewMain);
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

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentItemInteraction(String string);

    }

    public static void setupArtesianList(String artesian, ArrayList<ArtesianBlend> artesianBlends){

        mArtesianItemList = artesianBlends;
        Log.v("ARTESIAN", mArtesianItemList.get(0).getVqName());

    }

    public static void setupPremiumList(String premium, ArrayList<PremiumJuice> premiumJuices){

        mPremiumItemList = premiumJuices;
        Log.v("PREMIUM", mPremiumItemList.get(0).getPjName());

    }

}
