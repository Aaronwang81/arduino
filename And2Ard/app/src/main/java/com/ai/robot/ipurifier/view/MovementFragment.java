package com.ai.robot.ipurifier.view;

import android.app.Fragment;
import android.content.Context;
import android.hardware.usb.UsbDevice;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.ai.robot.ipurifier.R;
import com.ai.robot.ipurifier.feature.move.IMoveControlView;
import com.ai.robot.ipurifier.feature.move.IMovePresenter;
import com.ai.robot.ipurifier.manager.PresenterManager;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovementFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovementFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovementFragment extends Fragment implements IMoveControlView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    public MovementFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovementFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovementFragment newInstance(String param1, String param2) {
        MovementFragment fragment = new MovementFragment();
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
        View view = inflater.inflate(R.layout.fragment_movement, container, false);
        init(view);
        return view;
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

        PresenterManager.getInstance().getMovePresenter().registerView(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        PresenterManager.getInstance().getMovePresenter().unregisterView(this);
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

    public void init(View view){

        Button btnForward = view.findViewById(R.id.btnForward);
        Button btnLeft = view.findViewById(R.id.btnLeft);
        Button btnRight = view.findViewById(R.id.btnRight);
        Button btnBack = view.findViewById(R.id.btnBack);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IMovePresenter presenter = PresenterManager.getInstance().getMovePresenter();
                switch (v.getId()){
                    case R.id.btnForward:
                        presenter.forward();
                        break;
                    case R.id.btnLeft:
                        presenter.left();
                        break;
                    case R.id.btnRight:
                        presenter.right();
                        break;
                    case R.id.btnBack:
                        presenter.back();
                        break;
                    default:
                        //nothing
                }

            }
        };

        btnForward.setOnClickListener(listener);
        btnLeft.setOnClickListener(listener);
        btnRight.setOnClickListener(listener);
        btnBack.setOnClickListener(listener);
    }

    @Override
    public void updateStatus(final String status) {
        if(Looper.getMainLooper().getThread() == Thread.currentThread()){
            TextView textStatus = getView().findViewById(R.id.textStatus);
            textStatus.setText(status);
        }else{
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView textStatus = getView().findViewById(R.id.textStatus);
                    textStatus.setText(status);
                }
            });
        }

    }
}
