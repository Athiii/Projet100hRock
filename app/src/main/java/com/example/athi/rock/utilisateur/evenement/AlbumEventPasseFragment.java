package com.example.athi.rock.utilisateur.evenement;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.equipe.Membre;
import com.example.athi.rock.utilisateur.equipe.MembreAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumEventPasseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_album_event_passe, container, false);

        //On Récupère le nom de l'évenement sur lequel à cliqué l'utilisateur précédement
        Bundle bundle = getArguments();
        String nomEvent=bundle.getString("NomEvent");
        Toast.makeText(getContext(),"vous avez cliqué sur: "+nomEvent,Toast.LENGTH_SHORT).show();

        listerPhotos(nomEvent);

        //On affiche les photos associés à cet évenement
        GridView albumPhotos =(GridView) view.findViewById(R.id.gridview);
        Button returnButton = (Button) view.findViewById(R.id.btn_retour);
//        albumPhotos.setAdapter(new AlbumAdapter(getContext()));

        //au clique sur l'image on affiche la photo désirée
        albumPhotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),""+i,Toast.LENGTH_SHORT).show();
            }
        });
        //Bouton retour vers la liste des événèments passés
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"retour à la liste",Toast.LENGTH_SHORT).show();
                FragmentManager fm= getFragmentManager();
                fm.popBackStack();
            }
        });
        return view;
    }
    public AlbumEventPasseFragment() {
        // Required empty public constructor
    }
    /*Relation avec les éléments de la vue de EquipeFragment ici seulement une listView*/



    public void listerPhotos(final String nomEvenement) {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        final List<Photo> affichageListPhotos=new ArrayList<Photo>();
        databaseReference.child("Photos").addValueEventListener(new ValueEventListener() {
            //cette méthode sera implémenté à chaque fois que l'on change la database.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //renvoie la référence de chacun des sous objet de membre.
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {

                    Photo photo1 = child.getValue(Photo.class);
                    if(photo1.getType()==nomEvenement)
                    affichageListPhotos.add(photo1);
                }
//                ListView listViewEquipe = (ListView) getView().findViewById(R.id.id_listViewEquipe);
                AlbumAdapter adapter = new AlbumAdapter(getContext(),affichageListPhotos);
//                listViewEquipe.setAdapter(adapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
