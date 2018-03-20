package com.example.athi.rock;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    private ViewPager homeViewPager;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        homeViewPager = (ViewPager) rootView.findViewById(R.id.viewpager_content);
        tabLayout =(TabLayout) rootView.findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(homeViewPager);

        setupViewPager(homeViewPager);
        return rootView;
    }
    /*Association aux 2 fragments désifné par le menu secondaire (menu haut)*/
    public void setupViewPager(ViewPager upViewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new HomeHautFragment(),"Home");
        adapter.addFragment(new EvenementHautFragment(), "Evenements");
        homeViewPager.setAdapter(adapter);

    }
}
