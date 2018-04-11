package com.example.athi.rock.administrateur.Membre;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.athi.rock.R;
import com.example.athi.rock.TabViewPagerAdapter;
import com.example.athi.rock.utilisateur.equipe.Membre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.

 */

public class MembreAdminFragment extends Fragment {
    public MembreAdminFragment(){

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

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
