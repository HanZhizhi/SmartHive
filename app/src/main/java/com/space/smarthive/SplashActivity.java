package com.space.smarthive;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.space.smarthive.home.HomeActivity;

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    private static final int REQUEST_LOCATION_PERMISSON = 2345;
    private static final int OPEN_BLUETOOTH = 3456;
    private static final int OPEN_LOCATION = 4567;
    private BluetoothAdapter btAdapter;
    private BluetoothManager btManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        btManager= (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        btAdapter=btManager.getAdapter();


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION_PERMISSON);
        }

        openBlueTooth();
    }

    private void openBlueTooth(){
        if (btAdapter==null || !btAdapter.isEnabled()){
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, OPEN_BLUETOOTH);
        }else {
            openLoacation();
        }
    }

    private void openLoacation(){
        if (! isLocationOPen()){
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent, OPEN_LOCATION);
        }else {
            gotoHome();
        }
    }

    private void gotoHome(){
        new Handler().postDelayed(() -> {
            Intent homeIntent = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(homeIntent);

            finish();
        }, 400);
    }

    private boolean isLocationOPen() {
        LocationManager locationManager
                = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps || network) {
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case OPEN_BLUETOOTH:
                switch (resultCode){
                    case 0:
                        // 未打开蓝牙
                        Toast.makeText(this, "未打开蓝牙，正在退出！", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case -1:
                        // 蓝牙打开
                        openLoacation();
                        break;
                }
                break;
            case OPEN_LOCATION:
                Log.i(TAG, "onActivityResult: " + resultCode);
                switch (resultCode){
                    case -1:
                        Toast.makeText(this, "未打开定位，正在退出！", Toast.LENGTH_SHORT).show();
                        finish();
                        break;
                    case 0:
                        gotoHome();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_LOCATION_PERMISSON && grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "位置权限申请成功！", Toast.LENGTH_SHORT).show();
            gotoHome();
        }
    }
}