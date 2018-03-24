package com.example.athi.rock.utilisateur.evenement;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import com.example.athi.rock.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.Timestamp;

/**
 * A simple {@link Fragment} subclass.
 * sous-item de l'item evenement
 * liste des évenements à venir -> listView
 * précision sur les composants de la liste -> EvenementAVenirAdapter
 */
public class EvenementAVenirFragment extends Fragment {

    public EvenementAVenirFragment() {
        // Required empty public constructor
    }
/*Association aux éléments (layout) de la vue de EvenementAVenirFragment*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_evenement_avenir, container, false);
        List<Evenement> evenements = genererEvenement();
        ListView listViewEvenement =(ListView)view.findViewById(R.id.id_listViewEvenement_AVenir);
        EvenementAVenirAdapter adapter = new EvenementAVenirAdapter(getActivity(),evenements);
        listViewEvenement.setAdapter(adapter);
        return view;
    }
/*Creation en "dur" de la liste des évenements à venir */
    private List<Evenement> genererEvenement() {
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
        evenements.add(new Evenement(1,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        evenements.add(new Evenement(1,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp3));
        evenements.add(new Evenement(1,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp));
        evenements.add(new Evenement(1,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", timestamp2));
        return evenements;
    }
}
