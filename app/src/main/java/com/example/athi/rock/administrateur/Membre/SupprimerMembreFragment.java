package com.example.athi.rock.administrateur.Membre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.athi.rock.MainActivity;
import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.equipe.Membre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupprimerMembreFragment extends Fragment {
    public SupprimerMembreFragment(){
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_supprimer_membre, container, false);
        listerMembreASupprimer();
        //Bouton retour vers l'activité utilisateur (HomeHautFragment)
        Button returnButton = (Button) view.findViewById(R.id.btn_retour_utilisateur);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"retour à la maison",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void listerMembreASupprimer() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("membre").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Membre> listMembres = new ArrayList<Membre>();
                List<String> keys = new ArrayList<String>();
                ListView listViewMembre = (ListView) getView().findViewById(R.id.id_listViewMembre_supprimer);
                SupprimerMembreAdapter adapter = new SupprimerMembreAdapter(getActivity(),listMembres,keys);
                adapter.clear();
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    listMembres.add(child.getValue(Membre.class));
                    keys.add(child.getKey());
                }
                adapter = new SupprimerMembreAdapter(getActivity(),listMembres,keys);
                listViewMembre.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
