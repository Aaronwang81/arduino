package com.ai.robot.ipurifier.view;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;

import com.ai.robot.ipurifier.R;
import com.ai.robot.ipurifier.feature.mainpage.IMainPageView;
import com.ai.robot.ipurifier.manager.IDeviceModel;
import com.ai.robot.ipurifier.manager.PresenterManager;
import com.ai.robot.ipurifier.manager.UIManager;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements IMainPageView {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private ArrayAdapter<String> _adapter = null;

    public MainFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
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
        View view =  inflater.inflate(R.layout.fragment_main, container, false);

        Button btnMove = view.findViewById(R.id.btnMoveController);
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIManager.getInstance().showMovementController();
            }
        });

        Button btnVoiceAssisant = view.findViewById(R.id.btnVoiceAssisant);
        btnVoiceAssisant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIManager.getInstance().showVoiceAssisant();
            }
        });

        Switch swPurifier = view.findViewById(R.id.swPurifier);
        swPurifier.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){

                }else{

                }

            }
        });
        swPurifier.setTextOn(getString(R.string.purifier_is_on));
        swPurifier.setTextOff(getString(R.string.purifier_is_off));


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

        PresenterManager.getInstance().getMainPagePresenter().registerView(this);
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                displayDevices();
            }
        }, 1);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        PresenterManager.getInstance().getMainPagePresenter().unregisterView(this);
        mListener = null;
    }

    @Override
    public void onNewDeviceCome(IDeviceModel.DeviceInfo deviceInfo) {

        ListView deviceList = getView().findViewById(R.id.listDeviceInfo);
        if(null == _adapter){
            _adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1);
            deviceList.setAdapter(_adapter);
        }
        String item = "ID: " + deviceInfo._device.getDeviceId() + ", type: " + deviceInfo._type;
        _adapter.add(item);
        _adapter.notifyDataSetChanged();


    }

    @Override
    public void onDeviceRemoved(IDeviceModel.DeviceInfo deviceInfo) {
        displayDevices();

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

    private void displayDevices(){
        List<IDeviceModel.DeviceInfo> deviceInfoList = PresenterManager.getInstance().getMainPagePresenter().getDeviceList();
        ListView deviceList = getView().findViewById(R.id.listDeviceInfo);
        if(null == _adapter){
            _adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1);
            deviceList.setAdapter(_adapter);
        }
        _adapter.clear();
        for (IDeviceModel.DeviceInfo deviceInfo : deviceInfoList) {
            String item = "ID: " + deviceInfo._device.getDeviceId() + ", type: " + deviceInfo._type;
            _adapter.add(item);
        }
        _adapter.notifyDataSetChanged();
    }
}
