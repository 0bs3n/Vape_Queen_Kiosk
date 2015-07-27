package com.thevapequeen.vapequeenkiosk.navigation;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.thevapequeen.vapequeenkiosk.R;
import com.thevapequeen.vapequeenkiosk.housejuices.ArtesianBlend;
import com.thevapequeen.vapequeenkiosk.housejuices.ArtesianBlendAdapter;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuice;
import com.thevapequeen.vapequeenkiosk.premiumjuices.PremiumJuiceAdapter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JuiceViewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JuiceViewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JuiceViewFragment extends Fragment {

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private String mParam1;

    ImageView imageViewMain;

    TextView textViewMain;

    public static Integer ticklecounter = 0;

    private OnFragmentInteractionListener mListener;

    public static ArtesianBlendAdapter artesianBlendAdapter;
    public static PremiumJuiceAdapter premiumJuiceAdapter;

    public static String houseJuiceFile = null;
    public static String premiumJuiceFile = null;

    public static List<ArtesianBlend> artesianBlendList;
    public static List<ArtesianBlend> artesianBlendsPerCategory = new ArrayList<>();
    public static List<PremiumJuice> premiumJuiceList;
    public static List<PremiumJuice> premiumJuicesPerBrand = new ArrayList<>();

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment JuiceViewFragment.
     */

    public static JuiceViewFragment newInstance(String param1) {
        JuiceViewFragment fragment = new JuiceViewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    public JuiceViewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);

            houseJuiceFile = getString(R.string.artesian_juice_file);
            premiumJuiceFile = getString(R.string.premium_juice_file);
            setupArtesianJuiceList();
            setupPremiumJuiceList();

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_juice_view, container, false);
        assert v != null;

        imageViewMain = (ImageView)v.findViewById(R.id.imageViewJuiceFragment);
        imageViewMain.setImageResource(R.drawable.logo);
        imageViewMain.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                int action = event.getActionMasked();
                switch (action) {
                    case MotionEvent.ACTION_MOVE:
                        ticklecounter++;
                        if (ticklecounter > 175) {
                            showExitPopup(v);
                        }
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        textViewMain = (TextView)v.findViewById(R.id.textViewJuiceFragment);

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

        public void onFragmentInteraction(Uri uri);
    }

    public void changeImage(Bitmap bitmap){
        imageViewMain.setImageBitmap(bitmap);
    }

    public void changeText(String string){
        textViewMain.setText(string);
    }

    private void showExitPopup(View v){
        ticklecounter = 0;
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
        // Setting Cancellation
        alertDialog.setCancelable(false);
        // Setting Dialog Title
        alertDialog.setTitle("Confirm Exit...");
        // Setting Dialog Message
        alertDialog.setMessage("Are you sure you want to exit the menu?");
        // Setting Icon to Dialog
        alertDialog.setIcon(R.drawable.logo_thumbnail);
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event
                dialog.cancel();
            }
        });
        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                getActivity().finish();
            }
        });
        // Showing Alert Message
        alertDialog.show();
    }

    private void setupArtesianJuiceList(){
        artesianBlendList = new ArrayList<>();
        String csvFile = houseJuiceFile;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] _artesianjuice = line.split(cvsSplitBy);
                artesianBlendList.add(new ArtesianBlend(_artesianjuice[0],_artesianjuice[1],_artesianjuice[2],_artesianjuice[3],_artesianjuice[4],_artesianjuice[5]));
            }
        }  catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void setupPremiumJuiceList(){
        premiumJuiceList = new ArrayList<>();
        String csvFile = premiumJuiceFile;
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] _premiumjuice = line.split(cvsSplitBy);
                premiumJuiceList.add(new PremiumJuice(_premiumjuice[0],_premiumjuice[1],_premiumjuice[2],_premiumjuice[3],_premiumjuice[4],_premiumjuice[5]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
