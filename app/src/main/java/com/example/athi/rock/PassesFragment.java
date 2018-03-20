package com.example.athi.rock;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;

import com.example.athi.rock.equipe.MembreAdapter;
import com.example.athi.rock.passes.EtoileAdapter;
import com.example.athi.rock.passes.Passe;
import com.example.athi.rock.passes.PasseAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * item du menu seconaire de Danse
 * Liste des passes-> ListView
 * précisions sur les lignes -> PasseAdapter
 */
public class PassesFragment extends Fragment {

    public PassesFragment() {
        // Required empty public constructor
    }

    /*Association avec la vue du fragment PasseFragment ici seulement une ListView*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_passes, container, false);
        List<Passe> passes= genererPasses();
        ListView listViewPasse = (ListView) view.findViewById(R.id.id_listViewPasse);
        PasseAdapter adapter = new PasseAdapter(getActivity(),passes);
        listViewPasse.setAdapter(adapter);
        return view;
    }
/*Création en "dur" de la liste des passes -> sera directement dans la base de données
* utilisation de l'objet Passe
*/
    private List<Passe> genererPasses() {
        List<Passe> passes =new ArrayList<Passe>();
        passes.add(new Passe("Le Huit",2,Color.GRAY));
        passes.add(new Passe("Transition 1", 1, Color.GRAY));
        passes.add(new Passe("Transition 2",1,Color.GRAY));
        passes.add(new Passe("Transition 3",1, Color.GRAY));
        passes.add(new Passe("Transition 4", 5, Color.GRAY));
        passes.add(new Passe("Le Soleil", 4, Color.GRAY));
        passes.add(new Passe("La Pirouette",3, Color.GRAY));
        return passes;
    }


}
