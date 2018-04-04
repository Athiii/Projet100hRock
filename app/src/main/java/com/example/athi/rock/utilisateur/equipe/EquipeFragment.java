package com.example.athi.rock.utilisateur.equipe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.athi.rock.MainActivity;
import com.example.athi.rock.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Item du menu principal
 * liste des membres
 */
public class EquipeFragment extends Fragment {


    public EquipeFragment() {
        // Required empty public constructor
    }
/*Relation avec les éléments de la vue de EquipeFragment ici seulement une listView*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {




        View view =inflater.inflate(R.layout.fragment_equipe, container, false);
        ListView listViewEquipe = (ListView) view.findViewById(R.id.id_listViewEquipe);
        MembreAdapter adapter = new MembreAdapter(getActivity(),MainActivity.affichageListMembre);
        listViewEquipe.setAdapter(adapter);
        return view;
    }


}


