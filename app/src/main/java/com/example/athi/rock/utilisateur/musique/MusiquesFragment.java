package com.example.athi.rock.utilisateur.musique;


import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.equipe.Membre;
import com.example.athi.rock.utilisateur.evenement.Photo;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;


/*
 * A simple {@link Fragment} subclass.
 * Item du menu secondaire de Danse
 * liste des musiques -> ListView
 * elements de la liste -> MusiqueAdapter
 */
public class MusiquesFragment extends Fragment {

    DatabaseReference musiqueBase = FirebaseDatabase.getInstance().getReference();

    public MusiquesFragment() {
        // Required empty public constructor
    }
/*Association à la vue de MusiquesFragment ici seulement la listeView*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_musiques, container, false);
        listerMusique();
        ImageButton btnAjouterMusique = (ImageButton) view.findViewById(R.id.id_plusMusique);
        btnAjouterMusique.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View view) {
                EditText nomMusique = (EditText) getActivity().findViewById(R.id.id_nom_musique_ajoutee);
                String nomMusiqueAjoutee = nomMusique.getText().toString();

                EditText nomArtiste = (EditText) getActivity().findViewById(R.id.id_nom_artiste_ajoute);
                String nomArtisteAjoute = nomArtiste.getText().toString();
                

                if (nomMusiqueAjoutee == null ||nomArtisteAjoute ==null) {
                    Toast.makeText(getContext(),"Merci de donner le titre de la musique et son Artsite",Toast.LENGTH_SHORT).show();
                } else{
                    uploadFile(nomMusiqueAjoutee,nomArtisteAjoute);
                    //on créé un nouvel objet que l'on ajoute à fire base.
                    Toast.makeText(getContext(),nomMusiqueAjoutee+" a est ajoutée",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    private void listerMusique() {
        musiqueBase.child("musique").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                final List<Musique> listeMusique= new ArrayList<Musique>();
                final List<String> listeKeyMusique=new ArrayList<String>();
                ListView listViewMusique = (ListView) getView().findViewById(R.id.id_listViewMusique);
                MusiqueAdapter adapter = new MusiqueAdapter(getActivity(),listeMusique, listeKeyMusique);
                adapter.clear();
                for (DataSnapshot child : children) {
                    Musique musique1 = child.getValue(Musique.class);
                    String key = child.getKey();
                    listeMusique.add(musique1);
                    listeKeyMusique.add(key);
                }

                adapter= new MusiqueAdapter(getActivity(),listeMusique,listeKeyMusique);
                listViewMusique.setAdapter(adapter);
                listViewMusique.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Musique musique1 = listeMusique.get(i);
                        int nombre = musique1.getNbLike()+1;
                        musiqueBase.child("musique").child(listeKeyMusique.get(i)).child("nbLike").setValue(nombre);
                        Toast.makeText(getContext(), "il y a " +nombre+" likes", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });
    }


    private void uploadFile(String nomMusique, String nomArtiste){
        Musique nouvelleMusique = new Musique(nomMusique,nomArtiste,0);
        musiqueBase.child("musique").push().setValue(nouvelleMusique);
    }

}
