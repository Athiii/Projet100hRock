package com.example.athi.rock.administrateur.Evenement;

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
public class EvenementAdminFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager eventViewPager;

    public EvenementAdminFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_evenement_admin, container, false);
        eventViewPager = (ViewPager) view.findViewById(R.id.viewpager_content_evenement_admin);
        tabLayout =(TabLayout) view.findViewById(R.id.tabs_evenement_admin);
        tabLayout.setupWithViewPager(eventViewPager);
        setupViewPager(eventViewPager);
        return view;
    }
    /*Association aux 2 fragments désifné par le menu secondaire (menu haut)*/
    public void setupViewPager(ViewPager upViewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new AjouterEvenementFragment(),"+");
        adapter.addFragment(new SupprimerEvenementFragment(), "-");
        eventViewPager.setAdapter(adapter);

    }
}
