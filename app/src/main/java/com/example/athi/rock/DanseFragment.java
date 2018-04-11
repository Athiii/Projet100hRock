package com.example.athi.rock;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.athi.rock.utilisateur.musique.MusiquesFragment;
import com.example.athi.rock.utilisateur.passes.PassesFragment;


/**
 * A simple {@link Fragment} subclass.
 * Ca marche?
 */
public class DanseFragment extends Fragment{
    private TabLayout danseTabLayout;
    private ViewPager danseViewPager;



    public void onAttach(Context context) {
        super.onAttach(context);
    }
    /*Association au menu secondaire de l'item Danse*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootViewDanse = inflater.inflate(R.layout.fragment_danse,container,false);
        danseViewPager = (ViewPager) rootViewDanse.findViewById(R.id.viewpager_content_danse);
        danseTabLayout =(TabLayout) rootViewDanse.findViewById(R.id.tabsdanse);
        danseTabLayout.setupWithViewPager(danseViewPager);

        setupViewPager(danseViewPager);
        return rootViewDanse;
    }


/*Association avec les 2 fragments du fragemnt DanseFragment*/
    public void setupViewPager(ViewPager upViewPager) {
        TabViewPagerAdapter adapterDanse = new TabViewPagerAdapter(getChildFragmentManager());
        adapterDanse.addFragment(new PassesFragment(),"Passes");
        adapterDanse.addFragment(new MusiquesFragment(), "Musiques");
        danseViewPager.setAdapter(adapterDanse);
    }
}
