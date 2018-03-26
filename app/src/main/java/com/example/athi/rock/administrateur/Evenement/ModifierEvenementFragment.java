package com.example.athi.rock.administrateur.Evenement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.evenement.Evenement;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ModifierEvenementFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_modifier_evenement, container, false);
        List<Evenement> evenementList= genererEvenements();
        ListView listViewEvenements =(ListView) view.findViewById(R.id.id_listViewEvenement_modifier);
        EvenementModifierAdapter adapter = new EvenementModifierAdapter(getActivity(),evenementList);
        listViewEvenements.setAdapter(adapter);
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
        evenements.add(new Evenement(32,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        evenements.add(new Evenement(6,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp3));
        evenements.add(new Evenement(35,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp));
        evenements.add(new Evenement(8,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        evenements.add(new Evenement(9,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        evenements.add(new Evenement(4,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        evenements.add(new Evenement(10,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        evenements.add(new Evenement(7,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));

        return evenements;
    }
}
