package com.example.athi.rock.utilisateur.evenement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.athi.rock.R;
import com.example.athi.rock.utilisateur.equipe.MembreAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class ImageFragment extends Fragment {

    public ImageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        String nomImage=bundle.getString("UrlImage");
        View view=inflater.inflate(R.layout.fragment_image,container,false);
        ImageView imageView = view.findViewById(R.id.image_evenement);
        Picasso.with(getContext()).load(Uri.parse(nomImage)).into(imageView);
        return view;
    }

}
