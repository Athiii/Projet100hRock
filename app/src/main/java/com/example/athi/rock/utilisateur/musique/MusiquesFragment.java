package com.example.athi.rock.utilisateur.musique;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.athi.rock.R;

import java.util.ArrayList;
import java.util.List;


/*
 * A simple {@link Fragment} subclass.
 * Item du menu secondaire de Danse
 * liste des musiques -> ListView
 * elements de la liste -> MusiqueAdapter
 */
public class MusiquesFragment extends Fragment {

    public MusiquesFragment() {
        // Required empty public constructor
    }
/*Association à la vue de MusiquesFragment ici seulement la listeView*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_musiques, container, false);
        List<Musique> musiques = genererMusiques();
        ListView listViewMusique = (ListView) view.findViewById(R.id.id_listViewMusique);
        MusiqueAdapter adapter = new MusiqueAdapter(getActivity(),musiques);
        listViewMusique.setAdapter(adapter);
        return view;
    }
/*Création de la liste des musiques en "dur" -> sera directement dans la base de données apres*/
    private List<Musique> genererMusiques() {
        List<Musique> musiques = new ArrayList<Musique>();
        musiques.add(new Musique(1,"Hymn For The Weekend", "Coldplay",10));
        musiques.add(new Musique(2,"Hymn For The Weekend", "Coldplay",1));
        musiques.add(new Musique(3,"Hymn For The Weekend", "Coldplay",122));
        musiques.add(new Musique(4,"Hymn For The Weekend", "Coldplay",13));
        musiques.add(new Musique(5,"Hymn For The Weekend", "Coldplay",4));
        musiques.add(new Musique(6,"Hymn For The Weekend", "Coldplay",500));
        musiques.add(new Musique(7,"Hymn For The Weekend", "Coldplay",1000));
        musiques.add(new Musique(8,"Hymn For The Weekend", "Coldplay",40));
        musiques.add(new Musique(9,"Hymn For The Weekend", "Coldplay",0));
        musiques.add(new Musique(10,"Hymn For The Weekend", "Coldplay",3));
        return musiques;
    }
}
