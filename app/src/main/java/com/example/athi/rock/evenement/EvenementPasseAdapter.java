package com.example.athi.rock.evenement;


import android.app.FragmentTransaction;
import android.content.Context;

import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athi.rock.R;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Athi on 22/02/2018.
 * Gerer les éléments des lignes de la liste des événements passés
 */

public class EvenementPasseAdapter extends ArrayAdapter<EvenementPasse> {

    public EvenementPasseAdapter(Context context, List<EvenementPasse> evenements) {super(context, 0,evenements);}

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
            //viewHolder.btnAlbum=(Button) convertView.findViewById(R.id.id_btnAlbum);
            convertView.setTag(viewHolder);
        }
        /*Association de la vue avec les éléments de l'objet Java evenementPasse*/
        EvenementPasse evenementPasse =getItem(position);
        viewHolder.nomEvenement.setText(evenementPasse.getNomEvent());
        Timestamp timestamp = evenementPasse.getDateEvent();
        Calendar cal= Calendar.getInstance();
        cal.setTime(new Date(timestamp.getTime()));
        viewHolder.jourEvent.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
        viewHolder.moisEvent.setText(mois(cal.get(Calendar.MONTH)));
        viewHolder.anneeEvent.setText(String.valueOf(cal.get(Calendar.YEAR)));
        /*viewHolder.btnAlbum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button =(Button) view;
                EvenementPasse evenementPasse1 = getItem(position);
                AlbumEventPasseFragment albumEventPasseFragment = new AlbumEventPasseFragment();
                FragmentManager fragmentManager =
                android.support.v4.app.FragmentTransaction fragmentTransaction= fm.beginTransaction();
                fragmentTransaction.replace(R.id.album_content_frame,albumEventPasseFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
                Toast.makeText(getContext(),"Album"+evenementPasse1.getIdEvent(),Toast.LENGTH_SHORT).show();

            }
        });*/
        return convertView;
    }

    public class EvenementPasseViewAdapter {
        public TextView nomEvenement;
        public TextView jourEvent;
        public TextView moisEvent;
        public TextView anneeEvent;
        public Button btnAlbum;
    }
}
