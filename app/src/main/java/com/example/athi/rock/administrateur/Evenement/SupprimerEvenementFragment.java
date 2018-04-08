package com.example.athi.rock.administrateur.Evenement;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.athi.rock.MainActivity;
import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.evenement.Evenement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupprimerEvenementFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_supprimer_evenement, container, false);
        List<Evenement> evenementList= genererEvenements();
        ListView listViewEvenements =(ListView) view.findViewById(R.id.id_listViewEvenement_supprimer);
        EvenementSupprimerAdapter adapter = new EvenementSupprimerAdapter(getActivity(),evenementList);
        listViewEvenements.setAdapter(adapter);
        //Bouton retour vers l'activité utilisateur (HomeHautFragment)
        Button returnButton = (Button) view.findViewById(R.id.btn_retour_utilisateur);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"retour à la maison",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private List<Evenement> genererEvenements() {
        List<Evenement> evenements = new ArrayList<Evenement>();
        /*Creation d'un objet Timestamp afin de récuper le jour, le mois et l'année séparément*/
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,14);
        calendar.set(Calendar.MONTH,2);
        calendar.set(Calendar.YEAR,2018);
        Timestamp timestamp=new Timestamp(calendar.getTime().getTime());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH,2);
        calendar2.set(Calendar.MONTH,11);
        calendar2.set(Calendar.YEAR,2018);
        Timestamp timestamp2=new Timestamp(calendar2.getTime().getTime());
        Calendar calendar3 = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,4);
        calendar.set(Calendar.MONTH,5);
        calendar.set(Calendar.YEAR,2019);
        Timestamp timestamp3=new Timestamp(calendar.getTime().getTime());

        evenements.add(new Evenement(1,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp));
        evenements.add(new Evenement(32,"La Foire","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        evenements.add(new Evenement(6,"AfterWork 1","description de la prestige", "Prépa HEI, rue Colbert", timestamp3));
        evenements.add(new Evenement(35,"AfterWork 2","description de la prestige", "Prépa HEI, rue Colbert", timestamp));
        evenements.add(new Evenement(8,"Viens dans C","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        evenements.add(new Evenement(9,"Challenge","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        evenements.add(new Evenement(4,"C'est partii","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        evenements.add(new Evenement(10,"Yahou !!","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        evenements.add(new Evenement(7,"Pirates","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));

        return evenements;
    }
}
