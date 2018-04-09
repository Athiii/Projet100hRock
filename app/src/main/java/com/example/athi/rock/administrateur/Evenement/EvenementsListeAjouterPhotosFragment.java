package com.example.athi.rock.administrateur.Evenement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

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
public class EvenementsListeAjouterPhotosFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_evenements_liste_ajouter_photos, container, false);
        listerEvenementsPhotoAAjouter();

        return view;
    }
    public void listerEvenementsPhotoAAjouter(){
        final List<Evenement> listeDesEvenements=new ArrayList<Evenement>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("evenement").addValueEventListener(new ValueEventListener() {
            //cette méthode sera implémentée à chaque fois que l'on change la database.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //renvoie la référence de chacun des sous objets d'evenement.
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Evenement evenement1 = child.getValue(Evenement.class);
                    listeDesEvenements.add(evenement1);
                }
                ListView listViewEvenements =(ListView) getView().findViewById(R.id.id_listViewEvenement_Photos_Ajoutees);
                EvenementsListePhotosAjouterAdapter adapter = new EvenementsListePhotosAjouterAdapter(getActivity(),listeDesEvenements);
                listViewEvenements.setAdapter(adapter);

                //Au clique sur l'item on affiche le formulaire pour ajouter une photo à cette évènement
                //=> transition vers AjouterPhotoEvenementFragment
                listViewEvenements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                        //Envoi du nom de l'évenement cliqué vers AjouterPhotoEvenementFragment
                        String data= listeDesEvenements.get(i).getNomEvent();
                        Bundle bundle = new Bundle();
                        bundle.putString("NomEvent",data);

                        //Transition vers AjouterPhotoEvenementFragment
                        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction= fm.beginTransaction();

                        AjouterPhotoEvenementFragment ajouterPhotoEvenementFragment = new AjouterPhotoEvenementFragment();
                        ajouterPhotoEvenementFragment.setArguments(bundle);

                        fragmentTransaction.replace(android.R.id.content,ajouterPhotoEvenementFragment);
                        fragmentTransaction.addToBackStack("tag");
                        fragmentTransaction.commit();
                    }
                });

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
