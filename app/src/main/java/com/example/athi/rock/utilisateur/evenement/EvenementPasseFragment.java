package com.example.athi.rock.utilisateur.evenement;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.athi.rock.R;
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
 * sous-item de l'item evenement du menu principal
 * liste des évenements passés-> listView
 * précision des éléments de la liste-> EvenementPasseAdapter
 */
public class EvenementPasseFragment extends Fragment {
    public EvenementPasseFragment() {
        // Required empty public constructor
    }
    EvenementPasseFragment listener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
    /*Association aux éléments (layout) de la vue de EvenementPasseFragment*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_evenement_passe, container, false);
        listerEvenement();
        return view;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }
    public void listerEvenement(){
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        final List<Evenement> affichageListEvenement=new ArrayList<Evenement>();
        databaseReference.child("evenement").addValueEventListener(new ValueEventListener() {
            //cette méthode sera implémentée à chaque fois que l'on change la database.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //renvoie la référence de chacun des sous objet de membre.
                Iterable<DataSnapshot> children=dataSnapshot.getChildren();
                ListView listViewEvenement =(ListView)getView().findViewById(R.id.id_listViewEvenement_Passe);
                //renvoie la référence de chacun des sous objets d'evenement.
                EvenementPasseAdapter adapter = new EvenementPasseAdapter(getActivity(),affichageListEvenement);
                adapter.clear();

                Date today=Calendar.getInstance().getTime();
                for (DataSnapshot child : children) {
                    Evenement evenement1 = child.getValue(Evenement.class);

                    //Vérifions si les évènements sont antérieurs à aujourd'hui
                    if (evenement1.getDateEvent().compareTo(today)<0){
                        affichageListEvenement.add(evenement1);
                    }
                }
                //renvoie la référence de chacun des sous objets d'evenement.
                adapter = new EvenementPasseAdapter(getActivity(),affichageListEvenement);
                listViewEvenement.setAdapter(adapter);

                //Au clique sur l'item on affiche l'album photo associé à l'évènement
                //=> transition vers AlbumEventPasseFragment
                listViewEvenement.setOnItemClickListener(new OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //Envoi du nom de l'évenement cliqué vers le gridView associé
                        String data= affichageListEvenement.get(i).getNomEvent();
                        Bundle bundle = new Bundle();
                        bundle.putString("NomEvent",data);

                        //Transition vers AlbumEventPasseFragment
                        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction= fm.beginTransaction();

                        AlbumEventPasseFragment albumEventPasseFragment = new AlbumEventPasseFragment();
                        albumEventPasseFragment.setArguments(bundle);

                        fragmentTransaction.replace(android.R.id.content,albumEventPasseFragment);
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
