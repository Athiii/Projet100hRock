package com.example.athi.rock.utilisateur.equipe;

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
        View view =inflater.inflate(R.layout.fragment_equipe, container, false);
        listerMembre();
        return view;
    }


    public void listerMembre() {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        final List<Membre> affichageListMembre=new ArrayList<Membre>();
        databaseReference.child("membre").addValueEventListener(new ValueEventListener() {
            //cette méthode sera implémenté à chaque fois que l'on change la database.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //renvoie la référence de chacun des sous objet de membre.
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Membre membre1 = child.getValue(Membre.class);
                    affichageListMembre.add(membre1);
                }
                ListView listViewEquipe = (ListView) getView().findViewById(R.id.id_listViewEquipe);
                MembreAdapter adapter = new MembreAdapter(getActivity(),affichageListMembre);
                listViewEquipe.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}


