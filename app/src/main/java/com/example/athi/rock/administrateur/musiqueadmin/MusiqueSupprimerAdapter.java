package com.example.athi.rock.administrateur.musiqueadmin;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.musique.Musique;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Athi on 09/04/2018.
 */

public class MusiqueSupprimerAdapter extends ArrayAdapter<Musique> {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<String> keys;
    private int lar;
    private int haut;

    public class MusiqueSupprimerViewHolder{
        public TextView nomMusique;
        public ImageButton supprimer;
    }
    public MusiqueSupprimerAdapter( Context context, List<Musique> musiques, List<String> keys,int largeur, int hauteur) {
        super(context,0, musiques);
        this.keys=keys;
        this.lar=largeur;
        this.haut=hauteur;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.ligne_liste_supprimer,parent,false);
        }
        //lien avec les éléments de la vue
        MusiqueSupprimerViewHolder viewHolder = (MusiqueSupprimerViewHolder) convertView.getTag();
        if (viewHolder==null){
            viewHolder = new MusiqueSupprimerViewHolder();
            viewHolder.nomMusique = (TextView) convertView.findViewById(R.id.id_nom_supprimer);
            viewHolder.supprimer=(ImageButton) convertView.findViewById(R.id.btn_supprimer);
            convertView.setTag(viewHolder);
        }
        //getItem(position) va récupérer l'item [position] de la List<Musiques> musiques
        Musique musique = getItem(position);
        viewHolder.nomMusique.setText(musique.getNomMusique());
        viewHolder.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Musique musique1 = getItem(position);
                showPopup(musique1,position);

            }
        });
        return convertView;
    }
    private PopupWindow pw;
    Button Close;
    Button Supprimer;
    private void showPopup(final Musique musique1, final int position) {
        try {
            View viewpopup;
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            viewpopup = layoutInflater.inflate(R.layout.popup_supprimer,null);
            TextView textView = (TextView) viewpopup.findViewById(R.id.popup_text);
            textView.setText("Voulez vous supprimer: ");
            TextView nomEvenementSupprimer = (TextView) viewpopup.findViewById(R.id.popup_nom);
            nomEvenementSupprimer.setText("\" "+ musique1.getNomMusique() +" \" ?");
            Close = (Button) viewpopup.findViewById(R.id.popup_non);
            Supprimer = (Button) viewpopup.findViewById(R.id.popup_oui);

            //pn créer la nouvelle popup et on indique sa localisation sur la page
            pw = new PopupWindow(viewpopup,lar,haut,true);
            pw.showAtLocation(viewpopup, Gravity.CENTER, 0, 0);
            pw.getAnimationStyle();

            //on défini les fonctions qui dépendent des boutons
            Close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pw.dismiss();
                }
            });
            Supprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //supprimer la musique de firebase
                    databaseReference.child("musique").child(keys.get(position)).removeValue();
                    Toast.makeText(getContext(),musique1.getNomMusique()+" a été supprimer",Toast.LENGTH_SHORT).show();
                    pw.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
