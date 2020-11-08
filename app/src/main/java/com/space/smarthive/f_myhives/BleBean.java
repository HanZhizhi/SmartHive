package com.space.smarthive.f_myhives;

import android.bluetooth.BluetoothDevice;

import java.util.Objects;

/**
 * @Author: Space
 * @Date: 2020/11/1 21:14
 */


public class BleBean {
    protected BluetoothDevice device;
    protected int rssi;

    protected BleBean(BluetoothDevice d, int r){
        device = d;
        rssi = r;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BleBean bleBean = (BleBean) o;
        return Objects.equals(device, bleBean.device);
    }

    @Override
    public int hashCode() {
        return Objects.hash(device, rssi);
    }
}
