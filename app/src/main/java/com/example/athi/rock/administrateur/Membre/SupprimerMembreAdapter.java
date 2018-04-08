package com.example.athi.rock.administrateur.Membre;

import android.content.Context;
import android.support.annotation.NonNull;
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
import com.example.athi.rock.utilisateur.equipe.Membre;

/**
 * Created by Athi on 08/04/2018.
 */

public class SupprimerMembreAdapter extends ArrayAdapter<Membre> {

    public class MembreSupprimerViewHolder{
        public TextView nom;
        public ImageButton supprimer;
    }
    public SupprimerMembreAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_liste_supprimer,parent, false);
        }

        MembreSupprimerViewHolder viewHolder = (MembreSupprimerViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new MembreSupprimerViewHolder();
            viewHolder.nom= (TextView) convertView.findViewById(R.id.id_nom_supprimer);
            viewHolder.supprimer=(ImageButton) convertView.findViewById(R.id.btn_supprimer);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Musiques> musiques
        Membre membre= getItem(position);

        //il ne reste plus qu'à remplir notre vue
        viewHolder.nom.setText(membre.getRole()+": "+membre.getPrenom());
        viewHolder.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Membre membre1 = getItem(position);
                showPopup(membre1);
            }
        });
        return convertView;
    }
    /*Popup pour Valider la suppression d'un evenement*/
    private PopupWindow pw;
    Button Close;
    Button Supprimer;
    private void showPopup(final Membre membre) {
        try {
            View viewpopup;
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            viewpopup = layoutInflater.inflate(R.layout.popup_supprimer,null);
            TextView textView = (TextView) viewpopup.findViewById(R.id.popup_text);
            textView.setText("Voulez vous supprimer: ");
            TextView nomMembreSupprimer = (TextView) viewpopup.findViewById(R.id.popup_nom);
            nomMembreSupprimer.setText("\" "+ membre.getRole() +" \" ?");
            Close = (Button) viewpopup.findViewById(R.id.popup_non);
            Supprimer = (Button) viewpopup.findViewById(R.id.popup_oui);
            pw = new PopupWindow(viewpopup,300, 300, true);
            pw.showAtLocation(viewpopup, Gravity.CENTER, 0, 0);
            Close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pw.dismiss();
                }
            });
            Supprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*TODO: mettre la fonction supprimerParId(evenenement2)*/
                    Toast.makeText(getContext(),membre.getRole()+"-"+membre.getPrenom()+" a été supprimer",Toast.LENGTH_SHORT).show();
                    pw.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
