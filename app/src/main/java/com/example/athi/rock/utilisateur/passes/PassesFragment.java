package com.example.athi.rock.utilisateur.passes;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.equipe.Membre;
import com.example.athi.rock.utilisateur.equipe.MembreAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
        listerPasse();
//        List<Passe> passes= genererPasses();
//        ListView listViewPasse = (ListView) view.findViewById(R.id.id_listViewPasse);
//        PasseAdapter adapter = new PasseAdapter(getActivity(),passes);
//        listViewPasse.setAdapter(adapter);
        return view;
    }
/*Création en "dur" de la liste des passes -> sera directement dans la base de données
* utilisation de l'objet Passe
*/
/*Relation avec les éléments de la vue de EquipeFragment ici seulement une listView*/


    public void listerPasse() {
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        final List<Passe> affichageListPasse=new ArrayList<Passe>();
        databaseReference.child("passe").addValueEventListener(new ValueEventListener() {
            //cette méthode sera implémenté à chaque fois que l'on change la database.
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //renvoie la référence de chacun des sous objet de membre.
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    Passe passe1 = child.getValue(Passe.class);
                    affichageListPasse.add(passe1);
                }
                ListView listViewEquipe = (ListView) getView().findViewById(R.id.id_listViewPasse);
                PasseAdapter adapter = new PasseAdapter(getActivity(),affichageListPasse);
                listViewEquipe.setAdapter(adapter);
                listViewEquipe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Toast.makeText(getContext(),"Coucou",Toast.LENGTH_SHORT).show();
                        String url=affichageListPasse.get(i).getVideoPasseUrl().toString();
                        Toast.makeText(getContext(),url,Toast.LENGTH_SHORT).show();
                    }
                });
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
//    private List<Passe> genererPasses() {
//        List<Passe> passes =new ArrayList<Passe>();
//        passes.add(new Passe("Le Huit",2,Color.GRAY));
//        passes.add(new Passe("Transition 1", 1, Color.GRAY));
//        passes.add(new Passe("Transition 2",1,Color.GRAY));
//        passes.add(new Passe("Transition 3",1, Color.GRAY));
//        passes.add(new Passe("Transition 4", 5, Color.GRAY));
//        passes.add(new Passe("Le Soleil", 4, Color.GRAY));
//        passes.add(new Passe("La Pirouette",3, Color.GRAY));
//        return passes;
//    }


}
