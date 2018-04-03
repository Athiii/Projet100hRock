package com.example.athi.rock.administrateur.Membre;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.equipe.Membre;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.

 */

public class MembreAdminFragment extends Fragment {





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_admin_menbres, container, false);
        Button btnvaliderMembre = (Button) view.findViewById(R.id.btnValider_membre_ajouter);

        //on récupère les références de fire base afin de pouvoir ajouter les donnés au bonne endroit (via le fichier google.json)
        final DatabaseReference membre = FirebaseDatabase.getInstance().getReference();


        btnvaliderMembre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nom = (EditText) getActivity().findViewById(R.id.id_nom_membre_ajouter);
                String nomMembre = nom.getText().toString();


                EditText prenom = (EditText) getActivity().findViewById(R.id.id_prenom_membre_ajouter);
                String prenomMembre = prenom.getText().toString();

                EditText role = (EditText) getActivity().findViewById(R.id.id_role_membre_ajouter);
                String roleMembre = role.getText().toString();


                EditText description = (EditText) getActivity().findViewById(R.id.id_description_membre_ajouter);
                String descriptionMembre = description.getText().toString();

                //on créé un nouvel objet que l'on ajoute à fire base.
                Membre nouveauMembre =new Membre(1,descriptionMembre,nomMembre,prenomMembre,roleMembre);
                membre.child("membre").push().setValue(nouveauMembre);


                // Inflate the layout for this fragment


            }
        });
        return view;
    }
}
