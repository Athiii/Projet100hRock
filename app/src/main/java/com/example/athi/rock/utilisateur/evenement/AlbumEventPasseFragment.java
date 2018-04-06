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
import android.widget.Toast;

import com.example.athi.rock.R;

import java.time.Instant;

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

        //On affiche les photos associés à cet évenement
        GridView albumPhotos =(GridView) view.findViewById(R.id.gridview);
        Button returnButton = (Button) view.findViewById(R.id.btn_retour);
        albumPhotos.setAdapter(new AlbumAdapter(getContext()));

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

}
