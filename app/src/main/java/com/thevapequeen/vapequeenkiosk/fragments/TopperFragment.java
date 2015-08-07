package com.thevapequeen.vapequeenkiosk.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.R;

import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopperFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopperFragment extends Fragment {

    private OnFragmentInteractionListener mListenerFragmentTopper;
    private static ImageView imageViewTopper;
    private static TextView textViewTopper;
    public static AlphaAnimation anim = new AlphaAnimation(1.0f, 0.0f);
    private static TimerTask timerTask;
    public static Bitmap mBitmap;
    final Handler handler = new Handler();

    private String juiceType;
    private String juiceBrand;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_NAVTYPE = "juiceType";
    private static final String ARG_NAVBRAND = "juiceBrand";

    public static TopperFragment newInstance(String juiceType, String juiceBrand) {
        TopperFragment fragment = new TopperFragment();
        Bundle args = new Bundle();
        args.putString(ARG_NAVTYPE, "juiceType");
        args.putString(ARG_NAVBRAND, "juiceBrand");
        fragment.setArguments(args);
        return fragment;
    }

    public TopperFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_topper, container, false);

        imageViewTopper = (ImageView)view.findViewById(R.id.imageViewMain);
        setInitialLogo();

        textViewTopper = (TextView)view.findViewById(R.id.textViewMain);
        setInitialText();
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListenerFragmentTopper = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListenerFragmentTopper = null;
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
        public void onFragmentTopperInteraction(String textTopper);
    }

    private void setInitialLogo(){
        Bitmap bitmap = BitmapFactory.decodeFile("/sdcard/Download/logo.png");
        imageViewTopper.setImageBitmap(bitmap);

    }

    public static void setImageViewTopper(Bitmap bitmap){
        imageViewTopper.setImageBitmap(bitmap);
    }

    private void setInitialText(){
        textViewTopper.setText(getString(R.string.app_name));
    }

    public static void changeText(String juiceBrand){
        textViewTopper.setText(juiceBrand);
    }




}
