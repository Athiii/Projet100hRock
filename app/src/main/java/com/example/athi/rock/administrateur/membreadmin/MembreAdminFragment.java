package com.example.athi.rock.administrateur.membreadmin;

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

public class MembreAdminFragment extends Fragment {
    public MembreAdminFragment(){
        // Required empty public constructor
    }

    private TabLayout tabLayout;
    private ViewPager membreViewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_membre_admin, container, false);
        membreViewPager = (ViewPager) view.findViewById(R.id.viewpager_content_membre_admin);
        tabLayout =(TabLayout) view.findViewById(R.id.tabs_membre_admin);
        tabLayout.setupWithViewPager(membreViewPager);
        setupViewPager(membreViewPager);
        return view;
    }

    public void setupViewPager(ViewPager upViewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AjouterMembreFragment(),"+");
        adapter.addFragment(new SupprimerMembreFragment(), "-");
        membreViewPager.setAdapter(adapter);
    }

}
