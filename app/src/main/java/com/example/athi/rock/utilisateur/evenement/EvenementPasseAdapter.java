package com.example.athi.rock.utilisateur.evenement;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.athi.rock.R;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Athi on 22/02/2018.
 * Gerer les éléments des lignes de la liste des événements passés
 */

public class EvenementPasseAdapter extends ArrayAdapter<Evenement> {

    public class EvenementPasseViewHolder {
        public TextView nomEvenement;
        public TextView jourEvent;
        public TextView moisEvent;
        public TextView anneeEvent;

    }

    public EvenementPasseAdapter(Context context, List<Evenement> evenements) {super(context, 0,evenements);}

    //Abréviation des mois (tableau)-> affichage dans la vue
    public String mois(int nb){
        String[] moisAbreviations={"JAN","FEV","MARS","AVR","MAI","JUIN","JUIL","AOUT","SEPT","OCT","NOV","DEC"};
        return moisAbreviations[nb-1];
    }
/*Récuperer les éléments visuels de chaque ligne de la liste */
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView==null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_liste_evenement_passe,parent,false);
        }

        EvenementPasseViewHolder viewHolder= (EvenementPasseViewHolder) convertView.getTag();
        if (viewHolder==null){
            viewHolder=new EvenementPasseViewHolder();
            viewHolder.nomEvenement= (TextView) convertView.findViewById(R.id.id_nomEvenement_Passe);
            viewHolder.jourEvent=(TextView) convertView.findViewById(R.id.id_jour_passe);
            viewHolder.moisEvent=(TextView) convertView.findViewById(R.id.id_mois_passe);
            viewHolder.anneeEvent=(TextView) convertView.findViewById(R.id.id_annee_passe);
            convertView.setTag(viewHolder);
        }
        /*Association de la vue avec les éléments de l'objet Java evenementPasse*/
        Evenement evenement =getItem(position);
        viewHolder.nomEvenement.setText(evenement.getNomEvent());
        Date date= evenement.getDateEvent();
        Calendar cal= Calendar.getInstance();
        cal.setTime(new Date(date.getTime()));
        viewHolder.jourEvent.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
        viewHolder.moisEvent.setText(mois(cal.get(Calendar.MONTH)));
        viewHolder.anneeEvent.setText(String.valueOf(cal.get(Calendar.YEAR)));
        return convertView;
    }
}
