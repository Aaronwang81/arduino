package com.ai.robot.ipurifier.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.ai.robot.ipurifier.R;
import com.ai.robot.ipurifier.VoiceAssisant;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link VoiceAssisantFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link VoiceAssisantFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VoiceAssisantFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private VoiceAssisant _voiceAssisant = null;

    public VoiceAssisantFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VoiceAssisantFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VoiceAssisantFragment newInstance(String param1, String param2) {
        VoiceAssisantFragment fragment = new VoiceAssisantFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_voice_assisant, container, false);

        _voiceAssisant = new VoiceAssisant(view.getContext());

        Button btnStartVA = view.findViewById(R.id.btnStartVA);
        Button btnStopVA = view.findViewById(R.id.btnStopVA);
        Button btnCancelVA = view.findViewById(R.id.btnCancelVA);

        btnStartVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _voiceAssisant.start();
            }
        });

        btnStopVA.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                _voiceAssisant.stop();
            }
        });

        btnCancelVA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _voiceAssisant.cancel();
            }
        });


        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        _voiceAssisant.cancel();
        _voiceAssisant.release();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
