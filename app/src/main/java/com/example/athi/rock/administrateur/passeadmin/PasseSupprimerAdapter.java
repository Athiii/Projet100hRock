package com.example.athi.rock.administrateur.passeadmin;

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
import com.example.athi.rock.utilisateur.passes.Passe;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

/**
 * Created by Athi on 09/04/2018.
 */

public class PasseSupprimerAdapter extends ArrayAdapter<Passe> {
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private List<String> keys;
    public class PasseSupprimerViewHolder{
        public TextView nomPasse;
        public ImageButton supprimer;
    }
    public PasseSupprimerAdapter(Context context, List<Passe> passes,List<String> keys) {
        super(context,0, passes);
        this.keys= keys;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        if(convertView == null){
            convertView= LayoutInflater.from(getContext()).inflate(R.layout.ligne_liste_supprimer,parent,false);
        }
        PasseSupprimerViewHolder viewHolder = (PasseSupprimerViewHolder) convertView.getTag();
        if (viewHolder==null){
            viewHolder = new PasseSupprimerViewHolder();
            viewHolder.nomPasse = (TextView) convertView.findViewById(R.id.id_nom_supprimer);
            viewHolder.supprimer=(ImageButton) convertView.findViewById(R.id.btn_supprimer);
            convertView.setTag(viewHolder);
        }
        //getItem(position) va récupérer l'item [position] de la List<Musiques> musiques
        Passe passe = getItem(position);
        viewHolder.nomPasse.setText(passe.getNom());
        viewHolder.supprimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Passe passe1 = getItem(position);
                showPopup(passe1,position);

            }
        });
        return convertView;

}
    private PopupWindow pw;
    Button Close;
    Button Supprimer;
    private void showPopup(final Passe passe1, final int position) {
        try {
            View viewpopup;
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            viewpopup = layoutInflater.inflate(R.layout.popup_supprimer,null);
            TextView textView = (TextView) viewpopup.findViewById(R.id.popup_text);
            textView.setText("Voulez vous supprimer: ");
            TextView nomEvenementSupprimer = (TextView) viewpopup.findViewById(R.id.popup_nom);
            nomEvenementSupprimer.setText("\" "+ passe1.getNom() +" \" ?");
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
                    //supprimer la musique de firebase
                    databaseReference.child("passe").child(keys.get(position)).removeValue();
                    Toast.makeText(getContext(),passe1.getNom()+" a été supprimer",Toast.LENGTH_SHORT).show();
                    pw.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
