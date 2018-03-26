package com.example.athi.rock.administrateur.Evenement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.evenement.Evenement;
import com.example.athi.rock.utilisateur.musique.Musique;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Athi on 26/03/2018.
 */

public class EvenementSupprimerAdapter extends ArrayAdapter<Evenement> {

    public class EvenementSupprimerViewHolder{
        public TextView nomEvent;
        public ImageButton supprimer;
    }

    public EvenementSupprimerAdapter (Context context, List<Evenement> evenementList) {
        super(context, 0,evenementList);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_liste_evenement_supprimer,parent, false);
        }

        EvenementSupprimerViewHolder viewHolder = (EvenementSupprimerViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new EvenementSupprimerViewHolder();
            viewHolder.nomEvent= (TextView) convertView.findViewById(R.id.id_nom_evenement_supprimer);
            viewHolder.supprimer=(ImageButton) convertView.findViewById(R.id.btn_supprimer);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Musiques> musiques
        Evenement evenement= getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nomEvent.setText(evenement.getNomEvent());
        viewHolder.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageButton imageButton= (ImageButton) view;
                Evenement evenement1 = getItem(position);
                int id = evenement1.getIdEvent();
                Toast.makeText(getContext(), "supprimer l'évenement d'id: "+id, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;
    }


}
