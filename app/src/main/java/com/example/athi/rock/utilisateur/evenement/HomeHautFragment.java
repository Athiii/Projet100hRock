package com.example.athi.rock.utilisateur.evenement;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.athi.rock.R;

import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.

 */
public class HomeHautFragment extends Fragment {
    public HomeHautFragment(){
        //Required empty public constructor
    }
    HomeHautFragment listener;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_haut, container, false);
//        Appel des emplacements visuels pour les fonctions
        ImageView btnFacebook = (ImageView) view.findViewById(R.id.btn_facebook);
        btnFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "https://m.facebook.com";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent, null);
            }
        });
        return view;
    }
    @Override
    public void onDetach() {
        super.onDetach();
        this.listener = null;
    }
}


