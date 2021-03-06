package com.example.athi.rock.administrateur.passeadmin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.athi.rock.utilisateur.MainActivity;
import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.passes.Passe;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupprimerPasseFragment extends Fragment {
    public  SupprimerPasseFragment(){
        //Required empty public constructor
    }
    /*SupprimerPasseFragment listener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }*/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_supprimer_passe, container, false);
        listerPasseASupprimer();
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

    /*@Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }*/
    private void listerPasseASupprimer() {
        //on récupère la largeur et la hauteur du téléphone pour adapter la pop up au tel
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int width=dm.widthPixels;
        final int height = dm.heightPixels;


        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("passe").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Passe> listPasse = new ArrayList<Passe>();
                List<String> keys = new ArrayList<String>();
                ListView listViewPasse = (ListView) getView().findViewById(R.id.id_listViewPasse_supprimer);
                PasseSupprimerAdapter adapter = new PasseSupprimerAdapter(getActivity(), listPasse, keys,width,height);
                adapter.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    listPasse.add(child.getValue(Passe.class));
                    keys.add(child.getKey());
                }
                adapter = new PasseSupprimerAdapter(getActivity(), listPasse, keys,width,height);
                listViewPasse.setAdapter(adapter);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
