package com.example.athi.rock.utilisateur.musique;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athi.rock.R;
import java.util.List;

/**
 * Created by Athi on 12/02/2018.
 */

public class MusiqueAdapter extends ArrayAdapter<Musique> {

    public MusiqueAdapter (Context context, List<Musique> musiques) {
        super(context, 0,musiques);
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
            viewHolder.pluslike=(ImageButton) convertView.findViewById(R.id.id_plusLike);

            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Musiques> musiques
        Musique musique= getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nomMusique.setText(musique.getNomMusique());
        viewHolder.artiste.setText(musique.getArtiste());
        viewHolder.nbLike.setText(String.valueOf(musique.getNbLike()));
        viewHolder.pluslike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton imageButton= (ImageButton) view;
                Musique musique1 = getItem(position);
                int nombre = musique1.getNbLike()+1;
                Toast.makeText(getContext(), "il y a " +nombre+" likes", Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }

    public class MembreViewAdapter{
        public TextView nomMusique;
        public TextView artiste;
        public  TextView nbLike;
        public Button plusLike;
    }
}
