package com.example.athi.rock.utilisateur.evenement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.athi.rock.R;
import com.example.athi.rock.administrateur.AdministrateurActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * sous-item de l'item evenement
 * liste des évenements à venir -> listView
 * précision sur les composants de la liste -> EvenementAVenirAdapter
 */
public class EvenementAVenirFragment extends Fragment {

    public EvenementAVenirFragment() {
        // Required empty public constructor
    }
    /*Association aux éléments (layout) de la vue de EvenementAVenirFragment*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evenement_avenir, container, false);
        listerEvenement();
        return view;
    }
    public void listerEvenement(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("evenement").addValueEventListener(new ValueEventListener() {
            //cette méthode sera implémentée à chaque fois que l'on change la database.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();
                final List<Evenement> affichageListEvenement=new ArrayList<Evenement>();
                ListView listViewEvenement =(ListView)getView().findViewById(R.id.id_listViewEvenement_AVenir);
                EvenementAVenirAdapter adapter = new EvenementAVenirAdapter(getActivity(), affichageListEvenement);
                adapter.clear();
                Date today=Calendar.getInstance().getTime();
                //renvoie la référence de chacun des sous objets d'evenement.
                for (DataSnapshot child :children) {
                    Evenement evenement1 = child.getValue(Evenement.class);
                    if(evenement1.getDateEvent().compareTo(today)>=0){
                        affichageListEvenement.add(evenement1);
                    }
                }

                adapter = new EvenementAVenirAdapter(getActivity(), affichageListEvenement);
                listViewEvenement.setAdapter(adapter);


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


}
