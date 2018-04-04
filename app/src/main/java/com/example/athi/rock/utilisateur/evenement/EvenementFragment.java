package com.example.athi.rock.utilisateur.evenement;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.athi.rock.R;
import com.example.athi.rock.TabViewPagerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Item du menu secondaire de Home
 * appel d'un sous-menu -> evenement à venir et passés
 */
public class EvenementFragment extends Fragment {
    private TabLayout evenementTabLayout;
    private ViewPager evenementViewPager;

    public EvenementFragment() {
        // Required empty public constructor
    }
    /*Association au layout du fragment EvenementFragment
    * Asoociation au sous-menu des Evenements */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_evenement,container,false);

        evenementViewPager = (ViewPager) rootView.findViewById(R.id.viewpager_content_evenement);
        evenementTabLayout =(TabLayout) rootView.findViewById(R.id.tabs_evenement);
        evenementTabLayout.setupWithViewPager(evenementViewPager);
        setupViewPager(evenementViewPager);

        return rootView;
    }
/*Association aux 2 Fragements du sous menu */
    public void setupViewPager(ViewPager upViewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new EvenementAVenirFragment(),"A Venir");
        adapter.addFragment(new EvenementPasseFragment(),"Passé");
        evenementViewPager.setAdapter(adapter);
    }

}
