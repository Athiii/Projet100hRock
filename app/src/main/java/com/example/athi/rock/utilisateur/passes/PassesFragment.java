package com.example.athi.rock.utilisateur.passes;

import android.content.Context;
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
 * item du menu seconaire de Danse
 * Liste des passes-> ListView
 * précisions sur les lignes -> PasseAdapter
 */
public class PassesFragment extends Fragment {
    public PassesFragment() {
        // Required empty public constructor
    }
    PassesFragment listener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    /*Association avec la vue du fragment PasseFragment ici seulement une ListView*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_passes, container, false);
        listerPasse();
        return view;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }

/*Relation avec les éléments de la vue de EquipeFragment ici seulement une listView*/


    public void listerPasse() {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        final List<Passe> affichageListPasse=new ArrayList<Passe>();
        databaseReference.child("passe").addValueEventListener(new ValueEventListener() {
            //cette méthode sera implémenté à chaque fois que l'on change la database.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //renvoie la référence de chacun des sous objet de membre.
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Passe passe1 = child.getValue(Passe.class);
                    affichageListPasse.add(passe1);
                }
                ListView listViewEquipe = (ListView) getView().findViewById(R.id.id_listViewPasse);
                PasseAdapter adapter = new PasseAdapter(getActivity(),affichageListPasse);
                listViewEquipe.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
