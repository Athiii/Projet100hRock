package com.example.athi.rock.utilisateur;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.athi.rock.R;
import com.example.athi.rock.TabViewPagerAdapter;
import com.example.athi.rock.utilisateur.evenement.EvenementAVenirFragment;
import com.example.athi.rock.utilisateur.evenement.EvenementPasseFragment;
import com.example.athi.rock.utilisateur.evenement.HomeHautFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private TabLayout hometabLayout;
    private ViewPager homeViewPager;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home,container,false);
        homeViewPager = (ViewPager) rootView.findViewById(R.id.viewpager_content_home);
        hometabLayout =(TabLayout) rootView.findViewById(R.id.tabshome);
        hometabLayout.setupWithViewPager(homeViewPager);
        setupViewPager(homeViewPager);
        return rootView;
    }
    /*Association aux 2 fragments désifné par le menu secondaire (menu haut)*/
    public void setupViewPager(ViewPager upViewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new HomeHautFragment(),"Home");
        //adapter.addFragment(new EvenementAVenirFragment(),"Evenements A Venir");
        //adapter.addFragment(new EvenementPasseFragment(),"Evenements Passés");
        upViewPager.setAdapter(adapter);

    }


}
