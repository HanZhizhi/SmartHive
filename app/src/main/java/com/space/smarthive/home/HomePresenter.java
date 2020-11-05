package com.space.smarthive.home;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;


/**
 * @Author: Space
 * @Date: 2020/10/30 10:16
 */


public class HomePresenter implements HomeContract.Presenter{
    private Context context;

    private BluetoothAdapter btAdapter;
    private BluetoothManager btManager;

    public static boolean IS_BLUETOOTH_OPEN = false;

    public HomePresenter(Context ctx){
        context = ctx;
    }

    public void initBLE(){
        btManager= (BluetoothManager) context.getSystemService(Context.BLUETOOTH_SERVICE);
        btAdapter=btManager.getAdapter();
    }

    public void openBLE(){
    }

    @Override
    public void start() {

    }
}
