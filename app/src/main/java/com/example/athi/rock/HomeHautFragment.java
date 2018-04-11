package com.example.athi.rock;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athi.rock.administrateur.AdminCodeActivity;
import com.example.athi.rock.utilisateur.evenement.Evenement;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static android.support.v4.content.ContextCompat.startActivity;


/**
 * A simple {@link Fragment} subclass.

 */
public class HomeHautFragment extends Fragment {

    public HomeHautFragment() {
        // Required empty public constructor
    }
    TextView titreEvenement;
    TextView adresseEvenement;
    TextView jour;
    TextView mois;
    TextView annee;

    /*Association au layout du fragement HomeHautFragment*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_haut, container, false);
//        Appel des emplacements visuels pour les fonctions
        ImageView btnFacebook = (ImageView) view.findViewById(R.id.btn_facebook);
        TextView titreEvenement = view.findViewById(R.id.id_prochain_evenement_titre);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://m.facebook.com";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent, null);
            }
        });

        affichageAccueil();

        return view;
    }

    public void affichageAccueil() {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("evenement").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Date today = Calendar.getInstance().getTime();
                Evenement evenementLePlusProche = null;
                Date dateEvenementLePlusProche = new Date(2300,12,11);
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    Evenement evenement1 = child.getValue(Evenement.class);
                    if (evenement1.getDateEvent().compareTo(today) >= 0 && evenement1.getDateEvent().compareTo(dateEvenementLePlusProche) <= 0) {
                            evenementLePlusProche=evenement1;
                            dateEvenementLePlusProche=evenement1.getDateEvent();
                    }
                }

                Date date = evenementLePlusProche.getDateEvent();
                Calendar cal= Calendar.getInstance();
                cal.setTime(new Date(date.getTime()));
                titreEvenement= getView().findViewById(R.id.id_prochain_evenement_titre);
                adresseEvenement=getView().findViewById(R.id.id_prochain_evenement_adresse);
                jour=getView().findViewById(R.id.id_jour_prochaine_evenement);
                mois=getView().findViewById(R.id.id_mois_prochaine_evenement);
                annee=getView().findViewById(R.id.id_annee_prochaine_evenement);

                titreEvenement.setText(evenementLePlusProche.getNomEvent());
                adresseEvenement.setText(evenementLePlusProche.getAdresse());
                jour.setText(String.valueOf(cal.get(Calendar.DAY_OF_MONTH)));
                mois.setText(mois(cal.get(Calendar.MONTH)));
                annee.setText(String.valueOf(cal.get(Calendar.YEAR)));


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public String mois(int nb){
        String[] moisAbreviations={"JAN","FEV","MARS","AVR","MAI","JUIN","JUIL","AOUT","SEPT","OCT","NOV","DEC"};
        return moisAbreviations[nb];
    }
}


