package com.example.athi.rock.utilisateur.equipe;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.athi.rock.R;
import com.example.athi.rock.administrateur.AdminCodeActivity;
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

//Relation avec les éléments de la vue de EquipeFragment ici seulement une listView
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_equipe, container, false);

        Button btnVersMDP = (Button) view.findViewById(R.id.btn_vers_mdp);
        btnVersMDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), AdminCodeActivity.class);
                startActivity(intent);
            }
        });
        listerMembre();
        return view;
    }

// fonction qui permet l'affichage de la liste de membre
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
//                permet d'addapter la list obtenue au visuel de sorti
                MembreAdapter adapter = new MembreAdapter(getActivity(),affichageListMembre);
                listViewEquipe.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}


