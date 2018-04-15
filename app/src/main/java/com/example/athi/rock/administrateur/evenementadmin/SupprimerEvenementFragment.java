package com.example.athi.rock.administrateur.evenementadmin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.athi.rock.utilisateur.MainActivity;
import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.evenement.Evenement;
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
public class SupprimerEvenementFragment extends Fragment {
    public SupprimerEvenementFragment(){
        //Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_supprimer_evenement, container, false);
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
        listerEvenementASupprimer();
        return view;
    }
    public void listerEvenementASupprimer(){
//        on récupère la référence de la base de donnée
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
//      on cherche dans la base de donné les "enfants" de evenement sur lesquel on place un listener
        databaseReference.child("evenement").addValueEventListener(new ValueEventListener() {
            //cette méthode sera implémentée à chaque fois que l'on change la database.
//            DataSnaphsot permet de parcourir tous les éléments de l'enfant
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //listes des evènements et de leurs clés dans firebase afin de pouvoir les supprimer
                List<Evenement> listEvenement=new ArrayList<Evenement>();
                List<String> keys = new ArrayList<String>();
                ListView listViewEvenements =(ListView) getView().findViewById(R.id.id_listViewEvenement_supprimer);

                //on récupère la largeur et la hauteur du téléphone pour adapter la pop up au tel
                DisplayMetrics dm = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
                int width=dm.widthPixels;
                int height = dm.heightPixels;

                EvenementSupprimerAdapter adapter = new EvenementSupprimerAdapter(getActivity(),listEvenement,keys,width,height);
                adapter.clear();
                //renvoie la référence de chacun des sous objets d'evenement.
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    listEvenement.add(child.getValue(Evenement.class));
                    keys.add(child.getKey());
                }
                adapter = new EvenementSupprimerAdapter(getActivity(),listEvenement,keys,width,height);
                listViewEvenements.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


}
