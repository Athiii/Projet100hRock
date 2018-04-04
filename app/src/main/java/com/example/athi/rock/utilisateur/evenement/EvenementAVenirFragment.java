package com.example.athi.rock.utilisateur.evenement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.athi.rock.MainActivity;
import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.equipe.Membre;
import com.example.athi.rock.utilisateur.evenement.Evenement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Timestamp;

/**
 * A simple {@link Fragment} subclass.
 * sous-item de l'item evenement
 * liste des évenements à venir -> listView
 * précision sur les composants de la liste -> EvenementAVenirAdapter
 */
public class EvenementAVenirFragment extends Fragment {

    private DatabaseReference dataMembre;

    public EvenementAVenirFragment() {
        // Required empty public constructor
    }
/*Association aux éléments (layout) de la vue de EvenementAVenirFragment*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


// Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_evenement_avenir, container, false);
        ListView listViewEvenement =(ListView)view.findViewById(R.id.id_listViewEvenement_AVenir);
        EvenementAVenirAdapter adapter = new EvenementAVenirAdapter(getActivity(), MainActivity.affichageListEvenement);
        listViewEvenement.setAdapter(adapter);
        return view;
    }
}
