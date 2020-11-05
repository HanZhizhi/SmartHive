package com.space.smarthive.myhives;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.space.smarthive.R;
import com.space.smarthive.databinding.FragmentHivesBinding;
import com.space.smarthive.hivemanage.HivemanActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HivesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HivesFragment extends Fragment {
    private static final String TAG = "HivesFragment";
    private FragmentHivesBinding viewBinding;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Context context;
    
    private BluetoothAdapter btAdapter;
    private BluetoothManager btManager;
    private BluetoothLeScanner scanner;
    private ScanSettings.Builder settBuilder;
    private ScanCallback scanCallback;

    private RecyclerView rvHives;
    private HiveListAdapter rvAdapter;

    public HivesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HivesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HivesFragment newInstance(String param1, String param2) {
        HivesFragment fragment = new HivesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context = getActivity();


        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        btManager= (BluetoothManager) getActivity().getSystemService(Context.BLUETOOTH_SERVICE);
        btAdapter=btManager.getAdapter();
        scanner=btAdapter.getBluetoothLeScanner();

        settBuilder=new ScanSettings.Builder()
                .setScanMode(ScanSettings.SCAN_MODE_LOW_LATENCY);
        if (Build.VERSION.SDK_INT > 23){
            settBuilder.setCallbackType(ScanSettings.CALLBACK_TYPE_ALL_MATCHES);
            settBuilder.setMatchMode(ScanSettings.MATCH_MODE_STICKY);
        }

        scanCallback=new ScanCallback() {
            @Override
            public void onScanResult(int callbackType, ScanResult result) {
                super.onScanResult(callbackType, result);
                BluetoothDevice device=result.getDevice();
                int rssi=result.getRssi();
//                Log.i(TAG, "onScanResult: "+device.getName()+",   rssi:"+rssi);
//                listAdapter.addDevice(result.getDevice(), rssi);
                Log.i(TAG, "onScanResult: "+rssi + "  name: " + result.getDevice());
                rvAdapter.addDevice(new BleBean(device, rssi));
            }
        };
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewBinding = FragmentHivesBinding.inflate(inflater, container, false);

        rvHives = viewBinding.rvFragHives;
        rvHives.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        rvAdapter = new HiveListAdapter(rvHives);
        rvHives.setAdapter(rvAdapter);

        return viewBinding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();

        if (scanner==null){
            scanner=btAdapter.getBluetoothLeScanner();
        }
        scanner.startScan(null, settBuilder.build(), scanCallback);
    }

    @Override
    public void onPause() {
        super.onPause();

        scanner.stopScan(scanCallback);
    }
}