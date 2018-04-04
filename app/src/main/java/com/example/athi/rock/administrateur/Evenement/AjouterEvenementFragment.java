package com.example.athi.rock.administrateur.Evenement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.evenement.Evenement;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class AjouterEvenementFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_ajouter_evenement, container, false);
        Button btnValiderAjoutEvenement = (Button) view.findViewById(R.id.btnValider_evenement_ajouter);

        final DatabaseReference evenement = FirebaseDatabase.getInstance().getReference();

        btnValiderAjoutEvenement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nom = (EditText) getActivity().findViewById(R.id.id_nom_evenement_ajouter);
                String nomEvent = nom.getText().toString();

                EditText description =(EditText) getActivity().findViewById(R.id.id_description_evenement_ajouter);
                String desciptionEvent = description.getText().toString();

                EditText adresse = (EditText) getActivity().findViewById(R.id.id_adresse_evenement_ajouter);
                String adresseEvent = adresse.getText().toString();

                DatePicker date =(DatePicker) getActivity().findViewById(R.id.id_date_evenement_ajouter);

                //conversion de la date en Timestamp
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_MONTH,date.getDayOfMonth());
                calendar.set(Calendar.MONTH,date.getMonth());
                calendar.set(Calendar.YEAR,date.getYear());
                Date timestamp = new Date(calendar.getTime().getTime());

                Evenement nouveauEvenement = new Evenement(3,nomEvent,desciptionEvent,adresseEvent,timestamp);
                evenement.child("evenement").push().setValue(nouveauEvenement);
                //Evenement evenement = new Evenement(36, nomEvent,desciptionEvent,adresseEvent, timestamp);
                String dateString = date.getDayOfMonth()+"/"+date.getMonth()+"/"+date.getYear();
                Toast.makeText(getContext(),"Relier à Firebase !! ", Toast.LENGTH_SHORT).show();
                showpopup(nomEvent,desciptionEvent,adresseEvent,dateString);
            }
        });

        return view;
    }
    private PopupWindow pw;
    Button Close;
    Button Ajouter;
    private void showpopup(final String nomEvent, String desciptionEvent, String adresseEvent, String dateString) {
        try {
            View viewpopup;
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            viewpopup = layoutInflater.inflate(R.layout.popup_ajouter,null);
            TextView textView = (TextView) viewpopup.findViewById(R.id.popup_text_ajouter);
            textView.setText("Voulez vous ajouter: ");
            TextView nomEvenementAjouter = (TextView) viewpopup.findViewById(R.id.popup_nom_ajouter);
            nomEvenementAjouter.setText("\" "+ nomEvent +" \" ");
            TextView descriptionEvenementAjouter =(TextView) viewpopup.findViewById(R.id.popup_description);
            descriptionEvenementAjouter.setText("\" "+ desciptionEvent +" \" ");
            TextView adresseEvenementAjouter =(TextView) viewpopup.findViewById(R.id.popup_adresse);
            adresseEvenementAjouter.setText("à \" "+ adresseEvent +" \" ");
            TextView dateEvenementAjouter =(TextView) viewpopup.findViewById(R.id.popup_date);
            dateEvenementAjouter.setText("le \" "+ dateString +" \" ");
            Close = (Button) viewpopup.findViewById(R.id.popup_non);
            Ajouter = (Button) viewpopup.findViewById(R.id.popup_oui);
            pw = new PopupWindow(viewpopup,300,600,true);
            pw.showAtLocation(viewpopup, Gravity.CENTER, 0, 0);
            pw.getAnimationStyle();
            Close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    pw.dismiss();
                }
            });
            Ajouter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    /*TODO: mettre la fonction ajouterEvent(parametre evenement sauf id(automatique sur la base))*/
                    Toast.makeText(getContext(),"\""+nomEvent+"\" a été ajouter",Toast.LENGTH_SHORT).show();
                    pw.dismiss();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
