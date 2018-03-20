package com.example.athi.rock;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Athi on 07/02/2018.
 * permet de gérer la liste des fragments utilent pour les sous-menu
 */

public class TabViewPagerAdapter extends FragmentPagerAdapter{
    private final List<Fragment> fragmentList = new ArrayList<>();
    private final List<String> fragmentTitleList = new ArrayList<>();

    public TabViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment, String title){
        fragmentList.add(fragment);
        fragmentTitleList.add(title);
    }
    @Override
    public CharSequence getPageTitle(int position){
        return fragmentTitleList.get(position);
    }
}
