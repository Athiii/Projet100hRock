package com.example.athi.rock.administrateur.Musique;

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
public class SupprimerMusiqueFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_supprimer_musique, container, false);
    }
}
