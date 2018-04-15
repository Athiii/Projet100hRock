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
import android.widget.ArrayAdapter;
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
    GridView albumPhotos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_album_event_passe, container, false);

        //On Récupère le nom de l'évenement sur lequel à cliqué l'utilisateur précédement
        Bundle bundle = getArguments();
        String nomEvent=bundle.getString("NomEvent");

        //On affiche les photos associés à cet évenement
        listerPhotos(nomEvent);

        //Bouton retour vers la liste des événèments passés
        Button returnButton = (Button) view.findViewById(R.id.btn_retour);
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


//  fonction qui permet de récupérer les changement fait sur une photo
    public void listerPhotos(final String nomEvenement) {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        final List<Photo> affichageListPhotos=new ArrayList<Photo>();
        databaseReference.child("Photos").addValueEventListener(new ValueEventListener() {
            //cette méthode sera implémenté à chaque fois que l'on change la database.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> keys=new ArrayList<String>();
                albumPhotos=(GridView) getView().findViewById(R.id.gridview);
                //renvoie la référence de chacun des sous objet de membre.
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();

                for (DataSnapshot child : children) {
                    Photo photo1 = child.getValue(Photo.class);
                    String type=photo1.getType().toString();
                    if(nomEvenement.equals(type)){
                        affichageListPhotos.add(photo1);
                        keys.add(photo1.getUrlImage());
                    }

                }

                AlbumAdapter adapter = new AlbumAdapter(getContext(),affichageListPhotos);
                albumPhotos.setAdapter(adapter);
                albumPhotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        //Envoi du nom de l'évenement cliqué vers le gridView associé
                        String data= keys.get(i);
                        Bundle bundle = new Bundle();
                        bundle.putString("UrlImage",data);

                        //Transition vers AlbumEventPasseFragment
                        android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                        android.support.v4.app.FragmentTransaction fragmentTransaction= fm.beginTransaction();

                        ImageFragment imageFragment = new ImageFragment();
                        imageFragment.setArguments(bundle);

                        fragmentTransaction.replace(android.R.id.content,imageFragment);
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
    @Override
    public void onDetach() {
        super.onDetach();
    }

}
