package com.space.smarthive.home;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import com.gyf.immersionbar.ImmersionBar;
import com.space.smarthive.R;
import com.space.smarthive.databinding.ActivityHomeBinding;
import com.space.smarthive.info.InfoFragment;
import com.space.smarthive.myhives.HivesFragment;
import com.space.smarthive.user.UserFragment;
import com.space.smarthive.utils.ColorUtil;

import java.util.ArrayList;
import java.util.List;

import me.majiajie.pagerbottomtabstrip.NavigationController;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private static final int OPEN_BLE = 321;
    
    
    private ActivityHomeBinding viewBinding;
    private NavigationController bottomStrip;

    private HomeFragsAdapter pagerAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_home);
        viewBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(viewBinding.getRoot());

        ImmersionBar.with(this)
                .statusBarColor(R.color.darkWhite)
                .navigationBarColor(R.color.darkWhite)
                .fitsSystemWindows(true)
                .autoDarkModeEnable(true)
                .init();

        initViews();

        initFragments();
    }

    private void initViews(){

        bottomStrip = viewBinding.bottomStripMain.material()
                .addItem(R.mipmap.main_hives,
                        "蜂箱",
                        ColorUtil.getColor(this, R.color.main_bottom_check_color))
                .addItem(R.mipmap.main_info,
                        "资讯",
                        ColorUtil.getColor(this, R.color.main_bottom_check_color))
                .addItem(R.mipmap.main_user,
                        "我的",
                        ColorUtil.getColor(this, R.color.main_bottom_check_color))
                .setDefaultColor(
                        ColorUtil.getColor(this, R.color.main_bottom_default_color))
                .enableAnimateLayoutChanges()
                .build();


        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                }
            }
        };


        pagerAdapter = new HomeFragsAdapter(getSupportFragmentManager(), HomeFragsAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        viewBinding.pagerMain.setAdapter(pagerAdapter);

        bottomStrip.setupWithViewPager(viewBinding.pagerMain);
    }

    private void initFragments(){
        List<Fragment> frags = new ArrayList<>();
        Fragment f1 = new HivesFragment();
        Fragment f2 = new InfoFragment();
        Fragment f3 = new UserFragment();
        frags.add(f1);
        frags.add(f2);
        frags.add(f3);
        pagerAdapter.setData(frags);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case OPEN_BLE:
                Log.i(TAG, "onActivityResult: "+resultCode);
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(Intent.ACTION_MAIN);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addCategory(Intent.CATEGORY_HOME);
            startActivity(i);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}