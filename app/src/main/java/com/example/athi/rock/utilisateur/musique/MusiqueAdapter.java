package com.example.athi.rock.utilisateur.musique;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athi.rock.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Athi on 12/02/2018.
 */

public class MusiqueAdapter extends ArrayAdapter<Musique>{
    DatabaseReference musiqueBase = FirebaseDatabase.getInstance().getReference();
    List<String> keys;
    public class MusiqueViewHolder {
        public TextView nomMusique;
        public TextView artiste;
        public TextView nbLike;
    }

    public MusiqueAdapter (Context context, List<Musique> musiques, List<String> keys) {
        super(context, 0,musiques);
        this.keys=keys;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_liste_musique,parent, false);
        }

        MusiqueViewHolder viewHolder = (MusiqueViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MusiqueViewHolder();
            viewHolder.nomMusique= (TextView) convertView.findViewById(R.id.id_nom_musique);
            viewHolder.artiste = (TextView) convertView.findViewById(R.id.id_artiste);
            viewHolder.nbLike = (TextView) convertView.findViewById(R.id.id_nbLike);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Musiques> musiques
        final Musique musique= getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nomMusique.setText(musique.getNomMusique());
        viewHolder.artiste.setText(musique.getArtiste());
        viewHolder.nbLike.setText(String.valueOf(musique.getNbLike()));
        return convertView;
    }

}
