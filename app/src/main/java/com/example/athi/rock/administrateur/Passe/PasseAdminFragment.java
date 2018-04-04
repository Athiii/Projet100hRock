package com.example.athi.rock.administrateur.Passe;

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
public class PasseAdminFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager passeViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_passe_admin, container, false);
        passeViewPager = (ViewPager) view.findViewById(R.id.viewpager_content_passe_admin);
        tabLayout =(TabLayout) view.findViewById(R.id.tabs_passe_admin);
        tabLayout.setupWithViewPager(passeViewPager);
        setupViewPager(passeViewPager);
        return view;
    }

    public void setupViewPager(ViewPager upViewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AjouterPasseFragment(),"+");
        adapter.addFragment(new SupprimerPasseFragment(), "-");
        passeViewPager.setAdapter(adapter);
    }
}
