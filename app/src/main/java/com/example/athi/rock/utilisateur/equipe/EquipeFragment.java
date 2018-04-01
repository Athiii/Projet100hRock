package com.example.athi.rock.utilisateur.equipe;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

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
        final List<Membre> membreList = new ArrayList<Membre>();


       /* DatabaseReference dataMembre = FirebaseDatabase.getInstance().getReference();
        dataMembre.child("membre").addValueEventListener(new ValueEventListener() {
            //cette méthode sera implémenté à chaque fois que l'on change la database.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //renvoie la référence de chacun des sous objet de membre.
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child:children) {
                    Membre membre1 = child.getValue(Membre.class);
                    membreList.add(membre1);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/



        View view =inflater.inflate(R.layout.fragment_equipe, container, false);
        ListView listViewEquipe = (ListView) view.findViewById(R.id.id_listViewEquipe);
        MembreAdapter adapter = new MembreAdapter(getActivity(),membreList);
        listViewEquipe.setAdapter(adapter);
        return view;
    }
/*Création en "dur" de la liste des membres -> plus tard dans la base de donnée*/
    /*private List<Membre> genererMembres() {
        List<Membre> membres = new ArrayList<Membre>();
        membres.add(new Membre("President", "la desciption du pres", "Gorgeon", "Clément", Color.GRAY));
        membres.add(new Membre("President", "la desciption du pres blbalablablablablablabalablabl abalbalablablabalbalbalablab blabalblabalbalbalablabalba balkbalablbal babalb lablabalablablablab",
                "Gorgeon", "Clément", Color.GRAY));
        membres.add(new Membre("President", "la desciption du pres", "Gorgeon", "Clément", Color.GRAY));
        membres.add(new Membre("President", "la desciption du pres", "Gorgeon", "Clément", Color.GRAY));
        membres.add(new Membre("President", "la desciption du pres", "Gorgeon", "Clément", Color.GRAY));
        membres.add(new Membre("President", "la desciption du pres", "Gorgeon", "Clément", Color.GRAY));
        membres.add(new Membre("President", "la desciption du pres", "Gorgeon", "Clément", Color.GRAY));
        membres.add(new Membre("President", "la desciption du pres", "Gorgeon", "Clément", Color.GRAY));

        return membres;
    }*/
}
