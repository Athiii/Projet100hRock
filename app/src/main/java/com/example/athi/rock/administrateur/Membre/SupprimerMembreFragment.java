package com.example.athi.rock.administrateur.Membre;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.athi.rock.MainActivity;
import com.example.athi.rock.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SupprimerMembreFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_supprimer_membre, container, false);

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

}
