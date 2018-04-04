package com.example.athi.rock.administrateur.Passe;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.athi.rock.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupprimerPasseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_supprimer_passe, container, false);
        return view;
    }
}
