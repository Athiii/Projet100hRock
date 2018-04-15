package com.example.athi.rock.administrateur.evenementadmin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
public class EvenementsListeAjouterPhotosFragment extends Fragment {
    public EvenementsListeAjouterPhotosFragment(){
        //Required empty public constructor
    }

    //    Listener du fragment afin de pouvoir gérer sa fermeture et son ouverture
    EvenementsListeAjouterPhotosFragment listener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_evenements_liste_ajouter_photos, container, false);
        listerEvenementsPhotoAAjouter();
        //Bouton retour vers l'activité utilisateur (HomeHautFragment)
        Button returnButton = (Button) view.findViewById(R.id.btn_retour_utilisateur);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "retour à la maison", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
   @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }
    public void listerEvenementsPhotoAAjouter(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("evenement").addValueEventListener(new ValueEventListener() {
            //cette méthode sera implémentée à chaque fois que l'on change la database.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                final List<Evenement> listeDesEvenements=new ArrayList<Evenement>();
                ListView listViewEvenements =(ListView) getView().findViewById(R.id.id_listViewEvenement_Photos_Ajoutees);
                EvenementsListePhotosAjouterAdapter adapter = new EvenementsListePhotosAjouterAdapter(getActivity(),listeDesEvenements);
                adapter.clear();
                //renvoie la référence de chacun des sous objets d'evenement.
                for (DataSnapshot child : children) {
                    Evenement evenement =child.getValue(Evenement.class);
                    listeDesEvenements.add(evenement);
                }
                adapter = new EvenementsListePhotosAjouterAdapter(getActivity(),listeDesEvenements);
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
