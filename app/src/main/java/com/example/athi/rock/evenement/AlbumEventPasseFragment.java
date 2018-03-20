package com.example.athi.rock.evenement;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import com.example.athi.rock.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AlbumEventPasseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_album_event_passe, container, false);
        GridView albumPhotos =(GridView) view.findViewById(R.id.gridview);
        Button returnButton = (Button) view.findViewById(R.id.btn_retour);
        albumPhotos.setAdapter(new AlbumAdapter(getContext()));
        albumPhotos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getContext(),""+i,Toast.LENGTH_SHORT).show();
            }
        });
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"retour à la liste",Toast.LENGTH_SHORT).show();
                FragmentManager fm= getFragmentManager();
                fm.popBackStack();
            }
        });
        return view;
    }

}
