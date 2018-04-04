package com.example.athi.rock.utilisateur.evenement;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.example.athi.rock.R;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * sous-item de l'item evenement du menu principal
 * liste des évenements passés-> listView
 * précision des éléments de la liste-> EvenementPasseAdapter
 */
public class EvenementPasseFragment extends Fragment {

    public EvenementPasseFragment() {
        // Required empty public constructor
    }
    /*Association aux éléments (layout) de la vue de EvenementPasseFragment*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_evenement_passe, container, false);
        List<Evenement> evenements = genererEvenementPasse();
        ListView listViewEvenement =(ListView)view.findViewById(R.id.id_listViewEvenement_Passe);
        EvenementPasseAdapter adapter = new EvenementPasseAdapter(getActivity(),evenements);
        listViewEvenement.setAdapter(adapter);




        listViewEvenement.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),"dedans "+i,Toast.LENGTH_SHORT).show();
                AlbumEventPasseFragment albumEventPasseFragment = new AlbumEventPasseFragment();
                android.support.v4.app.FragmentManager fm = getActivity().getSupportFragmentManager();
                android.support.v4.app.FragmentTransaction fragmentTransaction= fm.beginTransaction();
                fragmentTransaction.replace(android.R.id.content,albumEventPasseFragment);
                fragmentTransaction.addToBackStack("tag");
                fragmentTransaction.commit();
            }
        });

        return view;
    }
    /*Creation en "dur" de la liste des évenements passés*/
    private List<Evenement> genererEvenementPasse() {
        List<Evenement> evenement= new ArrayList<Evenement>();
        /*Creation d'un objet Timestamp afin de récuper le jour, le mois et l'année séparément*/
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,14);
        calendar.set(Calendar.MONTH,2);
        calendar.set(Calendar.YEAR,2018);
        Date date=new Timestamp(calendar.getTime().getTime());
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH,2);
        calendar2.set(Calendar.MONTH,11);
        calendar2.set(Calendar.YEAR,2018);
        Date date1=new Timestamp(calendar2.getTime().getTime());
        Calendar calendar3 = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH,4);
        calendar.set(Calendar.MONTH,5);
        calendar.set(Calendar.YEAR,2019);
        Date date2=new Timestamp(calendar.getTime().getTime());

        evenement.add(new Evenement(1,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", date));
        evenement.add(new Evenement(2,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", date1));
        evenement.add(new Evenement(3,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", date2));
        evenement.add(new Evenement(4,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", date));
        evenement.add(new Evenement(5,"Prestige","description de la prestige", "Prépa HEI, rue Colbert", date2));
        return evenement;
    }
}
