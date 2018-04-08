package com.example.athi.rock.administrateur.Evenement;
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
import com.example.athi.rock.utilisateur.evenement.Evenement;
import java.util.List;

/**
 * Created by Athi on 26/03/2018.
 * EvenementSupprimerAdapter permet de gérer chaque ligne de la listView associé à EvenementSupprimerFragment
 * Layout ligne: ligne_liste_supprimer
 * Au click sur l'ImageButton Supprimer une pop up apparait pour Valider ou Non la suppression.
 * layout popup: popup_supprimer
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_liste_supprimer,parent, false);
        }

        EvenementSupprimerViewHolder viewHolder = (EvenementSupprimerViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new EvenementSupprimerViewHolder();
            viewHolder.nomEvent= (TextView) convertView.findViewById(R.id.id_nom_supprimer);
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
                Evenement evenement1 = getItem(position);
                showPopup(evenement1);
            }
        });
        return convertView;
    }
    /*Popup pour Valider la suppression d'un evenement*/
    private PopupWindow pw;
    Button Close;
    Button Supprimer;
    private void showPopup(final Evenement evenement2) {
        try {
            View viewpopup;
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            viewpopup = layoutInflater.inflate(R.layout.popup_supprimer,null);
            TextView textView = (TextView) viewpopup.findViewById(R.id.popup_text);
            textView.setText("Voulez vous supprimer: ");
            TextView nomEvenementSupprimer = (TextView) viewpopup.findViewById(R.id.popup_nom);
            nomEvenementSupprimer.setText("\" "+ evenement2.getNomEvent() +" \" ?");
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
                    Toast.makeText(getContext(),evenement2.getNomEvent()+" a été supprimer",Toast.LENGTH_SHORT).show();
                    pw.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
