package com.example.athi.rock;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.athi.rock.administrateur.AdminCodeActivity;


/**
 * A simple {@link Fragment} subclass.

 */
public class HomeHautFragment extends Fragment {

    public HomeHautFragment() {
        // Required empty public constructor
    }

    /*Association au layout du fragement HomeHautFragment*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_haut, container, false);
        Button btnVersMDP = (Button) view.findViewById(R.id.btn_vers_mdp);
        btnVersMDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "GO", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), AdminCodeActivity.class);
                startActivity(intent);
            }
        });
        return view ;
    }

}
