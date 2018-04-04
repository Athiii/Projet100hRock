package com.example.athi.rock.administrateur.Musique;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.athi.rock.R;
import com.example.athi.rock.TabViewPagerAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusiqueAdminFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager musiqueViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_musique_admin, container, false);
        musiqueViewPager = (ViewPager) view.findViewById(R.id.viewpager_content_musique_admin);
        tabLayout =(TabLayout) view.findViewById(R.id.tabs_musique_admin);
        tabLayout.setupWithViewPager(musiqueViewPager);
        setupViewPager(musiqueViewPager);
        return view;
    }

    public void setupViewPager(ViewPager upViewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AjouterMusiqueFragment(),"+");
        adapter.addFragment(new SupprimerMusiqueFragment(), "-");
        musiqueViewPager.setAdapter(adapter);
    }
}
