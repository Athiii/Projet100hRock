package com.example.athi.rock.utilisateur.passes;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.athi.rock.R;

import java.util.List;

/**
 * Created by Athi on 12/02/2018.
 *
 */

public class PasseAdapter extends ArrayAdapter<Passe>{

    public class PasseViewHolder {
        public TextView nom;
        public ImageView videoPasse;
        public GridView etoiles;
    }
    public PasseAdapter(Context context, List<Passe> passes) {
        super(context, 0,passes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.ligne_liste_passe,parent, false);
        }

        PasseViewHolder viewHolder = (PasseViewHolder) convertView.getTag();
        if(viewHolder == null){
            viewHolder = new PasseViewHolder();
            viewHolder.nom = (TextView) convertView.findViewById(R.id.id_nom_passe);
            viewHolder.videoPasse = (ImageView) convertView.findViewById(R.id.id_video_passe);
            viewHolder.etoiles =(GridView)convertView.findViewById(R.id.id_liste_etoiles);
            convertView.setTag(viewHolder);
        }

        //getItem(position) va récupérer l'item [position] de la List<Passe> passe
        Passe passe = getItem(position);
        EtoileAdapter etoileAdapter = new EtoileAdapter(getContext(),passe.getNiveau());
        viewHolder.nom.setText(passe.getNom());
        viewHolder.videoPasse.setImageDrawable(new ColorDrawable(passe.getColor()));
        viewHolder.etoiles.setAdapter(etoileAdapter);
        return convertView;
    }

}
