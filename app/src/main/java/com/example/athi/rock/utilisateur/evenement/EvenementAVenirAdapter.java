package com.example.athi.rock.utilisateur.evenement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.athi.rock.R;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Athi on 21/02/2018.
 * Gerer les éléments des lignes de la liste des événements a Venir
 */

public class EvenementAVenirAdapter extends ArrayAdapter<Evenement> {

    public EvenementAVenirAdapter(Context context, List<Evenement> evenements) {super(context, 0,evenements);}
    //Abréviation des mois (tableau)-> affichage dans la vue
    public String mois(int nb){
        String[] moisAbreviations={"JAN","FEV","MARS","AVR","MAI","JUIN","JUIL","AOUT","SEPT","OCT","NOV","DEC"};
        return moisAbreviations[nb-1];
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_liste_evenement_avenir,parent,false);
        }

        EvenementAVenirViewHolder viewHolder= (EvenementAVenirViewHolder) convertView.getTag();
        if (viewHolder==null){
            viewHolder=new EvenementAVenirViewHolder();
            viewHolder.nomEvenement= (TextView) convertView.findViewById(R.id.id_nomEvenement_AVenir);
            viewHolder.jourEvent=(TextView) convertView.findViewById(R.id.id_jour);
            viewHolder.moisEvent=(TextView) convertView.findViewById(R.id.id_mois);
            viewHolder.anneeEvent=(TextView) convertView.findViewById(R.id.id_annee);
            viewHolder.descriptionEvent=(TextView) convertView.findViewById(R.id.id_description_evenement_AVenir);
            viewHolder.adresse=(TextView)convertView.findViewById(R.id.id_adresseEvenement_AVenir);
            convertView.setTag(viewHolder);
        }
/*Association des éléments visuels de chaque ligne avec l'objet Java Evenement*/
        Evenement evenement =getItem(position);
        viewHolder.nomEvenement.setText(evenement.getNomEvent());
        viewHolder.descriptionEvent.setText(evenement.getDescriptionEvent());
        viewHolder.adresse.setText(evenement.getAdresse());
        Timestamp timestamp = evenement.getDateEvent();
        Calendar cal= Calendar.getInstance();
        cal.setTime(new Date(timestamp.getTime()));
        viewHolder.jourEvent.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
        viewHolder.moisEvent.setText(mois(cal.get(Calendar.MONTH)));
        viewHolder.anneeEvent.setText(String.valueOf(cal.get(Calendar.YEAR)));
        return convertView;
    }


    public class EvenementViewAdapter {
        public TextView nomEvenement;
        public TextView jourEvent;
        public TextView moisEvent;
        public TextView anneeEvent;
        public TextView adresse;
        public TextView descriptionEvent;
    }
}
