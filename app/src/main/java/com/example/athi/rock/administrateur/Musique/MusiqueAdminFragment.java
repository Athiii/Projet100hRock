package com.example.athi.rock.administrateur.Musique;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.athi.rock.MainActivity;
import com.example.athi.rock.R;
import com.example.athi.rock.TabViewPagerAdapter;
import com.example.athi.rock.utilisateur.evenement.Evenement;
import com.example.athi.rock.utilisateur.musique.Musique;
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
public class MusiqueAdminFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_musique_admin, container, false);
        listerMusiqueASupprimer();
        //Bouton retour vers l'activité Utilisateur (HomeHautFragment)
        Button btnRetour = (Button) view.findViewById(R.id.btn_retour_utilisateur);
        btnRetour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"retour à la maison",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void listerMusiqueASupprimer() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("musique").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //listes de musiques et de leur clés dans firebase permettant de les retrouver afin de les supprimer
                List<Musique> listMusiques = new ArrayList<Musique>();
                List<String> keys = new ArrayList<String>();
                ListView listViewMusique = (ListView) getView().findViewById(R.id.id_listViewMusique_supprimer);
                MusiqueSupprimerAdapter adapter= new MusiqueSupprimerAdapter(getActivity(),listMusiques,keys);
                adapter.clear();
                //Renvoie la référence de chacun des sous objets de musique
                for (DataSnapshot child: dataSnapshot.getChildren()){
                    listMusiques.add(child.getValue(Musique.class));
                    keys.add(child.getKey());
                }
                adapter = new MusiqueSupprimerAdapter(getActivity(),listMusiques,keys);
                listViewMusique.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
