package com.example.athi.rock.utilisateur.evenement;

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
import com.example.athi.rock.utilisateur.musique.MusiquesFragment;
import com.example.athi.rock.utilisateur.passes.PassesFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class EvenementsFragment extends Fragment{
    private TabLayout eventTabLayout;
    private ViewPager eventViewPager;
    EvenementsFragment listener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    /*Association au menu secondaire de l'item Danse*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_evenements,container,false);
        eventViewPager = (ViewPager) rootView.findViewById(R.id.viewpager_content_event);
        eventTabLayout =(TabLayout) rootView.findViewById(R.id.tabsevent);
        eventTabLayout.setupWithViewPager(eventViewPager);
        setupViewPager(eventViewPager);
        return rootView;
    }
    /*Association avec les 2 fragments du fragemnt DanseFragment*/
    public void setupViewPager(ViewPager upViewPager) {
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new EvenementAVenirFragment(), "Evenements A Venir");
        adapter.addFragment(new EvenementPasseFragment(),"Evenements Passés");
        upViewPager.setAdapter(adapter);
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }
}
