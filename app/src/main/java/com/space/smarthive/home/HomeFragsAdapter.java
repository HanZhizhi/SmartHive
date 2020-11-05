package com.space.smarthive.home;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Space
 * @Date: 2020/10/30 16:38
 */


public class HomeFragsAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments ;

    public HomeFragsAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    public void setData(List<Fragment> data){
        if (fragments == null){
            fragments = new ArrayList<>();
        }
        fragments.addAll(data);
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (fragments != null && fragments.size() > 0){
            return fragments.get(position);
        }
        return null;
    }

    @Override
    public int getCount() {
        if (fragments != null && fragments.size() > 0){
            return fragments.size();
        }
        return 0;
    }

}
